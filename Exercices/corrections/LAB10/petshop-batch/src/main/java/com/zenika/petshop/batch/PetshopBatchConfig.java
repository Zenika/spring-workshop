package com.zenika.petshop.batch;

import com.zenika.petshop.batch.components.PetProcessor;
import com.zenika.petshop.batch.components.PetReader;
import com.zenika.petshop.batch.components.PetWriter;
import com.zenika.petshop.PetshopDataConfig;
import com.zenika.petshop.model.PetEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan
@Import(PetshopDataConfig.class)
@EnableBatchProcessing
public class PetshopBatchConfig {

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public JobRepository jobRepository() {
        return new SimpleJobRepository(
                new MapJobInstanceDao(),
                new MapJobExecutionDao(),
                new MapStepExecutionDao(),
                new MapExecutionContextDao());
    }

    @Bean
    public Resource inputResource() {
        return new ClassPathResource("pets.csv");
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilder, Step chunkStep) {
        return jobBuilder.get("petshopJob")
                .start(chunkStep)
                .build();
    }

    @Bean
    public Step chunkStep(StepBuilderFactory stepBuilder,
                          PetReader reader,
                          PetProcessor processor,
                          PetWriter writer) {
        return stepBuilder.get("chunkStep")
                .<String[], PetEntity>chunk(20)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}

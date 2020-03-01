package com.zenika.petshop.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PetshopBatch {

    public static void main(String[] args) throws JobExecutionException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PetshopBatchConfig.class);

        Job job = applicationContext.getBean(Job.class);
        JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);

        jobLauncher.run(job, new JobParametersBuilder().addLong("uniqueness", System.nanoTime())
                .toJobParameters());
    }

}

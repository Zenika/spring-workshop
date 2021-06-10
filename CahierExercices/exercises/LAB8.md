<div class="pb"></div>

# LAB 8

## Spring Data JPA

Let's add real persistence to the Pet Shop !

- Configure Spring Data Jpa
  - We'll use a simple H2 database, available in *resources/LAB9*
  - You'll need the following dependencies

```xml
  <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>5.4.12.Final</version>
  </dependency>
  <dependency>
      <groupId>javax.persistence</groupId>
      <artifactId>javax.persistence-api</artifactId>
      <version>2.2</version>
  </dependency>
  <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <version>1.4.200</version>
  </dependency>
```

  - You'll need a configuration similar as this one to initialize JPA with H2:

```java
    @Bean
    public DataSource dataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        // Modify with the directory where you put "h2.mv.db"
        ds.setURL("jdbc:h2:c:/temp/h2");  
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.H2);
        jpaVendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan(Entity.class.getPackage().getName());
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
```

- Replace all our hand-crafted repositories with Spring Data Jpa repositories
- Update all impacted code accordingly
  - You won't need the csv files anymore
  - Remove the DAO unit tests. Spring Data is trustworthy
  
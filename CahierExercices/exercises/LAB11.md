<div class="pb"></div>

# LAB 11

## Spring Boot

The final test !

Convert your application into a Spring Boot app.

- Use starters 
  - *spring-boot-starter-data-jpa*
  - *spring-boot-starter-web*
  - *spring-boot-starter-security*

- You will need to use the following Spring Boot configuration to interface with a H2 datasource 

```yaml
spring:
  datasource:
    url: jdbc:h2:c:/temp/h2
    username: 'sa'
    driverClassName: org.h2.Driver
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate.ddl-auto: none
```

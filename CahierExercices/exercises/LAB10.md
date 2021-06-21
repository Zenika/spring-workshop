<div class="pb"></div>

# LAB 10

## Spring Boot

The final test !

Convert your application into a Spring Boot app and simplify your app !

- Your project should inherit from the Spring Boot parent pom:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.5.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

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

<div class="pb"></div>

# LAB 7

## Spring Security

Let's secure our application with Spring Security

- Set up security using an in-memory user credentials
  - "/" should be public
  - "/owners/..." and "/pets/..." should be secured
  - Spring MVC should set up a login page
  
- You will need the following dependencies:
```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>5.2.2.RELEASE</version>
</dependency>
```
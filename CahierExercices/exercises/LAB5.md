<div class="pb"></div>

# LAB 5

## Open API

Let's perform code generation with Maven

- Create a new Maven submodule: *petshop-api*
- Use *petstore.yaml* from *resources/LAB5* to generate a server-side REST API
  - Use generation settings :

```xml
<generatorName>spring</generatorName>
<library>spring-mvc</library>
```

- You will need the following dependencies to compile the generated code :
  
```xml
<dependencies>
    <dependency>
        <groupId>io.swagger</groupId>
        <artifactId>swagger-annotations</artifactId>
        <version>1.6.0</version>
    </dependency>
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>2.0.1.Final</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.3.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.openapitools</groupId>
        <artifactId>jackson-databind-nullable</artifactId>
        <version>0.2.1</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
    </dependency>    
</dependencies>
```

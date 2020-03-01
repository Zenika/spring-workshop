# Maven

<!-- .slide: class="page-title" -->



## Table of content

<!-- .slide: class="toc" -->

- [Clean code](#/1)
- [Testing](#/2)
- **[Maven](#/3)**
- [Spring Core](#/4)
- [Spring MVC](#/5)
- [Spring Security](#/6)
- [Spring Data](#/7)
- [Spring Batch](#/8)
- [Spring Boot](#/9)




## Maven

- A build management tool on steroids
- Brings standardisation and structure to your projects
- Handles dependencies hell (conflicts, transitivities)
- Open source (Apache Software Foundation)
- Very mature (Initial release in 2004, still updated)
- A rich ecosystem with plugins for any task (reporting, code generation, deployment...)



## Installation

- Download the latest version from **https://maven.apache.org/**
- Unzip to any folder you like
- Add **{maven_folder}/bin** to your PATH
- Check everything is OK with **mvn --version**

```
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 13.0.2, vendor: N/A, runtime: /usr/local/Cellar/openjdk/13.0.2+8_2/libexec/openjdk.jdk/Contents/Home
Default locale: fr_FR, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.3", arch: "x86_64", family: "mac"
```

- Done !
- IntelliJ has an embedded Maven installation ready to use



## Artifacts

- A Maven dependency is called an *artifact*
- It's uniquely identified by a *groupId*, *artifactId* and *version* number
- Two essential websites to locate available artifacts: https://mvnrepository.com/ and https://search.maven.org/



## Your project manifest: pom.xml

The heart of a maven project is the *pom.xml* file. Located at the root of your arborescence, it allows your to specify:

- The *groupId*, *artifactId*, *version* of your project
- The packaging type (pom, jar, war, ear...)
- Inheritance information from another maven project
- The dependencies used
- The description of the build process (by default, just compile and create a binary)



## Your project manifest: pom.xml

A simple pom.xml:

```xml
<project>
  <!-- model version is always 4.0.0 for Maven 2.x POMs -->
  <modelVersion>4.0.0</modelVersion>
  <!-- unique project identity -->
  <groupId>com.zenika.workshop</groupId>
  <artifactId>my-app</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <!-- library dependencies -->
  <dependencies>
    <dependency>
      <!-- coordinates of the required library -->
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <!-- this dependency is only used for running and compiling tests -->
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```



## Scopes

- *compile* Default scope. Those dependencies are propagated to dependent projects
- *provided* You expect the JDK or container to provide the dependency at runtime
- *runtime* This scope indicates that the dependency is not required for compilation, but is for execution
- *test* This scope indicates that the dependency is not required for normal use of the application, and is only available for the test compilation and execution phases
- *import* Includes the dependency management section of another artifact
- *system* Similar to *provided*



## Standard Directory Layout

- Maven codifies the structure of your project
- Possible to customize, but not recommended !

```

├── src
│   ├── main            This holds all of your production code
│   │   ├── java	    Source code
│   │   ├── resources   Code resources (xml, yaml, properties...)
│   │   └── webapp      Web app resources (jsp, html, css, jpg...)
│   ├── test            All your unit test code is here
│   │   ├── java	    Test source code
│   │   └── resources   Test resources
│   └── assembly	    Assembly descriptors
└── pom.xml
```



## Splitting your code in modules and submodules

- Structuring your application in modules and submodules promotes reuse and decoupling
- Maven introduces the notion of modules and submodules (aka parent projects / child projects)
- Parent projects are typically packaged as *pom* and mutualize dependency management and build configuration
- Child projects are stored as subfolders of the parent project

```

└── parent
    ├── submodule1
    │   ├── src
    │   └── pom.xml
    ├── submodule2
    │   ├── src
    │   └── pom.xml
    └── pom.xml
```



## Splitting your code: Parent POM

You need to declare child modules in the parent pom

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.zenika.workshop</groupId>
    <artifactId>my-app</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>

    <!-- declare child modules -->
    <modules>
        <module>submodule1</module>
        <module>submodule2</module>
    </modules>

    <dependencyManagement>...</dependencyManagement>
    <build>...</build>

</project>
```



## Parent POM: Dependency management

Mutualize your dependency definitions in the parent

- Versions
- Scopes
- Exclusions
- Configurations

```xml
<dependencyManagement>
    <dependencies>
        <!-- let's define the junit dependency once and for all -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```



## Parent POM: Plugin management

Mutualize your plugin definitions in the parent

- Versions
- Scopes
- Exclusions
- Configurations

```xml
<build>
    <pluginManagement>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <release>13</release>
                </configuration>
            </plugin>
        </plugins>
    </pluginManagement>
</build>
```



## Child pom

Be sure to inherit the parent pom in child modules !

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <!-- coordonates of the parent pom -->
    <parent>
        <groupId>com.zenika.workshop</groupId>
        <artifactId>my-app</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>..</relativePath>
    </parent>
    <artifactId>submodule1</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <dependencies>
        <!-- only need to specify groupId and artifactId ! inherit the rest -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>
</project>
```



## Maven lifecycle

- A Maven build follows a precise succession of steps: the *lifecycle*
- These steps are called *phases*
- Each plugin is configured to trigger on a specific phase, either implicitely (defaut configuration) or explicitely :

```xml
<build>
    <plugins>
        <plugin>
            <artifactId>my-plugin</artifactId>
            <groupId>com.zenika</groupId>
            <version>1.0.0</version>
            <executions>
                <execution>
                    <id>my-exec</id>
                    <!-- this plugin will execute in the compile phase -->
                    <phase>compile</phase>
                    ...
                </execution>
            </configuration>
        </plugin>
    </plugins>
<build>
```



## Maven lifecycle: Main phases

The main phases are: 

- *validate* - Validate the project is structurally correct
- *compile* - Compile the source code
- *test* - Run tests
- *package* - Take the compiled code and package it in its distributable format, such as a JAR.
- *install* - Install the package into the local maven repository
- *deploy* - Done in the CI environment, copies the final package to the remote repository (Nexus, Artifactory...)



## Maven lifecycle: Running a build

To build your project, just launch *mvn {phase}* to execute the build up to the specified phase

```xml
> mvn compile                                                                                                     Jeu 27 fév [INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] my-app                                                             [pom]
[INFO] submodule1                                                         [jar]
[INFO] submodule2                                                         [jar]
[INFO]
[INFO] ---------------------< com.zenika.workshop:my-app >---------------------
...
[INFO]
[INFO] my-app ............................................. SUCCESS [  0.003 s]
[INFO] submodule1 ......................................... SUCCESS [  0.453 s]
[INFO] submodule2 ......................................... SUCCESS [  0.005 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.549 s
[INFO] Finished at: 2020-02-27T11:06:27+01:00
[INFO] ------------------------------------------------------------------------
```



## Maven lifecycle: Many more !

Useful for your custom plugins

- initialize
- generate-sources, process-sources, generate-resources, process-resources
- process-classes
- generate-test-sources, process-test-sources
- generate-test-resources, process-test-resources
- test-compile, process-test-classes
- prepare-package
- pre-integration-test, integration-test, post-integration-test
- verify



## Maven lifecycle: Clean

A special lifecycle: *clean*

- Removes compilated classes, packaged binaries and more
- When all fails, a nice "clean" may help !
- Just run *mvn clean*

```xml
> mvn clean                                                                                                       Jeu 27 fév 1[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
...
[INFO] ---------------------< com.zenika.workshop:my-app >---------------------
[INFO] Building my-app 0.0.1-SNAPSHOT                                     [1/3]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ my-app ---
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.221 s
[INFO] Finished at: 2020-02-27T11:36:08+01:00
[INFO] ------------------------------------------------------------------------
```



<!-- .slide: class="page-tp4" -->



## Maven plugins 

Maven plugins are the elementary brick of the build process

There are plugins for everything ! 

- Compilation
- Code generation
- File operations (copying, deleting, compression, search and replace)
- Test execution
- Packaging
- Project maintenance (release, dependency analysis, updates)
- Deployment
- ... you name it !



## Maven plugins 

Every task of the build process is handled by a plugin, and all Maven core tasks are performed by plugins :

 - *maven-clean-plugin*
 - *maven-compiler-plugin*
 - *maven-resources-plugin*
 - *maven-surefire*
 - *maven-jar-plugin*
 - *maven-assembly-plugin*

- And many more: *https://maven.apache.org/plugins/index.html*



## Using Maven plugins 

Most of the core plugins are registered to the build lifecycle by default but you can customize plugin execution in the <build> section of pom.xml

```xml
 <build>
    <plugins>
        <plugin>
            <!-- groupId is not necessay for core plugins -->
            <!-- Be sure to include it for other plugins  -->
            <artifactId>maven-resources-plugin</artifactId>
            <version>1.0</version>
            <executions>
               <!-- you can specify more than one execution of the plugin -->
              <execution>
                <id>execution1</id>
                <phase>test</phase>
                <goals>     <!-- plugins may be able to perform several tasks -->
                  <goal>copy-resources</goal> <!-- specify the one you want ! -->
                </goals>
                <configuration>
                  <!-- plugin specific configuration goes here... -->
                </configuration>
              </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```



## Maven dependency enforcer plugin

The Enforcer plugin provides goals to control certain environmental constraints such as Maven version, JDK version and OS family along with many more built-in rules and user created rules.

Typical use cases include: 

- Very useful to make a Java library is never used in your project
- Make sure there are no conflicting versions of libraries
- A ton of built in rules : **https://maven.apache.org/enforcer/enforcer-rules/index.html**
- But you can add your own

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-enforcer-plugin</artifactId>
    <version>3.0.0-M3</version>
</plugin>
```



## Maven dependency enforcer plugin

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-enforcer-plugin</artifactId>
    <version>3.0.0-M3</version>
    <executions>
      <execution>
        <id>enforce-banned-dependencies</id>
        <goals>
          <goal>enforce</goal>
        </goals>
        <configuration>
          <rules>
            <bannedDependencies>
              <excludes>
                <!-- Let's ban log4j ! -->
                <exclude>org.apache.logging.log4j</exclude>
                <exclude>org.apache.logging.log4j:log4j-api</exclude>
                <exclude>*:log4j-api</exclude>
              </excludes>
             </bannedDependencies>
          </rules>
        </configuration>
      </execution>
    </executions>
</plugin>
```



## Code generation with Maven

- Files that can be generated by an automated process should never be commited.
- Instead, it's a good idea to use Maven to generate the files on each build, and commit the source specifications instead (xsd, wsdl, ...)
- Ensures noone will ever modify the generated files and commit them back, which could lead to silent divergence from the original specs. 
- Reduces the code base
- Easier to maintain
- Typically removes many false positives from code audit tool reports
- Only one source of truth. Both client and server code are generated from the same informatio,



## Code generation with Maven: OpenAPI

- OpenAPI (formerly known as Swagger) is an effort to standardize the specification of REST services
- REST APIs are documented as a yaml or properties file which details endpoints, parameters, security constraints, data structures...
- OpenAPI provides many tools, such as a swagger editor, code generators, test tools
- And also a Maven plugin !

```xml
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
```



## Code generation with Maven: OpenAPI

Code generation is highly configurable:

- Generates REST client or server code
- Targets many languages (Java, Angular, Go, PHP, Node, and many more...)
- Each language has many variants. Java has Jersey1.x, Jersey2.x, OkHttp, Retrofit1.x, Retrofit2.x, Feign, RestTemplate, RESTEasy, Vertx, Google API Client Library for Java, Rest-assured, Spring 5 Web Client, MicroProfile Rest Client...
- And each variant has many settings (eg: Date API to use for Java)
- You can override templates or write your own from scratch !



## OpenAPI: Example file (1/2)

```xml
openapi: "3.0.0"
paths:
  /pets:
    get:
      operationId: listPets
      parameters:
        - name: limit
          in: query
          required: false
          schema:
            type: integer
            format: int32
      responses:
        '200':
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Pets"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
```



## OpenAPI: Example file (2/2)

```xml
components:
  schemas:
    Pet:
      type: object
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
        tag:
          type: string
    Pets:
      type: array
      items:
        $ref: "#/components/schemas/Pet"
    Error:
      type: object
      properties:
        code:
          type: integer
          format: int32
        message:
          type: string

```



## OpenAPI: Plugin configuration

```xml
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <executions>
        <execution>
            <id>generate-api</id>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <inputSpec>${project.basedir}/src/main/api/petstore.yaml</inputSpec>
        <generatorName>spring</generatorName>
        <generateSupportingFiles>true</generateSupportingFiles>
        <library>spring-mvc</library>
        <apiPackage>com.zenika.workshop.petstore.api</apiPackage>
        <modelPackage>com.zenika.workshop.petstore.api.model</modelPackage>
        <configOptions>
            <sourceFolder>src/gen/main/java</sourceFolder>
            <dateLibrary>java8-localdatetime</dateLibrary>
            <interfaceOnly>true</interfaceOnly>
        </configOptions>
    </configuration>
</plugin>
```



<!-- .slide: class="page-tp5" -->



<!-- .slide: class="page-questions" -->

<div class="pb"></div>

# LAB 4

## Maven

Time to put some structure to our petshop

- Repackage the project into a Maven project
  - Use the Maven artifacts for our dependencies: libraries in /lib are named *groupId__artifactId__version*
  - JUnit depends on Hamcrest, and is only used for tests. Set up your pom.xml accordingly
  - As a first step, refactor your project as a single Maven module
  - Be sure to respect the Standard Directory Layout ! 

```
├── src
│   ├── main          
│   │   ├── java	    
│   ├── test          
│   │   ├── java	    
│   │   └── resources 
│   └── assembly	    
└── pom.xml
```


You will need to add this property block in your pom.xml in order to specify the Java version for compilation, and your files' encoding :

```xml
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
```

- As a second step, refactor the project to use a parent module and submodules

```
└── petshop-parent
    ├── petshop-model
    ├── petshop-persistence
    ├── petshop-services
    └── petshop-application
```

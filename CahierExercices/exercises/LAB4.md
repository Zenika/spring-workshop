<div class="pb"></div>

# LAB 4

## Maven

Time to put some structure to our petshop

- Repackage the project to build with Maven
  - Use the Maven artifacts for our dependencies: libraries in /lib are named *groupId__artifactId__version*
  - JUnit depends on Hamcrest, and is only used for tests. Set up your pom.xml accordingly

- Set up the project to use modules and submodules modules 

```
.
└── petshop-parent
    ├── petshop-model
    ├── petshop-persistence
    ├── petshop-services
    └── petshop-application
```

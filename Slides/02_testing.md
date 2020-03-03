# Testing

<!-- .slide: class="page-title" -->



## Table of content

<!-- .slide: class="toc" -->

- [Clean code](#/1)
- **[Testing](#/2)**
- [Maven](#/3)
- [Spring Core](#/4)
- [Spring MVC](#/5)
- [Spring Security](#/6)
- [Spring Data](#/7)
- [Spring Batch](#/8)
- [Spring Boot](#/9)



## Why bother testing ?

- Makes the process agile
- Quality of code
- Finds software bugs early
- Facilitates changes and simplifies integration
- Provides documentation
- Debugging process
- Improves design
- Reduces costs



## Different kind of tests
- Unit tests
- Integration tests
- Functional tests
- End-to-end tests
- Acceptance tests
- Performance tests
- Smoke tests



## Different kind of tests: Unit tests

- Testing *individual methods* and functions of the classes, components or modules used by your software.
- Very low level, close to the source of your application
- Easy to automate and run (locally or continuous integration server)
- Tools: JUnit, TestNG, Mockito, AssertJ...



## Different kind of tests: Integration tests

- Verify that different modules or services used by your application *work well together*
- For example, it can be testing the interaction with the database or making sure that microservices work together as expected
- More complicated: require multiple parts of the application to be up and running
- Technical tests
- Tools: JUnit, TestNG, Mockito, AssertJ...



## Different kind of tests: Functional tests

- Focus on the *business requirements* of an application
- Only verify the output of an action, do not check the intermediate states of the system when performing that action
- More thourough than integration tests: verify that the computated values are right
- Tools: Cucumber, SoapUI



## Different kind of tests: End-to-end tests

- Replicates a *user behavior* with the software in a complete application environment
- Verifies that various user flows work as expected (can be as simple as loading a web page or logging in or much more complex scenarios)
- Very useful, but they're expensive: hard to maintain
- Tools: SoapUI, Selenium



## Different kind of tests: Acceptance tests

- Formal tests executed to verify if a system satisfies its *business requirements*
- Require the entire application to be up and running and focus on replicating user behaviors
- Can also go further and measure the performance of the system and reject changes if certain goals are not met



## Different kind of tests: Performance tests

- Check the behaviors of the system under *significant load*
- Non-functional and can have the various form to understand the performance, reliability, stability, and availability of the platform
- Can be costly (infrastructure has to be relevant) but they can help you understand if new changes are going to degrade your system
- Tools: Gatling, JMeter, NeoLoad, LoadRunner



## Different kind of tests: Smoke tests

- Basic tests that check *basic functionality* of the application
- Meant to be quick to execute
- Goal is to ensure the major features of your system are working as expected
- Useful after deployement to check application us up and running
- Tools: Curl, shell scripts



## JUnit

- Most commonly used unit testing framework for Java programming language
- Provides *annotations* to identify test methods
- Provides *assertions* for testing expected results
- Provides *test runners* for running tests
- Well integrated into your UI and your builds (locally or on the continuous integration server)



## JUnit: Annotations

JUnit works with a set of *annotations* that govern the execution of your tests

- **@Test**: This method is a test method.
- **@BeforeEach**: This method should be executed before each @Test
- **@AfterEach**: This method should be executed after each @Test
- **@BeforeAll**: This method should be executed before all @Test
- **@AfterAll**: This method should be executed after all @Test
- **@Disabled**: Used to disable a test class or test method. Use wisely !

- Many more ! **@ParameterizedTest, @RepeatedTest, @TestFactory, @TestTemplate, @TestMethodOrder, @TestInstance, @DisplayName, @DisplayNameGeneration, @Nested, @Tag, @Timeout, @ExtendWith, @RegisterExtension, @TempDir**



## JUnit: Assertions

*Assertions*: A set of JUnit utility methods to use in your tests to check that a piece of code provides with the expected outcome

- **assertEquals** / **assertNotEquals**
- **assertTrue** / **assertFalse**
- **assertNull** / **assertNotNull**
- **assertSame** / **assertNotSame**
- **assertArrayEquals**
- **assertThrows** / **fail**



## JUnit: Test runners

- "Parent" classes that govern how a set of tests is executed and setup the test environment for you
- Specify the runner with the **@RunWith** class annotation
- If you don't specify any, the basic JUnit runner is selected by default
- JUnit provides more specialised runners (eg: Spring runner)
- You can write your own, to add specific behaviours (eg: data set initialisation, embedded broker...)
- Separates the technical boilerplate from the actual testing code



## JUnit: An example

```java
class MyFirstTest {

    private final Calculator calculator;

    @BeforeAll
    void init() {
        calculator = new Calculator();
    }

    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }

    @Test
    void substraction() {
        assertEquals(1, calculator.sub(2, 1));
    }
}
```



<!-- .slide: class="page-tp2" -->



## AssertJ

- Better *assertions* for JUnit
- Fluent style coding
- Type safe
- Easier to use
- Extensible (you can write your own assertions)

```java
assertThat(frodo).isNotEqualTo(sauron);

// chaining string specific assertions
assertThat(frodo.getName()).startsWith("Fro")
                           .endsWith("do")
                           .isEqualToIgnoringCase("frodo");

// collection specific assertions (there are plenty more)
// in the examples below fellowshipOfTheRing is a List<TolkienCharacter>
assertThat(fellowshipOfTheRing).hasSize(9)
                               .contains(frodo, sam)
                               .doesNotContain(sauron);
```



## Mockito

- Framework to use "mock" objects
- Allows you to test objects in isolation to their dependences
- Test failing means the tested class fails ! Not one of its dependences
- Declare mock objets with **@Mock**
- Inject them into the tested instance with **@InjectMocks**
- Program your mocks behavior with the Mockito fluent API: **when(...).then(...)**
- Check the mocks were called as expected with **verify**



## Mockito

```java
public Class MyServiceTest {
    
    @Mock 
    MyRepository repository;

    @InjectMocks 
    MyService service;
    
    @Test
    public void uploadDocument(){
        when(repository.findAll())
            .thenReturn(Collections.emptyList());

        service.process();  // this uses MyRepository.findAll()

        verify(repository, times(1)).findAll();
    }
}
```



<!-- .slide: class="page-tp3" -->



<!-- .slide: class="page-questions" -->

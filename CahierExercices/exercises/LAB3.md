<div class="pb"></div>

# LAB 3 - Unit tests

## Add Kotlin Test dependencies

- Open the *build.gradle.kts* file located at project root
- Copy the following line in the `dependencies` closure :

```
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.3.50")
    testRuntime ("org.junit.jupiter:junit-jupiter-engine:5.0.0")
```

- Check that the *kotlin-test-junit5* dependency version is the same than the gradle kotlin plugin defined earlier in the script.

- IntelliJ will automatically add the *kotlin-test-junit5* libraries to the project

- Since Junit 5, we need to add the following at the end of the build script for tests execution :

```
tasks.withType<Test> {
    useJUnitPlatform {
    }
}
```

## Write your first kotlin test

In LAB4, we need to develop a CircleAreaCalculator. We'll use Test Driven Developement (TDD) and the first thing is : Write a test that fails :)

- Under *src/test/kotlin* create the *com.zenika.lab3* package
- Use the new *Kotlin File/Class* menu, to add a Kotlin `class` named **CircleAreaCalculatorTest** to the previous package
- Create a simple method named `calculate_circle_area` in the class :
```kotlin
fun `calculate circle area`() {

}
```
We'll see more about functions in the next part.
- Annotate the method with `@Test` from **kotlin.test**
- In the function body, call the `fail()` method with a message like *Not implemented yet*

## Run the test

Execute the code by clicking the *Run Test* icon in the left bar of the editor and select *Run CircleAreaCalculatorTest* 

Your test must failed with the following message :
```shell
java.lang.AssertionError: Not implemented yet
```


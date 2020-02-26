<div class="pb"></div>


# LAB 4 - Functions, variables & constants

## Code the test

- Use IntelliJ to rename package *lab3* to *lab4*
- Replace the `fail` call with an `assertEquals`
    - First parameter will be the expected result. For example, 12.56 if radius is 2
    - The second is the call to a function named `area()` with the radius value as parameter

The code should not compile since the `area()` method doesn't exist

## Create the `area()` function

- Under *src/main/kotlin*, create the package *com.zenika.lab4*
- Create the Kotlin file **CircleAreaCalculator**
- Create the constant *PI* avec la valeur *3.14*
- Create the `area()` function which takes an `Int` parameter named *radius*
- Use a simple expression notation for the function body

*Reminder : circle area = PI x R²*

## Run the test

Your test should compile and succeed if you run it 

## Add `main()` function

Now that your implementation of `area()` is correct, we want to add a main to execute the program.

- Create a top level mutable variable of `Int` type named *circleRadius*
- Create the `main()` function
- Print the result of calling the `area()` function with the *circleRadius* variable as parameter
- Now, set another value to the *circleRadius* variable
- Print the result of calling the `area()` function again

## Execute the program

You can now execute the `main()` function. You sould see a result like this for value 2 and 4 :
```shell
12.56
50.24
```

<div class="pb"></div>

# LAB 6 - Control flows : `if`

## Fibonacci

Fibonacci suite is a sequence of integers in which each integer is the sum of the two preceding integers. 
With `F(0)=0` and `F(1)=1` so as : `F(n) = F(n-1) + F(n-2)`

`0  1  1  2  3  5  8  13  21  34  55  89  144  233 …`

## Preparation

 - Create package *com.zenika.lab6* under *src/test/kotlin*
 - Copy **FibonacciTest.kt** in this package

## Implementation of `fibonacci()` function
 
- Open the file **FibonnaciTest.kt**
- Remove the `TODO()` method call and uncomment the assertion in the test method
- Following TDD rules, implement the `fibonacci()` function using recursivity
    1. Create the **Fibonacci.kt** in folder *src/main/kotlin/com/zenika/lab6*  
    2. Add function `fibonacci()` which takes an int parameter and return a int
    3. Implements algorithm adding a test for each case and the `if` keyword to handle conditions

- Create the main method to launch program 
- Call function `fibonacci(10)` from method `main()` and print result on console.


package com.zenika.lab8

fun fibonacci(number: Int): Int {
    var number1 = 0
    var number2 = 1
    for (index in 1..number) {
        val sum = number1 + number2
        number1 = number2
        number2 = sum

    }
    return number1
}


fun main() {
    println("Fibonacci of 10 is ${fibonacci(10)}")
}
package com.zenika.lab7

fun fibonacci(number: Int): Int = when(number) {
    in 0 ..1 -> number
    else -> fibonacci(number - 2) + fibonacci(number - 1)
}

fun main() {
    println("Fibonacci of 10 is ${fibonacci(10)}")
}
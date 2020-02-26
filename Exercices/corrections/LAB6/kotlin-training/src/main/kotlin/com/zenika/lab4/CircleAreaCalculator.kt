package com.zenika.lab4

const val PI = 3.14

var circleRadius = 2

fun area(radius: Int) = PI * radius * radius

fun main() {
    println(area(circleRadius))
    circleRadius = 4
    println(area(circleRadius))
}
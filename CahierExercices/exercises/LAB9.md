<div class="pb"></div>

# LAB 9 - Classes 

We're now going to build the bases of a Tournament board generator for Single-elimination tournament. 
We are using TDD

## The first test class

- Create a package named *com.zenika.tournamentgenerator* under *src/test/kotlin* and  *src/main/kotlin* 
- Create a test class named **TournamentRoundGeneratorTest** in the **service** package


## First iteration to create a round of two players
- Create a test to check that the **TournamentRoundGenerator** service generates a round of one match for two players
- Create the necessary data classes to implement the test in the **domain** package
    - Player with a pseudo property. 
    - Tournament with a name and a list of players. Set a default value for name (eg: Z Tournament)
    - Match of two players.
    - Round with list of matches.
- Implement the **generate()** method that takes a list of 2 players to pass the test

Tips: look at the [List api](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html), you'll surely find method to get a list of players pair

## Add test for a round of 4 players
- Create a second test method that check the **generate()** implementation. You should have a round of two matches for four players.

Tips : you'll probably need to create a method to get a list of players in your test class

## Check the tournament number of players
We limit the number of players to 32 here. So, we need to check that the number of players is always between 2 and 32 and it must be a power of 2
- Create a **TournamentTest** class
- Add the following test methods and implement the algorithm. The verification needs to be done at Tournament initialization and an IllegalStateException should be thrown if conditions are not met
    - `should failed with no players`
    - `should failed with one player`
    - `should failed for not pow 2 number of players`
    - `should failed with number of players greater than 32`

Tips: Kotlin test api offers a `assertFailsWith` to check if an exception is thrown on method call.   
Tips: use **and**, which is equivalent to logical &, to check that the number of players is a power of 2.  
Tips: follow IntelliJ suggestions to replace the `if` condition with a call to a the `check()` method of the Standard Library

```kotlin
(number and number - 1 != 0) // Not power of 2
```

 

<div class="pb"></div>

# LAB 11 : Objects 

## Change the TournamentRoundGenerator declaration
To avoid the creation of multiple TournamentRoundGenerator, we want it to be a singleton
- Use the **object** keyword to change the TournamentRoundGenerator instanciation
- Check that the previous tests are still in success


## Add a `roundName` property to a round
- Create the **RoundTest** class
- Create a test method that check the `roundName` property value for a round of one match

Tips : add a method to generate list of matches to your test class. This method could use the **generatePlayers** that you must move to an util test file. We'll see later how to optimize code for these methods.

For implementation, you need to :
- Add a *name* property to **Round** data class with **RoundName** type
- Add the equivalent of a static method named **from()** to the **RoundName** enum (*companion object*). This method returns the **RoundName** member according to the given number of matches

Tips : use when expression for the **from()** implementation

- The *name* property needs to be initialize with a default value calling the **from()**. 
- Look at the IntelliJ warning to enhance, your code.

- Add test for each possible round name.

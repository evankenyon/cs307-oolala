## Lab Discussion
### Team

Oolala Team 04

### Names
Evan Kenyon
Haseeb Chaudhry
Luis Pereda Amaya 

### Issues in Current Code
* Long methods
* Exception handlers should preserve the original exceptions
* Magic numbers should not be used
* Methods should not be empty
* Classes should not be coupled to too many other Classes
* Control flow statements should not be nested too deeply
* Methods should not be too complex
* Utility classes should not have public constructors
* Abstract classes without fields should be converted to interfaces

#### CommandModel Class
 * parseInput() method is too long and too complex. This method implements a switch statement to convert the user input into commands that we have designed as classes. Professor Duvall told us the switch statement was fine for this project (most likely since we have not learned about factories) but we need to clean up the method still. The method was as short as we could possibly make it, but some of the strings are hard coded and we should think about how this would be implemented if we needed to have inputs in other languages. It is also a very long while loop, so we could look to shorten this a bit. 


#### LogoDisplay and CommandDisplay Class
 * The classes are coupled to too many other classes, likely just because a view class requires too many imports.

 #### TurtleController Class
 * The control flow was too complicated within the checkIfTurtleIDExists() method, so we abstracted one of the layers into a separate method to clear up the readiability. 


### Refactoring Plan

 * What are the code's biggest issues?
The code's biggest issues right now are that the CommandModel was handling a lot of complexity in one method to parse the input. A switch statement is not the cleanest way of dealing with creation for the command classes, but we have not yet been introduced to the idea of factories, so for now a switch should suffice. We do not have a way of handling different languages, which is a large issue at the moment. 

 * Which issues are easy to fix and which are hard?
Handling commands in different languages should be a simple fix using resource bundles. A more difficult fix is handling the parsing of the user input and creating commands. We have one option of simplifying the while loop in the method so it is more readable since the switch is acceptable. We could also look into using a factory class to generate the command classes, but this would require a significant deal of effort. The TurtleController issue was also a very simple fix. 

 * What are good ways to implement the changes "in place"?
A good way to implement the changes we are thinking of making is making a private helper method to abstract some of the complexity from the while loop. We could also handle all of this in a factory class instead. Abstracting into a private helper method was also an easy fix for the TurtleController issue.

### Refactoring Work

 * Command Model parseInput(): 
We chose to hide some of the complexity within the while loop of this method with a private helper method which was clear about the work it would be doing. We also will use a resource bundle to be able to switch between input languages. The biggest alternative to our fix would be using a factory class to handle the complexity of creating classes for each command instead of using the switch case. 


 * TurtleController checkIfTurtleIDExists():
We chose to hide some complexity for the control flow with a private helper method. This helped make the method more readable and the control flow easier to follow.

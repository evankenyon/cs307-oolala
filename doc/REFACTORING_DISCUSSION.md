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


#### LogoDisplay Class
 * The class is coupled to too many other classes, likely just because a view class requires too many imports.

 #### CommandDisplay Class
 * The class is coupled to too many other classes, again likely since a view class requires many imports.


### Refactoring Plan

 * What are the code's biggest issues?


 * Which issues are easy to fix and which are hard?

 * What are good ways to implement the changes "in place"?


### Refactoring Work

 * Issue chosen: Fix and Alternatives


 * Issue chosen: Fix and Alternatives

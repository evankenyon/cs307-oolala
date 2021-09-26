# OOLALA Design Plan
## NAMES
Evan Kenyon

### Team Roles and Responsibilities



### High Level Design
1. When does parsing need to take place and what does it need to start properly?
* Parsing needs to take place after the user clicks enter/submit on a command. 
* In order to start properly, the program needs error catching for bad syntax
* The model also needs a translation from the syntax into model updates

2. What is the result of parsing and who receives it?
* The result of parsing would be a set of instructions to follow
* The model would receive the result of parsing

3. When are errors detected and how are they reported?
* Syntax errors are detected during parsing
* Other errors, such as invalid command usage, would be detected in the model 
* When error is detected, want program to stop there, tell user what happened and how they can fix it

4. What do commands know, when do they know it, and how do they get it?
* Commands know what the user wants to do with the Turtle/s
* They know it once they have received the parsed information from the user
* They get it through user input and then parsing that input

5. How is the GUI updated after a command has completed execution?
* The model handles updating the state of the Turtle/s backend representation, and then
returns the necessary data for the GUI to update (e.g. the turtle's new position).

### CRC Card Classes

This class's purpose or value is to represent a customer's order:
![Order Class CRC Card](order_crc_card.png "Order Class")
 ```
 

### Use Cases 

 * The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user loads a file of commands, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user clicks in the display window to set a new Home location.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user changes the color of the environment's background.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```


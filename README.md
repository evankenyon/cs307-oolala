OOLALA
====

This project is used to create art, designs, and other images, on a computer canvas by making use of turtles (act as pens). The project contains two programs: LOGO Program and L-System Visualizer

LOGO Program: Free Style turtle drawing with multiple turtles active. Customizable such as MS Paint.

L-System Visualizer: Algorithmic turtle drawing to draw Fractal Patterns.

Names: Haseeb Chaudhry, Evan Kenyon, Luis Pereda

### Timeline

Start Date: Wednesday, September 22, 2021

Finish Date: Monday, October 11, 2021

Hours Spent: 75-100 hours

### Primary Roles

Haseeb Chaudhry: L-System backend design and algorithm, TurtleDisplay and TurtleInfo design, Some Backend commands, some JavaDocs

Evan Kenyon: LOGO and L-System frontend design, File System creator, Command Hierarchy and Design, Overall Program Hierarchy

Luis Pereda: LOGO backend design, CSS, JavaFx Tester, JavaDoc, Languages, Command Hierarchy and Design, and Overall Program Hierarchy 

ALL: Refactoring of code and multiple redesigns

### Resources Used
https://stackoverflow.com/ for basic implementation of different JavaFx features
CS307 Course Website Used

### Attributions Used
Found turtle image at 'https://www.freepik.com/vectors/logo'.

### Running the Program

Main class: Class called in Main is LogoDisplay to setup the screen and overall UI as well as create initial backend behaviour

Data files needed: TurtleImage.jpg, Programs for L-System and LOGO .txt files, 

Error handling:

* Handle incorrect input to command line
* Handle incorrect file or no file entered

Test Files: Test files used for project are contained within test package in order to make sure methods within classes are working
especially backend. For further program testing, example code can be uploaded to check whether drawing for turtle are working.

Features implemented:
* LOGO commands for turtle movement
* Choose turtle image, pen color, and pen size
* L-system commands for fractal drawing
* Save current turtle commands/drawing into file
* Load turtle commands/drawing from file
* Run Code in different languages

Extra Feature:
* Added full reset to canvas
* Canvas color option

### Notes/Assumptions

Assumptions or Simplifications:

* Users will input the code in a certain format
* Users will use shown canvas for drawing
* Users will only upload compatible file types for turtle image
and program file input
* Certain boundaries will be expected such as no turtle with negative names, or movement to decimals
* User will save file before launching another as current work will be deleted to perform such actions

Interesting data files:

* Grid file
* Dashed line file

Known Bugs:

* Must use fullscreen for proper frontend layout
* Unstable build of L-system
* Improper UI Placement
* Error in Uploading L-system File
* Run Previous Command Bug
* Input of L-system buggy


### Impressions

* A lot of work and functionality to implement within the given timespan especially with the work from other classes
* Many items that have to be implemented have to be self-taught especially in the frontend resulting in longer work times
shows up as minimal code writing
* LOGO and L-System were an interesting challenge to code. Overall hierarchy and design was fun to implement when in the flow
* Darwin presented more of a divergence from the other two coding assignments requiring time which we didn't have
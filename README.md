# Assignment 3: GUI TicTacToe and Numerical TicTacToe

This program simulates the TicTacToe game as well as the newly formed Numerical TicTacToe and 
allows 2 users to play on a shared computer. The rules for the game are the same as the original
game and the format is on a 3x3 grid.

For more information on the game rules, visit this website: [Numerical TicTacToe](https://mathequalslove.net/numerical-tic-tac-toe) &
[TicTacToe](https://www.thesprucecrafts.com/tic-tac-toe-game-rules-412170)

## Description

This project focuses on using Object Oriented (OO) concepts to create an effective and user friendly GUI
TicTacToe game for 2 users. Some of the major OO concepts that were used in this program include 
encapsulation, aggregation, accessors/mutators, message passing, inheritance, polymorphism, interfaces
and effective coupling/cohesion. This project contains a README file, a build.gradle file, checkstyle
page, an assets folder, and 15 classes in total.

## Getting Started

### Dependencies

* Latest version of gradle must be installed
* Latest version of Java JDK must be installed
* Works on both MacOS and Windows

### Executing program

In order to run this program please have the latest version of gradle installed on your computer, 
after you have done so follow the steps below in order to execute the program:

* First cd into the project folder A3 on your computer
* Then use the following gradle command ```gradle build``` to build the files:
* if this throws a checkstyle error or test case error by any chance and you want to run the program, 
execute using the following condition ```gradle build -x checkstylemain```

* After it displays the message "build successful", use the following command to run the program 
```gradle run```

* This command will give you an executable file location such as ```java -cp build/classes/java/main game.TextUI``` and it will give a executable JAR file like
```java -jar build/libs/A3.jar```. The file location is to execute the CMD game version of tictactoe, and the JAR is to execute the GUI version. Copy one of these lines
 of code and paste it into your command prompt and press enter. This will execute the program and you should now be able to play TicTacToe.

## Limitations

Somethings that aren't done in this project is the incorperation of indepth file validation, for instance this program does not check to see
if the player username matches with the file player username, and all it does is overwrites the current player username with the one in the file.

## Author Information

* Name: *Jyothir Krishnan*
* E-mail Address: *jkrishna@uoguelph.ca*
* Student ID: *1177917*

## Development History

* Day 6
    * Finalizing the program
    * Created TextUI for CMD version of game
    * Cleaned up the program
    * Included javadoc commenting
    * Completed README file
* Day 5
    * Reworked organization of the UI and added extra features
    * Finished majority of both games, starting on file save/load
* Day 4
    * Started setting up Numerical Tictactoe game UI
    * Set up TicTacToe game UI and finished all wincases
* Day 3    
    * Started creating game pages for the two games
    * Organized layout for hub page
    * Re-worked OOP principles
* Day 2
    * Incorperated implementation hiding
    * Re-worked OOP principles
    * Got GUI working for main hub page
* Day 1
    * Started writing game hub UI code
    * Set up folder and package structure  

## Acknowledgments

Inspiration of readMe file.
* [simple-readme](https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)




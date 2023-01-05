package game;
import java.util.Scanner;

import tictactoe.TicTacToeGame;

/**
 * The TextUI class is the main class that runs the CMD version of the tictactoe game
 * It has very basic functionallities and cannot save or load
 */
public class TextUI{
    private TicTacToeGame game = new TicTacToeGame(3, 3);    
    private Scanner userInput = new Scanner(System.in);
    private int acrossVal;
    private int downVal; 

    /**
     * This function is the main function of the program. It creates a new TextUI object and calls the
     * runGame() function
     */
    public static void main(String[] args){
        TextUI cmdGame = new TextUI();        
        System.out.println("Welcome to TicTacToe CMD Version!");             
        cmdGame.runGame();
    }

    private void runGame(){
        boolean gameOver = false;      

        while(!gameOver){
            displayBoard();
            System.out.println("Turn = " + game.toString());
            promptValues();
            
            if(!game.takeTurn(acrossVal, downVal, game.toString())){
                System.out.println("Position already taken! Please enter an empty position!");
            }

            if(game.isDone()){
                gameOver = true;
                displayWinner();
            }
        }
    }

    private void promptValues(){
        boolean invalidInput = true;

        while(invalidInput){
            System.out.print("Enter across value = ");
            acrossVal = userInput.nextInt();            

            if(acrossVal >= 1 && acrossVal <= 3){
                invalidInput = false;
            }else{
                System.out.println("Please enter valid across value! (1-3)");
            }
        }
        
        invalidInput = true;
        while(invalidInput){
            System.out.print("Enter down value = ");
            downVal = userInput.nextInt();
            
            if(downVal >= 1 && downVal <= 3){
                invalidInput = false;
            }else{
                System.out.println("Please enter valid down value! (1-3)");
            }
        }
    }

    private void displayBoard(){        
        int rowCounter = 0;
        String currentCell = game.getNextValue();

        System.out.println(" -------------");
        while(currentCell != null){
            System.out.print(" | " + currentCell);
            rowCounter++;
            if(rowCounter == 3){
                System.out.println(" |");
                System.out.println(" -------------");
                rowCounter = 0;
            }
            currentCell = game.getNextValue();
        }        
    }

    private void displayWinner(){
        int winner = game.getWinner();
        displayBoard();

        if(winner == 1){
            System.out.println("Winner = X");
        }else if(winner == 2){
            System.out.println("Winner = O");
        }else{
            System.out.println("Tie Game!");
        }
    }
    
}

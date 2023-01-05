package tictactoe;

/**
 * This class is the game logic for the TicTacToe game
 * @author Jyothir Krishnan
 */
public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{
    private String turn = "X";
    private int gameCounter = 0;
    private int winner = -1;
    private Boolean validMove = true;   
    private StringBuilder build = new StringBuilder();

    /**
     * This is the constructor for the TicTacToeGame class. It is calling the constructor of the
     * superclass, BoardGame, and passing in the number of columns and rows. It is also creating a new
     * TicTacToeGrid object and setting it to the grid variable.
     * @param cols number of columns in grid
     * @param rows number of rows in grid
     */    
    public TicTacToeGame(int cols, int rows){
        super(cols, rows);        
        setGrid(new TicTacToeGrid(cols, rows));
    }

    private void changeTurn(){
        if(turn.equals("X")){
            turn = "O";
        }else{
            turn = "X";
        }
    }        

    private void setWinner(){        
        if(checkHorizontalWin() || checkVerticalWin() 
           || checkDiagonalWin() || checkReverseDiagonalWin()){
            if(turn == "X"){
                winner = 2;
            }else{
                winner = 1;
            }
            
            return;
        }else{
            winner = -1;
        }

        if(setTie()){
            winner = 0;            
        }
    }

    private boolean setTie(){
        gameCounter++;
        if(gameCounter > 8){            
            return true;
        }  

        return false;
    }

    private boolean checkHorizontalWin(){
        int winCounterX = 0;
        int winCounterO = 0;
        
        for (int y = 1; y <= getHeight(); y++){
            for (int x = 1; x <= getWidth(); x++){ 
                if(getCell(y, x).equals("X")){
                    winCounterX++;
                }
                if(getCell(y, x).equals("O")){
                    winCounterO++;
                }
            }

            if(winCounterX == 3 || winCounterO == 3){
                return true;
            }
            
            winCounterX = 0;
            winCounterO = 0;
        }
        
        return false;
    }

    private boolean checkVerticalWin(){
        int winCounterX = 0;
        int winCounterO = 0;

        for (int y = 1; y <= getHeight(); y++){
            for (int x = 1; x <= getWidth(); x++){ 
                if(getCell(x, y).equals("X")){
                    winCounterX++;
                }
                if(getCell(x, y).equals("O")){
                    winCounterO++;
                }
            }         
            if(winCounterX == 3 || winCounterO == 3){
                return true;
            }

            winCounterX = 0;
            winCounterO = 0;
        }

        return false;
    }

    private boolean checkDiagonalWin(){
        if((getCell(1, 1).equals("X") && getCell(2, 2).equals("X") && getCell(3, 3).equals("X")) 
        || (getCell(1, 1).equals("O") && getCell(2, 2).equals("O") && getCell(3, 3).equals("O"))){
            return true;
        }
        return false;
    }

    private boolean checkReverseDiagonalWin(){
        if((getCell(1, 3).equals("X") && getCell(2, 2).equals("X") && getCell(3, 1).equals("X")) 
         || (getCell(1, 3).equals("O") && getCell(2, 2).equals("O") && getCell(3, 1).equals("O"))){
            return true;
        }
        return false;
    }

    /**
     * This function resets the game counter to 0 and changes the turn to 
     * X if the turn is currently O
     */
    protected void reset(){
        gameCounter = 0;
        if(turn == "O"){
            changeTurn();
        }
    }

    /* Implementation Methods */        

    /**
     * It takes the current state of the game and saves it to a string
     * @return A string representation of the board.
     */
    @Override
    public String getStringToSave() {
        build.delete(0, build.length());
        build.append(turn + "\n");
        for (int y = 1; y <= getHeight(); y++){
            for (int x = 1; x <= getWidth(); x++){ 
                if(getCell(y, x) == "X"){
                    build.append("X,");
                }else if(getCell(y, x) == "O"){
                    build.append("O,");
                }else{
                    build.append(",");
                }       
                
                if(x == getWidth()){
                    build.deleteCharAt(build.length()-1);
                }
            }
            build.append("\n");
        }

        return build.toString();
    }

    /**
     * This function is called when the user loads a game from a file. It takes the 
     * string from the file and parses it into the board
     * @param toLoad the string that is being loaded from the file
     */
    @Override
    public void loadSavedString(String toLoad){
        TicTacToeGrid myGrid = (TicTacToeGrid)getGrid();
        try{
            myGrid.parseStringIntoBoard(toLoad);            
            gameCounter = myGrid.getMoves();   
            turn = myGrid.getFileTurn();     
        }catch(Exception e){
            gameCounter--;
            TicTacToeView.errorMessage("Error with file format! Try a different file!");
        }
                
    }

    /**
     * This function takes in the coordinates of the cell the user wants to place their piece in,
     * and the piece itself. It then checks to see if the cell is empty, and if it is, it places the
     * marker in the cell and changes the turn
     * 
     * @param across the x coordinate of the cell
     * @param down the y coordinate of the cell
     * @param input The value to be placed in the cell.
     * @return boolean return, true for successful placement
     */
    @Override
    public boolean takeTurn(int across, int down, String input) {            
        if(getCell(across, down).equals("X") || getCell(across, down).equals("O")){            
            validMove = false;
            return false;
        }

        validMove = true;
        setValue(across, down, input);
        changeTurn();
        return true;
    }

    /**
     * This function takes in three integers, across, down, and input, and returns a boolean.     
     * @param across number of moves across
     * @param down number of moves down
     * @param input The number that the user has entered.
     */
    @Override
    public boolean takeTurn(int across, int down, int input) {        
        return false;
    }

    /**
     * If the move is valid, set the winner, if the winner is not -1, reset the board and change the
     * turn     
     * @return true if there has been a win or tie case
     */
    @Override
    public boolean isDone() {
        if(validMove){
            setWinner();      
            
            if(winner != -1){
                reset();
                changeTurn();
                return true;
            }
        }     
        return false;
    }

    /**
     * This function returns the winner of the game     
     * @return The winner of the game.
     */
    @Override
    public int getWinner() {        
        return winner;
    }

    /**
     * It returns a string that describes the current state of the game.     
     * @return The game state message.
     */
    @Override
    public String getGameStateMessage() {        
        return null;
    }

    /**
     * Gives the string representation of the current turn and is a warpper method    
     * @return The turn variable is being returned.
     */
    @Override
    public String toString(){
        return turn;
    }
}

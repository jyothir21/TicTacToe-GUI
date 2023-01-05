package numtictactoe;
/**
 * This class is the game logic for the Numerical TicTacToe game
 * @author Jyothir Krishnan
 */
public class NumTTTGame extends boardgame.BoardGame implements boardgame.Saveable{
    private String turn = "O";
    private int gameCounter = 0;
    private int winner = -1;
    private Boolean validMove = true;    
    private StringBuilder build = new StringBuilder();

    /**
     * This is the constructor for the NumTTTGame class. It is calling the constructor of the
     * superclass, BoardGame, and passing in the number of columns and rows. It is also creating a new
     * NumTTTGrid object and setting it to the grid variable.
     * @param cols number of columns in grid
     * @param rows number of rows in grid
     */    
    public NumTTTGame(int cols, int rows) {
        super(cols, rows);
        setGrid(new NumTTTGrid(cols, rows));
    }

    private void changeTurn(){
        if(turn.equals("O")){
            turn = "E";
        }else{
            turn = "O";
        }
    }   

    private void setWinner(){
        if(checkHorizontalWin() || checkVerticalWin() 
           || checkDiagonalWin() || checkReverseDiagonalWin()){
            if(turn == "O"){
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
        int rowSum;
        for (int y = 1; y <= getHeight(); y++){
            rowSum = 0;

            for (int x = 1; x <= getWidth(); x++){ 
                if(isNumber(getCell(y, x))){
                    rowSum += Integer.parseInt(getCell(y, x));
                }else{
                    rowSum = 0;
                }
            }

            if(rowSum == 15){
                return true;
            }
        }

        return false;
    }

    private boolean checkVerticalWin(){
        int colSum;
        for (int y = 1; y <= getHeight(); y++){
            colSum = 0;

            for (int x = 1; x <= getWidth(); x++){ 
                if(isNumber(getCell(x, y))){
                    colSum += Integer.parseInt(getCell(x, y));
                }else{
                    colSum = 0;
                }
            }

            if(colSum == 15){
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonalWin(){
        int diaSum = 0;

        for (int i = 1; i <= getHeight(); i++){
            if(isNumber(getCell(i, i))){
                diaSum += Integer.parseInt(getCell(i, i));
            }else{
                diaSum = 0;
            }
        }
        
        if(diaSum == 15){
            return true;
        }

        return false;
    }

    private boolean checkReverseDiagonalWin(){
        int revDiaSum = 0;
        int acrossCounter = 1;
        
        for (int i = getHeight(); i >= 1; i--){
            if(isNumber(getCell(acrossCounter, i))){
                revDiaSum += Integer.parseInt(getCell(acrossCounter, i));
            }else{
                revDiaSum = 0;                
            }
            acrossCounter++;
        }
        
        if(revDiaSum == 15){
            return true;
        }

        return false;
    }

    /**
     * This function resets the game counter to 0 and changes the turn to
     * E if the turn is currently O
     */
    protected void reset(){
        gameCounter = 0;
        if(turn == "E"){
            changeTurn();
        }
        return;
    }

    /**
     * The function isNumber() takes a string as an argument and 
     * returns true if the string is a number and false if it is not
     * 
     * @param str The string to be checked
     * @return boolean value stating if its a number or not
     */
    public boolean isNumber(String str) { 
        try {  
            Integer.parseInt(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
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
                if(isNumber(getCell(y, x))){
                    build.append(getCell(y, x) + ",");
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
    public void loadSavedString(String toLoad) {
        NumTTTGrid myGrid = (NumTTTGrid)getGrid();
        try{
            myGrid.parseStringIntoBoard(toLoad);            
            gameCounter = myGrid.getMoves();   
            turn = myGrid.getFileTurn();              
        }catch(Exception e){
            gameCounter--;
            NumTTTView.errorMessage("Error with file format! Try a different file!");
        }
    }

    /**
     * This function takes in the coordinates of the player's move and the input string, and returns a
     * boolean value
     * @param across number of moves across
     * @param down number of moves down
     * @param input string to place in coordinate
     */
    @Override
    public boolean takeTurn(int across, int down, String input) {        
        return false;
    }

    /**
     * If the cell is a number, then the move is invalid and the function returns false. Otherwise, the
     * move is valid, the value is set, and the turn is changed
     * 
     * @param across the x coordinate of the cell
     * @param down the y of the cell
     * @param input The number that the player wants to place on the board.
     * @return boolean return, true for successful placement
     */
    @Override
    public boolean takeTurn(int across, int down, int input) {
        if(isNumber(getCell(across, down))){            
            validMove = false;
            return false;
        }

        validMove = true;
        setValue(across, down, input);
        changeTurn();
        return true;
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

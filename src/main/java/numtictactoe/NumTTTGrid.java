package numtictactoe;


/**
 * This class helps managing the game board grid, and can give a string representation of the grid
 * which is useful for file saving/loading
 * @author Jyothir Krishnan
 */
public class NumTTTGrid extends boardgame.Grid{
    private int moves; 
    private String fileTurn;

    /**
     * Calling the constructor of the super class.
     */ 
    public NumTTTGrid(int wide, int tall) {
        super(wide, tall);               
    }
    
    /**
     * This function sets the number of moves made by the player to the value of the parameter     
     * @param movesMade The number of moves made by the player in the file
     */
    public void setMoves(int movesMade){
        moves = movesMade;
    }

    /**
     * This function returns the number of moves the player has made from the file
     * @return The number of moves minus 1.
     */
    public int getMoves(){
        return moves-1;
    }

    /**
     * This function sets the fileTurn variable to the turn variable
     * @param turn The player turn of the game.
     */
    public void setFileTurn(String turn){
        fileTurn = turn;
    }

    /**
     * This function returns the fileTurn variable
     * @return The fileTurn variable is being returned.
     */
    public String getFileTurn(){
        return fileTurn;
    }

    /**
     * The function isNumber() takes a string as an argument and 
     * returns true if the string is a number and false if it is not
     * 
     * @param str The string to be checked
     * @return boolean value stating if its a number or not
     */
    private boolean isNumber(String str) { 
        try {  
            Integer.parseInt(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }
    }
    
    /**
     * It takes a string, splits it into an array, and then assigns the values of the array to the
     * game board for the GUI
     * @param toParse String to be parsed
     */
    public void parseStringIntoBoard(String toParse) throws Exception{
        int j = 1;        
        String[] parsedString = toParse.split(",");
        setMoves(0);
        setFileTurn(parsedString[0]);

        if(parsedString.length == 10){                   
            for(int x = 1; x <= getWidth(); x++){
                for(int y = 1; y <= getHeight(); y++){                
                    if(isNumber(parsedString[j])){
                        moves++;
                        setValue(x, y, Integer.parseInt(parsedString[j]));  
                    }
                    setValue(x, y, parsedString[j]);  
                    j++;
                }
            }
        }else{
            throw new Exception();
        }           
    }
}

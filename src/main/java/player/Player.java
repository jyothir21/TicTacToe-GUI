package player;

/**
 * Is responsible for saving and loading the user profile as well as setting player
 * information and statistics
 * @author Jyothir Krishnan
 */
public class Player implements boardgame.Saveable {
    private String playerName;
    private int wins;
    private int losses;
    private int gamesPlayed;

    /**
     * Calling the constructor of the super class.
     */ 
    public Player(){
        super();
    }

    /**
     * This function sets the player's name to the name that is passed in as a parameter     
     * @param name The name of the player.
     */
    public void setPlayerName(String name){
        playerName = name;
    }

    /**
     * This function returns the player's name     
     * @return The playerName variable is being returned.
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * This function takes in an integer and adds it to the current value of the wins variable     
     * @param winNum The number of wins to add to the player's total.
     */
    public void setPlayerWins(int winNum){
        wins += winNum;
    }

   /**
    * This function returns the number of wins the player has    
    * @return The number of wins the player has.
    */
    public int getPlayerWins(){
        return wins;
    }

    /**
     * This function takes in an integer and adds it to the losses variable     
     * @param lossNum The number of losses to add to the player's total.
     */
    public void setPlayerLosses(int lossNum){
        losses += lossNum;
    }

    /**
     * This function returns the number of losses the player has
     * @return The number of losses the player has.
     */
    public int getPlayerLosses(){
        return losses;
    }

    /**
     * This function takes in an integer and adds it to the gamesPlayed variable     
     * @param game The number of games played.
     */
    public void setGamesPlayed(int game){
        gamesPlayed += game;
    }

    /**
     * This function returns the number of games played by the player     
     * @return The number of games played.
     */
    public int getGamesPlayed(){
        return gamesPlayed;
    }

    /**
     * This function is called when player one wins. It increments the player one wins and games played
     * by one, and increments player two losses and games played by one
     * 
     * @param p1 player 1 details/object
     * @param p2 player 2 details/object
     */
    public static void setPlayer1WinSenario(Player p1, Player p2){
        p1.setPlayerWins(1);                
        p1.setGamesPlayed(1);
        System.out.println("Player one = " + p1.getStringToSave());
        
        p2.setPlayerLosses(1);
        p2.setGamesPlayed(1);
        System.out.println("Player two = " + p2.getStringToSave());
    }

    /**
     * This function is called when player two wins. It increments the player two wins and games played
     * by one, and increments player ones losses and games played by one
     * 
     * @param p1 player 1 details/object
     * @param p2 player 2 details/object
     */
    public static void setPlayer2WinSenario(Player p1, Player p2){
        p2.setPlayerWins(1);
        p1.setPlayerLosses(1);
        p1.setGamesPlayed(1);
        p2.setGamesPlayed(1);
    }

    /**
     * This function sets the games played for both players to 1
     * 
     * @param p1 player 1 details/object
     * @param p2 player 2 details/object
     */
    public static void setTieScenario(Player p1, Player p2){
        p1.setGamesPlayed(1);
        p2.setGamesPlayed(1);
    }

    /**
     * This function resets the wins, losses, and gamesPlayed variables of a Player object to 0     
     * @param p The player to reset
     */
    public static void resetPlayer(Player p){
        p.wins = 0;
        p.losses = 0;
        p.gamesPlayed = 0;
    }

    /* Interface Implementation */

    /**
     * The function takes the player's name, wins, losses, and games played and returns a string that
     * is formatted to be saved in a file
     * 
     * @return The player's name, wins, losses, and games played as a string
     */
    @Override
    public String getStringToSave(){
        return getPlayerName() + "," + getPlayerWins() + "," + getPlayerLosses()
        + "," + getGamesPlayed(); //need to modify later
    }

    /**
     * This function takes a string, splits it into an array of strings, and then sets the player's
     * name, wins, losses, and games played to the values in the array
     * 
     * @param toLoad the string loaded from the file
     */
    @Override
    public void loadSavedString(String toLoad){
        String[] parts = toLoad.split(",");        
        setPlayerName(parts[0]);
        setPlayerWins(Integer.valueOf(parts[1]));
        setPlayerLosses(Integer.valueOf(parts[2]));
        setGamesPlayed(Integer.valueOf(parts[3]));
    }
}

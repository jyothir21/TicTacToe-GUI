package tictactoe;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import boardgame.ui.PositionAwareButton;
import game.GameUI;
import player.Player;
import saveload.DataSaveable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;


/**
 * This class is a view class that creates a GUI for the TicTacToe game
 * @author Jyothir Krishnan
 */
public class TicTacToeView extends JPanel{
    private GameUI rootHub;
    private TicTacToeGame game;    
    private PositionAwareButton[][] buttonPos;
    private JLabel messageLabel;    
    private JLabel turnDisplay;
    private Player player1;
    private Player player2;        

    /**
     * Constructor of this class, which creates a new TicTacToeView object and it sets up te GUI
     * for the TicTacToe game arranging all the panels in the corresponding areas of the screen     
     */
    public TicTacToeView(int width, int length, GameUI frame, String p1, String p2){
        super();
        rootHub = frame;
        
        setLayout(new BorderLayout(0,10));        
        setGameController(new TicTacToeGame(width, length));
        setPlayerController(new Player(), new Player());                        

        loadPlayerProfilesOption(p1,p2);

        messageLabel = new JLabel("Welcome to TicTacToe", SwingConstants.CENTER);
        turnDisplay = new JLabel("Turn = " + game +  " (" + player1.getPlayerName() + ")", SwingConstants.CENTER); 

        add(messageLabel, BorderLayout.NORTH);     
        add(optionButtonPanel(), BorderLayout.SOUTH);
        add(centerPanel(width,length), BorderLayout.CENTER);     
        rootHub.setJMenuBar(createMenuBar());      
    }

    /**
     * This function sets the game controller to the controller passed in as a parameter     
     * @param controller The controller object that will be used to control the game.
     */
    public void setGameController(TicTacToeGame controller){
        this.game = controller;
    }   

    /**
     * This function sets the player controller for the game     
     * @param p1 The first player
     * @param p2 The second player
     */
    public void setPlayerController(Player p1, Player p2){
        this.player1 = p1;
        this.player2 = p2;
    }
        
    private void changeDisplayTurn(){
        if(game.toString() == "X"){
            turnDisplay.setText("Turn = " + game + " (" + player1.getPlayerName() + ")");
        }else{
            turnDisplay.setText("Turn = " + game + " (" + player2.getPlayerName() + ")");
        }
    }

    private void loadPlayerProfilesOption(String p1, String p2){        
        int option;

        option = JOptionPane.showConfirmDialog(null, "Do you want to load player profiles?",
        "Load Player Profile", JOptionPane.YES_NO_OPTION);

        if(option == JOptionPane.YES_OPTION){            
            try{
                DataSaveable.load(player1, chooseFileToLoad(1));    
            }catch(RuntimeException e){                
                player1.setPlayerName(p1);            
                Player.resetPlayer(player1);
                TicTacToeView.errorMessage("Player Profile Format Incorrect, new profile was created!");
            }

            try{
                DataSaveable.load(player2, chooseFileToLoad(2));        
            }catch(RuntimeException e){                
                player2.setPlayerName(p2);                
                Player.resetPlayer(player2);
                TicTacToeView.errorMessage("Player Profile Format Incorrect, new profile was created!");
            }
        }else{
            player1.setPlayerName(p1);            
            player2.setPlayerName(p2);                
        }
    }

    private void savePlayerProfilesOption(){        
        try{
            DataSaveable.save(player1, chooseFileToSave(1));    
        }catch(RuntimeException e){                                           
            JOptionPane.showMessageDialog(null, "Player 1 Data was not saved!",
        "Not Saved", JOptionPane.INFORMATION_MESSAGE);
        }

        try{
            DataSaveable.save(player2, chooseFileToSave(2));        
        }catch(RuntimeException e){                                              
            JOptionPane.showMessageDialog(null, "Player 2 Data was not saved!",
        "Not Saved", JOptionPane.INFORMATION_MESSAGE);             
        }        
    }

    private String chooseFileToLoad(int player){              
        JFileChooser fs = new JFileChooser("./assets");        
        String path = null;

        if(player == 1){
            fs.setDialogTitle("Load File for Player 1");
        }else{
            fs.setDialogTitle("Load File for Player 2");
        }
        fs.setFileSelectionMode(JFileChooser.OPEN_DIALOG);        
        
        if(fs.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){            
            path = fs.getSelectedFile().getPath();
        }

        return path;
    }

    private String chooseFileToSave(int player){
        JFileChooser fs = new JFileChooser("./assets");              
        String path = null;

        if(player == 1){
            fs.setDialogTitle("Save File for " + player1.getPlayerName());
            fs.setSelectedFile(new File(player1.getPlayerName() + ".csv"));        
        }else{
            fs.setDialogTitle("Save File for " + player2.getPlayerName());
            fs.setSelectedFile(new File(player2.getPlayerName() + ".csv"));        
        }

        fs.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        
        if(fs.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){            
            path = fs.getSelectedFile().getPath();
        }

        return path;     
    }

    /* Panels */
    private JPanel centerPanel(int width, int length){
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new GridLayout(1,3));
        // cPanel.add(new JLabel("Turn: " + game, SwingConstants.CENTER));
        cPanel.add(turnDisplay);
        cPanel.add(makeButtonGrid(length, width));
        cPanel.add(new JLabel());
        
        return cPanel;
    }

    private JPanel optionButtonPanel(){
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,10,5,10);
        optionPanel.add(makeResetButton(), c);
        optionPanel.add(makeHubButton(), c);

        return optionPanel;
    }    

    private JMenuBar createMenuBar(){
        JMenuBar menuOptionBar = new JMenuBar();                
        menuOptionBar.add(makeMenuBar());
        return menuOptionBar;
    }
    
    private JMenu makeMenuBar(){
        JMenu menu = new JMenu("Menu Options");
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem load = new JMenuItem("Load Game");

        menu.add(save);
        menu.add(load);
        
        save.addActionListener(e->saveGameToFile());
        load.addActionListener(e->loadGameFromFile());

        return menu;
    }

    private JPanel makeButtonGrid(int rows, int cols){
        JPanel panel = new JPanel();
        buttonPos = new PositionAwareButton[rows][cols];
        panel.setLayout(new GridLayout(cols, rows, 15, 15));

        for(int i = 0; i < cols; i++){
            for(int j = 0; j < rows; j++){
                buttonPos[i][j] = new PositionAwareButton();
                buttonPos[i][j].setAcross(i+1);
                buttonPos[i][j].setDown(j+1);                
                buttonPos[i][j].addActionListener(e->{
                                            changeString(e); 
                                            checkGameState();
                                            });
                
                panel.add(buttonPos[i][j]);
            }
        }

        return panel;
    }

    /* Buttons */
    private JButton makeResetButton(){
        JButton button = new JButton("Reset");
        button.addActionListener(e->newGame());
        return button;
    }

    private JButton makeHubButton(){
        JButton button = new JButton("Hub");
        button.addActionListener(e->goHub());
        return button;
    }

    /* Action Listners */
    
    /**
     * It starts a new game.
     */
    protected void newGame(){
        game.newGame();
        resetGridView();
    } 

    /**
     * It removes the menu bar from the current frame and then restarts the program
     */
    protected void goHub(){
        rootHub.setJMenuBar(null);
        rootHub.restart();
    }

    /**
     * This function resets the game board to its original state (blank)
     */
    protected void resetGridView(){        
        for (int i = 0; i < game.getHeight(); i++){
            for (int j = 0; j < game.getWidth(); j++){  
                buttonPos[i][j].setText(game.getCell(buttonPos[i][j].getAcross(),
                                                     buttonPos[i][j].getDown())); 
            }
        }
        game.reset();
        changeDisplayTurn();
    }

    /**
     * It takes the game object and updates the view of the game from the file being read
     */
    protected void updateView(){        
        for (int i = 0; i < game.getHeight(); i++){
            for (int j = 0; j < game.getWidth(); j++){  
                buttonPos[i][j].setText(game.getCell(buttonPos[i][j].getAcross(),
                                                     buttonPos[i][j].getDown())); 
            }
        }

        if(game.toString().equals("O")){
            changeDisplayTurn();
        }
    }

    /**
     * If the game's takeTurn function returns true, then the button's text is updated with players
     * piece at the position they clicked     
     * @param e the action event that is triggered when the button is clicked
     */
    protected void changeString(ActionEvent e){
        PositionAwareButton clicked  = ((PositionAwareButton)(e.getSource()));
        
        if(game.takeTurn(clicked.getAcross(), clicked.getDown(), game.toString())){
            clicked.setText(game.getCell(clicked.getAcross(), clicked.getDown()));            
            changeDisplayTurn();
        }                   
    }
    
    private void checkGameState(){
        int selection = 0;
        int save;        

        if(game.isDone()){
            selection = gameOverDialogueBox(selection);
            
            if(selection == JOptionPane.NO_OPTION){
                save = JOptionPane.showConfirmDialog(null, "Do you want to save player profiles?",
                 "Save Player Profiles", JOptionPane.YES_NO_OPTION);

                if(save == JOptionPane.YES_OPTION){
                    savePlayerProfilesOption();
                }

                goHub();
            }else{
                newGame();
                changeDisplayTurn();
            }
        }
    }

    private int gameOverDialogueBox(int selection){
        String winner = "X (" + player1.getPlayerName() + ") ";        

        if(game.getWinner() != 0){
            if(game.getWinner() == 2){
                winner = "O (" + player2.getPlayerName() + ") ";
                Player.setPlayer2WinSenario(player1, player2);
            }else{
                Player.setPlayer1WinSenario(player1, player2);
            }

            selection = JOptionPane.showConfirmDialog(null, "Player "
            + winner + "Won! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        }else{
            selection = JOptionPane.showConfirmDialog(null,
            "Tie game! Do you want to play again?", "Game Over",  JOptionPane.YES_NO_OPTION);             
            Player.setTieScenario(player1, player2);
        }

        return selection;
    }

    private void saveGameToFile(){
        JFileChooser fs = new JFileChooser("./assets");              
        String path = null;
        
        fs.setDialogTitle("Save Game");
        fs.setSelectedFile(new File("board.csv"));                

        fs.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        
        if(fs.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){            
            path = fs.getSelectedFile().getPath();
            DataSaveable.save(game, path);
        }           
    }

    private void loadGameFromFile(){
        JFileChooser fs = new JFileChooser("./assets");              
        String path = null;
        
        fs.setDialogTitle("Load Game");              
        fs.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        
        if(fs.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){            
            path = fs.getSelectedFile().getPath();
            DataSaveable.load(game, path);
            updateView();
            checkGameState();
        }   
    }

    /**
     * It displays a message dialog box with the given error message
     * @param msg The message to be displayed.
     */
    public static void errorMessage(String msg){        
        JOptionPane.showMessageDialog(null, msg,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

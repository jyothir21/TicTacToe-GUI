package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import player.PlayerInputScreen;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.Font;

/**
 * The GameUI class is the main class that creates the main window of the application. It has a
 * container that holds the main panel and buttons to navigate to the other screens with games.
 * @author Jyothir Krishnan
 */
public class GameUI extends JFrame{
    public static final int WIDTH = 500;
    public static final int LENGTH = 300;
    
    private Container hubContentPane;

    /**
     * The constructor for the GameUI class. It calls the super constructor, which is the constructor
     * for the JFrame class. It then calls the setWindowSize() and setWindowContainer() methods.
     */     
    public GameUI(){
        super();
        setWindowSize();
        setWindowContainer();    
    }

    private void setWindowSize(){
        setSize(WIDTH, LENGTH);
        setTitle("Game Suite Application Hub");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setWindowContainer(){
        hubContentPane = getContentPane();
        
        hubContentPane.setLayout(new BorderLayout());        
        hubContentPane.add(createCenterPanel(), BorderLayout.CENTER);              
    }

    /**
     * It removes all the components from the main JPanel, then refreshs the home screen
     */
    public void restart(){
        hubContentPane.removeAll();
        setWindowContainer();    
        getContentPane().repaint();
        getContentPane().revalidate();    
    }

    /* Panels */   
    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0,1,0,5));
        centerPanel.add(homeMessage());
        centerPanel.add(createButtonGridPanel());        

        return centerPanel;
    }

    private JPanel createButtonGridPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weighty = 1;
        c.gridx = 0;

        buttonPanel.add(makeTicTacToeButton(),c);
        buttonPanel.add(makeNumTicTacToeButton(),c); 
        buttonPanel.add(makeExitButton(),c);        
        return buttonPanel;
    }

    private JLabel homeMessage(){
        JLabel welcomeMsg = new JLabel("Welcome to the Game Hub!", SwingConstants.CENTER);
        welcomeMsg.setFont(new Font("Serif", Font.BOLD, 30));         
        return welcomeMsg;
    }

    /* Buttons */
    private JButton makeTicTacToeButton(){
        JButton button = new JButton("Play TicTacToe");
        button.addActionListener(e->playerInputScreen());        
        return button;
    }

    private JButton makeNumTicTacToeButton(){
        JButton button = new JButton("Play Numerical TicTacToe");
        button.addActionListener(e->playerInputScreen2());        
        return button;
    }

    private JButton makeExitButton(){
        JButton button = new JButton("Exit");
        button.addActionListener(e->exitProgram());
        return button;
    }

    /* Action Listeners */

    /**
     * It removes all components from the hubContentPane, adds a new PlayerInputScreen for 
     * TicTacToe, then repaints and revalidates the hubContentPane
     */
    protected void playerInputScreen(){
        hubContentPane.removeAll();
        hubContentPane.add(new PlayerInputScreen(this, "T"));
        getContentPane().repaint();
        getContentPane().revalidate(); 
    }

    /**
     * It removes all components from the hubContentPane, adds a new PlayerInputScreen for 
     * Numerical TicTacToe, then repaints and revalidates the hubContentPane
     */
    protected void playerInputScreen2(){
        hubContentPane.removeAll();
        hubContentPane.add(new PlayerInputScreen(this, "NT"));
        getContentPane().repaint();
        getContentPane().revalidate(); 
    }

    /**
     * This mentod is used to exit the program
     */
    protected void exitProgram(){
        System.exit(0);
    }

    /* Main Method */
    /**
     * This function is the main function of the program. It creates a new GameUI object 
     * and sets it visible to access the GUI interface
     */
    public static void main(String[] args){
        GameUI hub = new GameUI();
        hub.setVisible(true);
    }
    
}

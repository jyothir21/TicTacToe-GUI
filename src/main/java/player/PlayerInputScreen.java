package player;

import game.GameUI;
import numtictactoe.NumTTTView;
import tictactoe.TicTacToeView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;


/**
 * It's a JPanel that takes in two player names and then creates a new game based on the game type.
 * @author Jyothir Krishnan
 */
public class PlayerInputScreen extends JPanel{
    private GameUI rootHub;
    private JTextField inputBox1;
    private JTextField inputBox2;

    private String gameCurrentlyPlayed;
    private String player1;
    private String player2;    

    /**
     * It's a constructor that takes in a GameUI object and a String object. It then sets the layout of
     * the JPanel to a BorderLayout. It then adds three JPanels to the JPanel.
     */     
    public PlayerInputScreen(GameUI frame, String curGame){
        super();
        rootHub = frame;
        gameCurrentlyPlayed = curGame;

        setLayout(new BorderLayout(0,10));     
        add(playerMessage(), BorderLayout.NORTH);
        add(inputBoxPanel(), BorderLayout.CENTER);
        add(optionButtonPanel(), BorderLayout.SOUTH);
    }

    /* Panels */
    private JPanel optionButtonPanel(){
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,10,5,10);
        optionPanel.add(makeHubButton(), c);
        optionPanel.add(makeSubmitButton(), c);

        return optionPanel;
    }     
    
    private JPanel inputBoxPanel(){
        JPanel inputBoxes = new JPanel();
        inputBoxes.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 105;
        c.insets = new Insets(10,30,5,30);  
        
        inputBoxes.add(new JLabel("Player 1", SwingConstants.CENTER), c);

        c.gridy = 1;
        inputBoxes.add(player1InputField(), c);

        c.gridx = 1;
        c.gridy = 0;
        inputBoxes.add(new JLabel("Player 2", SwingConstants.CENTER), c);

        c.gridy = 1;
        inputBoxes.add(player2InputField(), c);

        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 2;
        inputBoxes.add(makePlayer1Button(), c);
        c.gridx = 1;
        c.gridy = 2;
        inputBoxes.add(makePlayer2Button(), c);


        return inputBoxes;
    }
    
    private JLabel playerMessage(){
        JLabel playerMsg = new JLabel("Input Player Names!", SwingConstants.CENTER);
        playerMsg.setFont(new Font("Serif", Font.BOLD, 20));         
        return playerMsg;
    }

    /* Buttons */
    private JButton makeHubButton(){
        JButton button = new JButton("Hub");
        button.addActionListener(e->goHub());
        return button;
    }

    private JButton makeSubmitButton(){
        JButton button = new JButton("Submit");
        if(gameCurrentlyPlayed == "T"){
            button.addActionListener(e->ticTacToe());
        }else{
            button.addActionListener(e->numTicTacToe());
        }
        return button;
    }

    private JButton makePlayer1Button(){
        JButton button = new JButton("Set P1");        
        button.addActionListener(e->takePlayer1Name());        
        return button;
    }

    private JButton makePlayer2Button(){
        JButton button = new JButton("Set P2");        
        button.addActionListener(e->takePlayer2Name());        
        return button;
    }

    private JTextField player1InputField(){
        inputBox1 = new JTextField();
        inputBox1.setHorizontalAlignment(JTextField.CENTER);
        inputBox1.setBounds(0,0,70,30);        
        return inputBox1;
    }

    private JTextField player2InputField(){
        inputBox2 = new JTextField();
        inputBox2.setHorizontalAlignment(JTextField.CENTER);
        inputBox2.setBounds(0,0,70,30);                
        return inputBox2;
    }

    /* Action Listners */
    /**
     * It removes the menu bar from the current frame and then restarts the program
     */
    protected void goHub(){
        rootHub.setJMenuBar(null);
        rootHub.restart();
    }

    /**
     * It removes all components from the JPanel, adds a new JPanel, and repaints and revalidates the
     * JPanel and starts the tictactoe game interface
     */
    protected void ticTacToe(){
        try{
            if(player1.length() != 0 && player2.length() != 0){                
                removeAll();
                add(new TicTacToeView(3, 3, rootHub, player1, player2));
                repaint();
                revalidate();    
            }else{
                throw new NullPointerException();   
            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please input player names and click set!",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * It removes all components from the JPanel, adds a new JPanel, and repaints and revalidates the
     * JPanel and starts the numerical tictactoe game interface
     */
    protected void numTicTacToe(){
        try{
            if(player1.length() != 0 && player2.length() != 0){                
                removeAll();
                add(new NumTTTView(3, 3, rootHub, player1, player2));
                repaint();
                revalidate();    
            }else{
                throw new NullPointerException();   
            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please input player names and click set!",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This function takes the text from the input box and assigns it to the variable player1.
     */
    protected void takePlayer1Name(){
        player1 = inputBox1.getText();        
    }

    /**
     * This function takes the text from the input box and assigns it to the variable player2.
     */
    protected void takePlayer2Name(){
        player2 = inputBox2.getText();        
    }
}

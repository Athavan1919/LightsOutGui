import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>GridButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {


    private GameModel gameModel;
    
    private GridButton[][] board;

    private JCheckBox solution;
    
    private JLabel label;

    private GridButton steps;
    

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
            super("Light Out -- The ITI 1121 version");
            setSize(400,400);
            setResizable(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBackground(Color.WHITE);
            this.gameModel = gameModel;
            
            JPanel panelOne = new JPanel();
            panelOne.setBackground(Color.white);
            panelOne.setLayout(new GridLayout(gameModel.getHeight(), gameModel.getWidth()));
            panelOne.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
            board = new GridButton[gameModel.getHeight()][gameModel.getWidth()];
            
              for (int row = 0; row < gameModel.getHeight(); row++) {
                for (int column = 0; column < gameModel.getWidth(); column++) {
                        board[row][column] = new GridButton(row, column, 1,this);
                        board[row][column].addActionListener(gameController);
                        panelOne.add(board[row][column]);
                }
            }
            add(panelOne,BorderLayout.CENTER);

            JPanel panelTwo = new JPanel(new BorderLayout());
            panelTwo.setLayout(new GridLayout(4,1));
            panelTwo.setBackground(Color.white);

            Button reset = new Button("Reset");
            reset.addActionListener(gameController);
            reset.setBackground(Color.WHITE);
            
            Button random = new Button("Random");
            random.addActionListener(gameController);
            random.setBackground(Color.WHITE);
            
            Button quit = new Button ("Quit");
            quit.addActionListener(gameController);
            quit.setBackground(Color.WHITE);

            solution = new JCheckBox("solution",false);
            solution.addItemListener(gameController);
            solution.setBackground(Color.WHITE);

            panelTwo.add(reset,BorderLayout.EAST);
            panelTwo.add(random,BorderLayout.EAST);
            panelTwo.add(solution,BorderLayout.EAST);
            panelTwo.add(quit,BorderLayout.EAST);
            
            add(panelTwo,BorderLayout.EAST);
            
            
            JPanel panelThree = new JPanel();
            panelThree.setBackground(Color.white);
            panelThree.setLayout(new GridLayout(4,1));
            panelThree.setBackground(Color.white);

            //JLabel label = new JLabel("Number of steps:");
            label = new JLabel("Number of steps:");
            panelThree.add(label,BorderLayout.CENTER);
            add(panelThree,BorderLayout.SOUTH);


            setVisible(true);

    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
        if (solutionShown()){
            for (int row = 0; row < gameModel.getHeight(); row++) {
                for (int column = 0; column < gameModel.getWidth(); column++) {
                    board[row][column].setState(gameModel.isOn(row,column), gameModel.solutionSelects(row,column));
                    label.setText(gameModel.NumberOfSteps());

                }
            }
            
            if(gameModel.isFinished()) {
                    JFrame f=new JFrame();  
                    Object[] options = {"Play Again", "Quit"};
                    JOptionPane.showOptionDialog(f,"Congratulations, you won in " + gameModel.getNumberOfSteps() + 
                            " steps! Would you like to play again?", "Won", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[1]);  
                
            }
     
            
        }else{
            for (int row = 0; row < gameModel.getHeight(); row++) {
                for (int column = 0; column < gameModel.getWidth(); column++) {
                    
                    board[row][column].setState(gameModel.isOn(row,column), false);
                    label.setText(gameModel.NumberOfSteps());

                }
            }
            
            if(gameModel.isFinished()) {
                JFrame f=new JFrame();  
                Object[] options = {"Play Again","Quit"};
                JOptionPane.showOptionDialog(f,"Congratulations, you won in " + gameModel.getNumberOfSteps() + 
                        " steps! Would you like to play again?", "Won", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);  
            
        }

        }

        setVisible(true);
    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */

    public boolean solutionShown(){
            //TODO: Fix this
        return solution.isSelected();

    }
 
}









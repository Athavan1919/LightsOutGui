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
            setLayout(new GridLayout(gameModel.getHeight(), gameModel.getWidth()));
            //setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
            board = new GridButton[gameModel.getHeight()][gameModel.getWidth()];
            
              for (int row = 0; row < gameModel.getHeight(); row++) {
                for (int column = 0; column < gameModel.getWidth(); column++) {
                        board[row][column] = new GridButton(row, column, 1,this);
                        board[row][column].addActionListener(gameController);
                        add(board[row][column]);
                }
            }

            JPanel panelTwo = new JPanel();
            panelTwo.setLayout(new GridLayout(4,1));
            panelTwo.setBackground(Color.white);

            Button reset = new Button("Reset");
            reset.addActionListener(gameController);
            Button random = new Button("Random");
            random.addActionListener(gameController);
            Button quit = new Button ("Quit");
            quit.addActionListener(gameController);

            solution = new JCheckBox("solution",false);
            solution.addItemListener(gameController);

            panelTwo.add(reset,BorderLayout.EAST);
            panelTwo.add(random,BorderLayout.EAST);
            panelTwo.add(solution,BorderLayout.EAST);
            panelTwo.add(quit,BorderLayout.EAST);
            
            add(panelTwo,BorderLayout.EAST);

            setVisible(true);

    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
        //setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        if (solutionShown()){
            gameModel.setSolution();
            for (int row = 0; row < gameModel.getHeight(); row++) {
                for (int column = 0; column < gameModel.getWidth(); column++) {
                    board[row][column].setState(gameModel.isOn(row,column), gameModel.solutionSelects(row,column));
                }
            }
        }
        for (int row = 0; row < gameModel.getHeight(); row++) {
            for (int column = 0; column < gameModel.getWidth(); column++) {
                    
                    board[row][column].setState(gameModel.isOn(row,column), false);
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
    // public boolean setSolution(boolean value){
    //     solution.
    // }


}


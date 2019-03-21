import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

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
    
    JPanel p = new JPanel();

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel/*, GameController gameController*/) {
            super("Light Out -- The ITI 1121 version");
            setSize(400,400);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBackground(Color.WHITE);

            this.gameModel = gameModel;
            setLayout(new GridLayout(gameModel.getHeight(), gameModel.getWidth()));
            //setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
            board = new GridButton[gameModel.getHeight()][gameModel.getWidth()];
            
            for (int row = 0; row < gameModel.getHeight(); row++) {
                for (int column = 0; column < gameModel.getWidth(); column++) {
                        board[row][column] = new GridButton(this, row, column, 1);
                        add(board[row][column]);
                }
            }

            setVisible(true);
    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
        //setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        for (int row = 0; row < gameModel.getHeight(); row++) {
            for (int column = 0; column < gameModel.getWidth(); column++) {
                    if(gameModel.isOn(row, column)) {
                        board[row][column] = new GridButton(this, row, column, 0);
                        add(board[row][column]);
                    }
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

        // YOUR CODE HERE

    }
    
    public static void main(String[] args) {
            GameModel o = new GameModel(3,4);
            o.set(0, 2, true);
            GameView z = new GameView(o);
            z.update();
    }

}
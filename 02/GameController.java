import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;



// YOUR OTHER IMPORTS HERE IF NEEDE
import javax.swing.JOptionPane;
import javax.swing.*;
/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, ItemListener {

    public static final int DEFAULT_COLUMN = 10;
    public static final int DEFAULT_ROW = 8;

    private int row;
    private int column; 
    private GameModel model;
    public GameView view;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {

        row = height;
        column = width; 

        try{
                if(column<1){
                    System.out.println("Invalid argument, using default...");
                column = DEFAULT_COLUMN;
            }
            if(row<1){
                System.out.println("Invalid argument, using default...");
                row = DEFAULT_ROW;
            }
        } catch(NumberFormatException e){
            System.out.println("Invalid argument, using default...");
            width   = DEFAULT_ROW;
            height  = DEFAULT_COLUMN;
        }
        
        model = new GameModel(row,column);
        view = new GameView(model,this);
        
    }


    /**
     * Callback used when the user clicks a button (reset, 
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {

            if(e.getSource() instanceof GridButton) {
                
                GridButton src = (GridButton) e.getSource();
                

                    int row = src.getRow();
                    int column = src.getColumn();
                    model.click(row,column);
                    model.setSolution();
                    src.setState(model.isOn(row, column), model.solutionSelects(row,column));
                    
                    model.getNumberOfSteps();
                    System.out.println("Number of clicks is: " + model.getNumberOfSteps());

                
            }else if (e.getActionCommand().equals("Reset")){
                model.reset();
                model.setSolution();
                System.out.println("");
                System.out.println("Model has been reset: ");
                System.out.println(model);
                System.out.println("");
            
            }else if(e.getActionCommand().equals("Random")){
                model.randomize();
                model.setSolution();
                System.out.println("");
                System.out.println("Random model is: ");
                System.out.println(model);
                System.out.println("");

            }else if(e.getActionCommand().equals("Quit")){
                System.exit(0);
            }else{
                System.err.println("Unknown Action");
                System.exit(0);
            }

            view.update();

            if(model.isFinished()) {
                JFrame f=new JFrame();  
                Object[] options = {"Play Again", "Quit"};
                int exitOrplay = JOptionPane.showOptionDialog(f,"Congratulations, you won in " + model.getNumberOfSteps() + 
                        " steps! Would you like to play again?", "Won", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);  

                if (exitOrplay == 0){
                    model.reset();
                    view.update();
                }
                if (exitOrplay == 1){
                    System.exit(0);
                }

            }

    }

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */

    public void  itemStateChanged(ItemEvent e){


        if (e.getStateChange() ==1){
            model.setSolution();
        }
        view.update();
    }



}

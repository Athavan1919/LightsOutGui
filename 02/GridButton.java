import java.util.Random;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;



public class GridButton extends JButton {
    
    private static Random generator = new Random();

    public static final int NUM_STATE = 4;
    
    private boolean isOn = false;
    private boolean isClicked = false;


    private int row, column;
    private int type;
    
    private static final ImageIcon[] icons = new ImageIcon[NUM_STATE];


    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     * 
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int row, int column, int type, GameView board) {
            
            this.column = column;
            this.row = row;
            this.type = type;
    
            setBackground(Color.WHITE);
            setIcon(getImageIcon());
            Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
            setBorder(emptyBorder);
            setBorderPainted(false);
            //addActionListener(board);

    }
    
    public GridButton(int row, int column, GameView board) {
            this(column, row, generator.nextInt(NUM_STATE), board);
    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */ 
    public void setState(boolean isOn, boolean isClicked) {
        
            this.isOn = isOn;
            this.isClicked = isClicked;
           
            setIcon( getImageIcon());            

    }

 

    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
            return row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
            return column;
    }

    private ImageIcon getImageIcon() {
                
            int id;
            if (isOn) {
                id = 0;
            }else if(!isOn){
                id = 1;
            }else if (isOn && isClicked){
                id = 2;
            }else if (!isOn && isClicked){
                id = 3;
            }else{
                id = type;
            }
            if (icons[id] == null) {
                String strId = Integer.toString(id);
                icons[id] = new ImageIcon(this.getClass().getResource("Icons/Light-"+ strId + ".png"));
            }
            return icons[id];
        }
    
    private int newRandomCellType() {
            return generator.nextInt(NUM_STATE);
    }
    
    public void reset() {
            type = newRandomCellType();
            setIcon(getImageIcon());
            isOn = false;
    }
    
    public int getType() {
            return type;
    }
    
    public boolean sameType(GridButton other) {
            return type == other.type;
    }


}
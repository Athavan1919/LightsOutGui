import java.util.Random;

public class GameModel {
	
	private int row;
	private int column;
	private boolean[][] gameArray;
	private int numberOfClicks;
	private Solution minimal;


	
	public GameModel(int width, int height) {
		row = height;
		column = width;
		gameArray = new boolean[row][column];

	}
	
	public int getHeight() {
		return row;
	}
	
	public int getWidth() {
		return column;
	}
	
	public void set(int i, int j, boolean value) {

		gameArray[j][i] = value;
		
	}
	
	public boolean isOn(int i, int j) {
		return gameArray[i][j];
	}

	public void reset(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				gameArray[i][j] = false; 
			}
		}
		numberOfClicks = 0;
	}

	public void click(int i, int j){
        set(j , i , !isOn(i,j));
                        
        if ( (0 <= (j-1)) && ((j-1) < column) ){
            set(j-1,i, !isOn(i,j-1));
        }

        if ((0 <= (j+1)) && ((j+1) < column)){
            set(j+1,i, !isOn(i,j+1));
        }

        if ((0 <= (i-1)) && ((i-1) <= row)){
            set(j,i-1, !isOn(i-1,j));
        }

        if ((0 <= (i+1)) && ((i+1) < row)){
            set(j,i+1, !isOn(i+1,j));
        }
        
        numberOfClicks++;
       
	}

	public int getNumberOfSteps(){
		return numberOfClicks;
	}

	public boolean isFinished(){
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				if (!gameArray[i][j]){
					return false; 
				}
			}
		}
		return true;
	}
	public void randomize(){
		for (int i=0; i < row; i++){
			for (int j=0; j < column; j++){
				this.set(j,i, Math.random() <0.5);
			}
		}
	}

	public void setSolution(){
		minimal = LightsOut.solveShortest(this);
		System.out.println("Solution is: ");
		System.out.println(minimal);
	}

	public boolean solutionSelects(int i, int j){
		return minimal.get(i,j);
	}
	
	public String NumberOfSteps(){
		return "Number of steps: " + getNumberOfSteps();
	}

	public String toString() {
		String array_output = "[[";
		for (int row = 0; row < gameArray.length; row++) {
			if (row>0) {
				array_output += "[";
			}
			
			for(int column = 0; column < gameArray[row].length; column++) {
				if (column < gameArray[row].length - 1) {
					array_output += gameArray[row][column] + ", "; 
				}
				else {
					array_output += gameArray[row][column];
				}
				
			}
			
			if (row != gameArray.length - 1) {
				array_output += ("], " + System.lineSeparator());
			}
			
		}
		array_output += "]]";
		return array_output;
	}

}
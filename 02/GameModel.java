import java.util.Random;

public class GameModel {
	
	private int row;
	private int column;
	private boolean[][] gameArray;
	private int[][] oddCounter;
	private int counter = 0;

	
	public GameModel(int width, int height) {
		row = height;
		column = width;
		gameArray = new boolean[row][column];
		oddCounter = new int[row][column];
	}
	
	public int getHeight() {
		return row;
	}
	
	public int getWidth() {
		return column;
	}
	
	public void set(int i, int j, boolean value) {
		gameArray[j][i] = value;
		oddCounter[j][i] += 1;
	}
	
	public boolean isOn(int i, int j) {
		if(oddCounter[i][j]%2 == 0) {
			return false;
		}
		return true;
	}
	
	public void click(int i, int j) {
		if(i == 0 && j == 0) {
			oddCounter[i][j] += 1;
			oddCounter[i][j+1] += 1;
			oddCounter[i+1][j] += 1;
			
			counter += 1;
		}
		
		if(i == 0 && j == column-1) {
			oddCounter[i][j] += 1;
			oddCounter[i][j-1] += 1;
			oddCounter[i+1][j] += 1;
			
			counter += 1;
		}
		
		if(i == row-1 && j == 0) {
			oddCounter[i][j] += 1;
			oddCounter[i-1][j] += 1;
			oddCounter[i][j+1] += 1;
			
			counter += 1;
		}
		
		if(i == row-1 && j == column-1) {
			oddCounter[i][j] += 1;
			oddCounter[i-1][j] += 1;
			oddCounter[i][j-1] += 1;
			
			counter += 1;
		}
		
		if(i == 0 && (j != 0 || j != column-1)) {
			oddCounter[i][j] += 1;
			oddCounter[i][j-1] += 1;
			oddCounter[i][j+1] += 1;
			oddCounter[i+1][j] += 1;
			
			counter += 1;
		}
		
		if(i == row-1 && (j != 0 || j != column-1)) {
			oddCounter[i][j] += 1;
			oddCounter[i][j-1] += 1;
			oddCounter[i][j+1] += 1;
			oddCounter[i-1][j] += 1;
			
			counter += 1;
		}
		
		if(j == 0 && (i != 0 || i != row-1)) {
			oddCounter[i][j] += 1;
			oddCounter[i-1][j] += 1;
			oddCounter[i+1][j] += 1;
			oddCounter[i][j+1] += 1;
			
			counter += 1;
		}
		
		if(j == column-1 && (i != 0 || i != row-1)) {
			oddCounter[i][j] += 1;
			oddCounter[i-1][j] += 1;
			oddCounter[i+1][j] += 1;
			oddCounter[i][j-1] += 1;
			
			counter += 1;
		}
		
		oddCounter[i][j] += 1;
		oddCounter[i][j-1] += 1;
		oddCounter[i][j+1] += 1;
		oddCounter[i-1][j] += 1;
		oddCounter[i+1][j] += 1;
		
		counter += 1;
		
	}
	
	public int getNumberOfSteps() {
		return counter;
	}
	
	public boolean isFinished() {
		for(int i=0;i<row;i++) {
			for (int j=0;j<column;j++){
				if(oddCounter[i][j] % 2 == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*Need to add three more methods*/
	
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


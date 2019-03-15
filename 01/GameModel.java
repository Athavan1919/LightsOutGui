
public class GameModel {
	
	private int row;
	private int column;
	private boolean[][] gameArray;
	private int[][] oddCounter;

	
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

import java.util.Scanner; 
public class TextView{
	//temp class 
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		GameModel model = new GameModel(3,3);
		model.randomize();
		while (!model.isFinished()){
			System.out.println("Potential solution is: ");
			model.setSolution();
			System.out.println("");

			System.out.println("Board is");
			System.out.println(model);
			System.out.println("Enter row");
			int row = input.nextInt();
			System.out.println("Enter column");
			int column = input.nextInt();
			
			model.click(row,column);
		}	
		System.out.println("Finished");
	}
}
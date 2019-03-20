import java.util.ArrayList;
public class SampleTest{
	private static void runSolverTest(GameModel model, int numberOfSolutions){
        ArrayList<Solution>  results  = LightsOut.solve(model);
        if(results.size()==numberOfSolutions) {
            System.out.println("Success!");
        } else {
            System.out.println("Failure! Expecting " 
                + numberOfSolutions + " and got " 
                + results.size());            
        }
        System.out.println("Starting from :");
        System.out.println(model);
        System.out.println("Solution(s) :");
        for(int i =0; i < results.size(); i++){
            System.out.println(results.get(i));            
        }
    }

     private static void testSolver(){
     	GameModel model = new GameModel(3,3);
     	model.set(0,0,true);
        model.set(1,1,true);
        model.set(2,2,true);
        System.out.println("7");
        runSolverTest(model,1);
     }
     public static void main(String[] args){
     	testSolver();
     }
}
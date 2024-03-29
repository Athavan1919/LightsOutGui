import java.util.ArrayList;


/**
 * The class <b>LightsOut</b> launches the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class LightsOut {


     /**
     * default width of the game.
     */
    public static final int DEFAULT_WIDTH = 10;
     /**
     * default height of the game.
     */
    public static final int DEFAULT_HEIGTH = 8;


    public static ArrayList<Solution> solve(GameModel model){
        if (model == null){
            throw new NullPointerException("Model is null");
        }


        Queue<Solution> q  = new QueueImplementation<Solution>();
        ArrayList<Solution> solutions  = new ArrayList<Solution>();

        Solution temp = new Solution(model.getWidth(),model.getHeight());
        //System.out.println("temp solution is " +temp);
        q.enqueue(temp);
        long start = System.currentTimeMillis();
        while(!q.isEmpty()){

            Solution s  = q.dequeue();
            
            /*    
            System.out.println("");
            System.out.println("Solution number" + counter);
            System.out.println(s);
            System.out.println("");
            */
            if(s.isSuccessful(model)){
                // by construction, it is successfull
                System.out.println("Solution found in " + (System.currentTimeMillis()-start) + " ms" );
                solutions.add(s);

            } else {
                boolean withTrue = s.stillPossible(true);
                boolean withFalse = s.stillPossible(false);

                    /*
                System.out.println(withTrue + " and " + withFalse);
                System.out.println("Current index is " + s.currentIndex());
                    */
                if(withTrue && withFalse) {
                    Solution s2 = new Solution(s);
                    s.setNext(true);
                    q.enqueue(s);
                    s2.setNext(false);
                    q.enqueue(s2);
                } else if (withTrue) {
                    if(s.finish(model)){
                        q.enqueue(s);
                    }                
                } else if (withFalse) {
                    if( s.finish(model)){
                        q.enqueue(s); 
                    }               
                }
            }
        }
        return solutions;
    }

    public static Solution solveShortest(GameModel model){

        if (model == null){
            throw new NullPointerException("Model is null");
        }

        ArrayList<Solution> solutions = solve(model);

        Solution shortest = solutions.get(0);
        
        for (int i = 0; i < solutions.size(); i++){
            if (solutions.get(i).getSize() < shortest.getSize()){
                shortest = solutions.get(i);
            }
        }

        return shortest;
    }


    
   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If two parameters width and height
     * are passed, they are used. 
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 1).
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {
        int width   = DEFAULT_WIDTH;
        int height  = DEFAULT_HEIGTH;
 
        StudentInfo.display();

        if (args.length == 2) {
            try{
                width = Integer.parseInt(args[0]);
                if(width<1){
                    System.out.println("Invalid argument, using default...");
                    width = DEFAULT_WIDTH;
                }
                height = Integer.parseInt(args[1]);
                if(height<1){
                    System.out.println("Invalid argument, using default...");
                    height = DEFAULT_HEIGTH;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = DEFAULT_WIDTH;
                height  = DEFAULT_HEIGTH;
            }
        }
        GameController game = new GameController(width, height);
    }


}

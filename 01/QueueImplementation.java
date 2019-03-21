import java.util.ArrayList;
public class QueueImplementation<E> implements Queue<E> {

    private ArrayList<E> queue;

    /**
     * Constructor, initializes  <b>queue</b>
     */
    public QueueImplementation() {

         queue = new ArrayList<E>();
        
    }

    /**
     * implementation of the method <b>enqueue</b> 
     * from the interface <b>SolutionQueue</b>.
     * @param value
     *      The reference to the new element
     */
    public void enqueue(E value) {

        queue.add(value);
        
    }

    /**
     * implementation of the method <b>dequeue</b> 
     * from the interface <b>SolutionQueue</b>.
     * @return 
     *      The reference to removed Solution
     */
    public E dequeue() {

        E first = queue.get(0);
        queue.remove(0);
        return first;
        
    }

    /**
     * implementation of the method <b>isEmpty</b> 
     * from the interface <b>SolutionQueue</b>.
     * @return 
     *      true if the queue is empty 
     */
    public boolean isEmpty() {

        int length = queue.size();
        //System.out.println("Size is "+ length);
        return (length == 0);
        
    }

}

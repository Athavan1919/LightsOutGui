public interface Queue<E> {
 
    boolean isEmpty();


	/**
     * Add the reference to Solution at the rear of
     * the queue. Assumes s is not null
     * @param s
     *		The (non null) reference to the new element
     */
    void enqueue(E s);

	/**
     * Removes the reference to Solution at the front of
     * the queue. Assumes the queue is not empty
     * @return 
     *		The reference to removed Solution
     */
    E dequeue();
}
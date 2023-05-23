public interface Stack<T> {

    /**
     * Returns the number of items in the stack.
     * 
     * @return the number of items in the stack.
     */
    public int size();

    /**
     * Returns the item on the top of the stack.
     * 
     * @return the item on the top of the stack.
     */
    public T peek();

    /**
     * Returns and removes the item on the top of the stack.
     * 
     * @return the item on the top of the stack.
     */
    public T pop();

    /**
     * Adds the given {@code item} to the top of the stack.
     * 
     * @param item the item to be added.
     */
    public void push(T item);
}

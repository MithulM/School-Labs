/**
* Main class that adds methods to the default stack class. It reverse a stack, removes an object based on an index
* and it removes multiple values.
* 
* @author Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Ishman
* Period: 2
* Due Date: 4/6/2020
*/

import java.util.*;

public class StackBonus<E> extends Stack <E>
{
    /*NOTICE THE INHERITANCE, The stack will be constructed in the parent */
    /** Constructs this StackBonus object **/
    public StackBonus()
    {
        super();
    }
    
    /** multiPop will repetively remove data for a given number of times.
     * @param numItems the number of items to remove from the stack.
     */
    public void multiPop(int numItems)
    {
        if(numItems < super.size())
    	    for(int i = 0; i < numItems; i++)
    	        super.pop();
    }
    
    
    /** reverse will create a new Stack containing the inverse order of 
     * the current stack.  
     * @return the Stack in reverse order
     * */
    public StackBonus<E> reverse()
    {
        StackBonus<E> reversed = new StackBonus<>(); 
        Stack<E> temp = new Stack<>();
        int size = super.size();
        for(int i = 0; i < size; i++)
        {
            E popped = super.pop();
            reversed.push(popped);
            temp.push(popped);
        }
        for(int i = 0; i < size; i++)
            super.push(temp.pop());
        return reversed;
    }
    
    /** undo will remove one item from the Stack for a given index. 
     * @param index the index of the data to be removed.
     * @return false if the index is beyond the bounds of the Stack.
     * otherwise, it will return true.
     */
    public boolean undo(int index)
    {
        int size = super.size();
        if(index < 0 || index >= size)
            return false;
        Stack<E> temp = new Stack<>();
        for(int i = 0; i < size - index - 1; i++)
            temp.push(super.pop());
        super.pop();
        for(int i = 0; i < size - index - 1; i++)
            super.push(temp.pop());
        return true;
    }
}
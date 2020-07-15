/**
 * Node.java
 * Class representing a generic doubly linked list node 
 * Created by Plano ISD CS Teachers 
 * For use for Computer Science 3 Honors
 * Due Date: 04-20-2020
 */

public class Node<T>
{
	private T value;
	private Node<T> next;
	private Node<T> previous;
	
	/** Construct a node with the given value and the next and prev references
	 *  @param initValue the value to store in this node
	 *  @param initNext the next node in the list
	 *  @param initPrev the previous node in the list
	 */
	public Node(T initValue, Node<T> initNext, Node<T> initPrev) 
	{
		value = initValue;
		next = initNext;
		previous = initPrev;
	}
	
	/** Retrieve this node's value
	 *  @return the value stored in this node
	 */
	public T getValue() 
	{ 
		return value; 
	}
	
	/** Retrieve this node's next node
	 *  @return the reference to the next node in the list
	 */
	public Node<T> getNext() 
	{ 
		return next;
	}
	
	/** Retrieve this node's previous node
	 *  @return the reference to the previous node in the list
	 */
	public Node<T> getPrevious() 
	{ 
		return previous; 
	}
	
	/** Change the value stored in this node
	 *  @param theNewValue the new value to store in this node
	 */
	public void setValue(T theNewValue) 
	{
		value = theNewValue;
	}
	
	/** Change the reference to the next node after this node
	 *  @param theNewNext the next node in the list
	 */
	public void setNext(Node<T> theNewNext) 
	{
		next = theNewNext;
	}
	
	/** Change the reference to the previous node after this node
	 *  @param theNewPrev the previous node in the list
	 */
	public void setPrevious(Node<T> theNewPrev) 
	{
		previous = theNewPrev;
	}
}
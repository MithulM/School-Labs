/**
 * Description: The FilingCabinet class has only one data member, the array of
 * DoubleNode<Student> objects. Each index stores the first item to a drawer.
 * The linked listed are doubly linked.Each drawer in the filing cabinet contains
 * the students whose last name starts with the same letter. There is the add method
 * to add student, there is the contains method to check if a student exists, and
 * there is a remove method to remove a student from the doubly linked list or drawers.
 *
 * Teacher: Mrs. Ishman
 * Collab: None
 * Period: 2nd
 * @author Mithul Manivannan
 * Date: 12/10/19
 */

public class FilingCabinet
{
	// Number of filing cabinets (one for each letter A-Z) */
	private static final int NUM_CABINETS = 26;

	// Array of DoubleNode objects for filing cabinet of Students
	private DoubleNode<Student>[] cabinet;

	@SuppressWarnings("unchecked")
	public FilingCabinet()
	{
		cabinet = new DoubleNode[NUM_CABINETS];
	}

	/**
	 * Inserts a student in the proper drawer.
	 * @param stu student to be added to the proper drawer alphabetical order.
	 */
	public void add(Student stu)
	{

		DoubleNode<Student> temp = cabinet[getDrawer(stu)];
		DoubleNode<Student> node = new DoubleNode<>(stu, null,null);
		if(temp == null)
			cabinet[getDrawer(stu)] = node;
		else if(temp.getValue().compareTo(stu) >= 0){
			node.setNext(temp);
			node.getNext().setPrevious(node);
			cabinet[getDrawer(stu)] = node;
		} else {
			while (temp.getNext() != null && temp.getNext().getValue().compareTo(stu) < 0)
				temp = temp.getNext();

			node.setNext(temp.getNext());
			if(temp.getNext() != null)
				node.getNext().setPrevious(node);
			temp.setNext(node);
			node.setPrevious(temp);
		}

	}

	/**
	 * checkis if stu was already added.
	 * @returns tells if stu is inside a drawer.
	 */
	public boolean contains(Student stu)
	{
		DoubleNode<Student> frontTemp = cabinet[getDrawer(stu)];
		while (frontTemp != null){
			if(frontTemp.getValue().equals(stu))
				return true;
			frontTemp = frontTemp.getNext();
		}
		return false;
	}
	/**
	 * removes a given student
	 * @param the student to remove
	 */
	public void remove(Student stu)
	{
		DoubleNode<Student> temp = cabinet[getDrawer(stu)];
		DoubleNode<Student> newNode = new DoubleNode<>(stu, null, null);
		if(temp == null || !contains(stu))
			return;
		if(temp.getNext() == null)
		{
			cabinet[getDrawer(stu)] = null;
			return;
		}
		DoubleNode<Student> prev = null;
		for(; temp != null && !temp.getValue().equals(stu); temp = temp.getNext())
			prev = temp;
		if(prev == null)
		{
			temp.getNext().setPrevious(null);
			cabinet[getDrawer(stu)] = temp.getNext();
		}
		else if(temp == null)
			return;
		else if(temp.getNext() == null)
			prev.setNext(null);
		else if(prev.equals(newNode))
		{
			prev.getPrevious().setNext(temp);
			temp.setPrevious(prev.getPrevious());
		}
		else{
			prev.setNext(temp.getNext());
			temp.getNext().setPrevious(prev);
		}
	}

	/**
	 * overrides the object toString.
	 * @return filing cabinet as a string with each student on a separate line
	 */
	@Override
	public String toString()
	{
		StringBuilder buf = new StringBuilder("Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = 0; k < cabinet.length; k++)
		{
			drawer = cabinet[k];
			if (drawer != null)
				buf.append("Drawer " + (char) ('A' + k) + ":  ");
			while (drawer != null)
			{
				buf.append(drawer.getValue().toString() + "\n");
				drawer = drawer.getNext();
				if (drawer != null) buf.append("           ");
			}
		}
		buf.append("**end**");
		return buf.toString();
	}

	/**
	 * gives the reversed to string.
	 * @return filing cabinet as a string with each
	 *  		student in reverse alphabetical order
	 */
	public String reverseToString()
	{
		StringBuilder build = new StringBuilder("Reverse Filing Cabinet:\n");
		DoubleNode<Student> drawer;
		for (int k = cabinet.length-1; k >= 0; k--)
		{
			drawer = cabinet[k];
			if (drawer != null)
			{
				build.append("Drawer " + (char) ('A' + k) + ":  ");
				// go to end
				while (drawer.getNext() != null)
					drawer = drawer.getNext();
				while (drawer != null)
				{
					build.append(drawer.getValue().toString() + "\n");
					drawer = drawer.getPrevious();
					if (drawer != null) build.append("           ");
				}
			}
		}
		build.append("**end**");
		return build.toString();
	}

	//--------------------------------------------------
	//	Private Methods
	//--------------------------------------------------

	/** Determines which drawer could contain stu
	 *   @return the index of the drawer for where it goes
	 *          (uses the 'Z' drawer if last name does not begin with a letter)
	 */
	private int getDrawer(Student stu)
	{
		char letter = stu.getLastName().toUpperCase().charAt(0);
		if (letter < 'A' || letter > 'Z') letter = 'Z';
		return letter - 'A';
	}
}
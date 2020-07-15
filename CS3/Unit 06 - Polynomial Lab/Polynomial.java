/**
 * Description: The Polynomial class uses the ListNode class to
 * implement its linked list of terms. It includes the Term class
 * as a private inner class. Term creates a term for a polynomial list.
 * It also adds the terms of the given array in order.
 *
 * Teacher: Mrs. Ishman
 * Collab: None
 * Period: 2nd
 * @author Mithul Manivannan
 * Date: 11/14/19
 */
import java.util.ListIterator;

public class Polynomial
{
	private ListNode<Term> list;

	/**
	 *  Initializes the list variable
	 */
	public Polynomial()
	{
		list = null;
	}

	/**
	 *  Adds a term to a class in sorted order
	 *  @param coeff the coefficient for the term to have
	 *  @param power the power for the term to have
	 */
	public void addTerm(int coeff, int power)
	{
		if(coeff == 0)
			return;
		ListNode<Term> node = new ListNode<>(new Term(coeff, power), null);
		if (list == null)
			list = node;
		else if(power > list.getValue().getPower())
		{
				node.setNext(list);
				list = node;
		}
		else
		{
			ListNode<Term> prev = list;
			ListNode<Term> curr = list.getNext();
			while (!(curr == null) && (power < curr.getValue().getPower()))
			{
				prev = curr;
				curr = curr.getNext();
			}
			node.setNext(curr);
			prev.setNext(node);
		}
	}

	/**
	 * adds two polynomials together
	 * @param other a polynomial class to add from
	 * @return a polynomial of list and other added together
	 */
	public Polynomial sum(Polynomial other)
	{
		Polynomial poly = new Polynomial();
		for (ListNode<Term> otherTemp = other.getList(); otherTemp != null; otherTemp = otherTemp.getNext())
		{
			boolean checker = false;
			Term otherValue = otherTemp.getValue();
			for (ListNode<Term> thisTemp = list; thisTemp != null; thisTemp = thisTemp.getNext())
			{
				Term value = thisTemp.getValue();
				if (value.getPower() == otherValue.getPower())
					checker = true;
			}
			if (checker == false)
				poly.addTerm(otherValue.getCoefficient(), otherValue.getPower());
		}
		for (ListNode<Term> thisTemp = list; thisTemp != null; thisTemp = thisTemp.getNext())
		{
			boolean checker = false;
			Term value = thisTemp.getValue();
			for (ListNode<Term> otherTemp = other.getList(); otherTemp != null; otherTemp = otherTemp.getNext())
			{
				Term otherValue = otherTemp.getValue();
				if (value.getPower() == otherValue.getPower())
				{
					poly.addTerm(value.getCoefficient() + otherValue.getCoefficient(), value.getPower());
					checker = true;
				}
			}
			if (checker == false)
				poly.addTerm(value.getCoefficient(), value.getPower());
		}
		return poly;
	}

	/**
	 * @returns the given list
	 */
	private ListNode<Term> getList()
	{
		return list;
	}

	/**
	 * @param values contains the values to add to list
	 */
	private void addValues(ListNode<Term> values)
	{
		for(ListNode<Term> temp = values; temp != null; temp = temp.getNext())
			addTerm(temp.getValue().getCoefficient(), temp.getValue().getPower());
	}

	/**
	 * overrides the object tostring method
	 * @return a string with polynomials in order
	 */
	@Override
	public String toString()
	{
		ListNode<Term> temp;
		String out = "";
		if(list == null)
			return "0";
		for(temp = list; !(temp.getNext() == null); temp = temp.getNext())
			out += temp.getValue() + " + ";
		out += temp.getValue();
		return out;
	}


	/**
	 * Private Inner Class
	 */
	private class Term
	{
		/** Coefficient and exponent of a polynomial term
		 */
		private int coeff;
		private int power;

		/**
		 * Constructs a term with the given coefficient and exponent
		 * @param co the coefficient for this term
		 * @param pwr the exponent for this term
		 */
		public Term(int co, int pwr)
		{
			coeff = co;
			power = pwr;
		}

		/**
		 * Returns the coefficient for this term
		 * @return coefficient of this term
		 */
		public int getCoefficient()
		{
			return coeff;
		}

		/**
		 * Returns the exponent for this term
		 * @return exponent of this term
		 */
		public int getPower()
		{
			return power;
		}
	/**
	 * overrides the object tostring method
	 * @return a term
	 */
		@Override
		public String toString()
		{
            if(coeff == 0)
                return "";
            if(coeff == 1 && power == 1)
                return "x";
            if(coeff == -1 && power == 1)
                return "-x";
            if(coeff == 1)
            	return "x^" + power;
            if(coeff == -1)
            	return "-x^" + power;
            if(power == 0)
            	return "" + coeff;
            if(power == 1)
            	return coeff + "x";
			return coeff + "x^" + power;
		}
	}
}
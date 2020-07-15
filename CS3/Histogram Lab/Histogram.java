/**
* Creates a map that keeps track of how many a character shows up.
* 
* @author Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Ishman
* Period: 2
* Due Date: 3/31/2020
*/

import java.util.*;

public class Histogram
{
	private Map<String, Integer> histogram;
	
	/**
	 * creates a new Map
	 **/
	public Histogram()
	{
	    histogram = new TreeMap<>();
	}
	
	/**
	 * calls the setSentence
	 * @param a line of characters sperated by a space
	 **/
	public Histogram(String sent)
	{
	    setSentence(sent);
	}
	
	/**
	 * creates a new map and counts the number of times a character shows up
	 * @param a line of characters sperated by a space
	 **/
	public void setSentence(String line)
	{
	    histogram = new TreeMap<>();
	    Scanner in = new Scanner(line);
	    while(in.hasNext())
	    {
	        String val = in.next();
	        if(!histogram.containsKey(val))
	            histogram.put(val, 1);
	        else
	            histogram.put(val, histogram.get(val)+1);
	    }
	}
	
	/**
	 * a formated string is returned
	 * @return a string that formats characters
	 **/
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		output.append("char 1---5----01---5\n");
		for(String chars: histogram.keySet())
		{
		    output.append(chars);
		    output.append("    ");
		    for(int i = 0; i < histogram.get(chars); i++)
		        output.append("*");
		    output.append("\n");
		}
		output.append("\n\n");
		return output.toString();
	}
}
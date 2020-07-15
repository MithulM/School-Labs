/**
 * The Partition class stores a pattern for the a string to check for the solution word.
 * It has three methods.
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2nd
 * Due Date: 8/30/2019
 */


import java.util.*;

public class Partition
{
	private String wordPattern;
	private List<String> wordList;

	/**
	 * Construct a Partition object with no words.
	 * @param pattern the pattern for the partition.
	 */
	public Partition(String pattern)
	{
		wordPattern=pattern;
		wordList = new ArrayList<>();
	}

	/**
	 * Construct a Partition object with one given word.
	 * @param pattern the pattern for the partition.
	 * @param word the initial word for the partition.
	 */
	public Partition(String pattern, String word)
	{
		wordPattern = pattern;
		wordList = new ArrayList<>();
		wordList.add(word);
	}

	/**
	 * Adds word to the partition IFF the pattern matches the given one.
	 * @param pattern the pattern to check for a match.
	 * @param word the word to add if the patterns match.
	 * @returns true if word is added; false otherwise.
	 */
	public boolean addIfMatches(String pattern, String word)
	{
		if(pattern.equals(wordPattern))
		{
			wordList.add(word);
			return true;
		}
		return false;
	}

	/**
	 * Retrieves the words for this pattern
	 * @return the pattern's words.
	 */
	public List<String> getWords()
	{
		return wordList;
	}

	/**
	 * Retrieves the number of dashes in the partition's pattern.
	 * @return the number of dashes in the partition's pattern.
	 */
	public int getPatternDashCount()
	{
		int num = 0;
		for(int i = 0;i<wordPattern.length();i++)
			if(wordPattern.charAt(i)=='-')
				num++;
		return num;
	}
}
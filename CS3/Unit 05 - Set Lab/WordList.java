/**
 *  Description: WordList parses a file given throught the constructor.
 *	The class parses the file to store unique words in case-insensitive order.
 *	setFile method resets the file, the getWords method returns the current words list,
 *	the getAllWords method returns the words from the current file and a given set from a
 *	different file, and the the getCommonWords method returns the common words in the given set and the
 *	current file.
 *
 *  Teacher: Mrs. Ishman
 *  Collab: None
 *  Period: 2nd
 *  @author Mithul Manivannan
 *  Date: 10/21/19
 */


import java.util.*;
import java.io.*;

public class WordList
{
    Scanner in;
    Set<String> uniqueWords;

    /**
     *	calls setFile method and initializes the uniqueWords set and in scanner.
     *	@param file the file name to read
     */
    public WordList(String file)
    {
        setFile(file);
    }

    /**
     *	reads text for the given file
     *	@param file the new file name to read for unique words
     */
    public void setFile(String file)
    {
        try {
            in = new Scanner(new File(file));
            in.useDelimiter("[,;:.!?()\\s+]");
            uniqueWords = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
            while (in.hasNext()) {
                String word = in.next();
                if (word.length() != 0)
                    uniqueWords.add(word);
            }
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
    }

    /**
     *	returns the uniqueWords as list
     *	@return the uniqueWords as list
     */
    public Set<String> getWords()
    {
        return uniqueWords;
    }

    /**
     *	returns a set of all words in the unique words and moreWords
     *	@param moreWords the set of the words from a different file
     *	@return a set of all words in the unique words and moreWords
     */
    public Set<String> getAllWords(Set<String> moreWords)
    {
        Set<String> combine = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        combine.addAll(moreWords);
        combine.addAll(uniqueWords);
        return combine;
    }

    /**
     *	returns a set of common words between the unique words and moreWords
     *	@param moreWords the set of the words from a different file
     *	@return a set of common words between the unique words and moreWords
     */
    public Set<String> getCommonWords(Set<String> moreWords)
    {
        Set<String> both = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        both.addAll(moreWords);
        both.retainAll(uniqueWords);
        return both;
    }
}
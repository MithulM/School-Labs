/**
 * The EvilHangman is the game class that has the game logic.
 * It has also overrides the toString.
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2nd
 * Due Date: 8/30/2019
 */

import java.util.*;
import java.io.*;

public class EvilHangman
{
    private boolean debug;
    private Scanner in;
    private List<String> wordList;
    private int wordLength;
    private int remainingGuesses;
    private String solution;
    private String guessedLetters;

    public EvilHangman(String fileName, boolean debug)
            throws FileNotFoundException
    {
        this.debug = debug;
        in = new Scanner(System.in);
        inputWords(fileName);
        System.out.print("Number of guesses? ");
        if(in.hasNext())
            remainingGuesses = Integer.parseInt(in.next());
        solution = "";
        for(int i=0;i<wordLength;i++)
            solution += "-";
        guessedLetters = "";
    }

    /**
     * This has main game logic that calls toString and
     *  uses all the helper methods.
     */
    public void playGame()
    {
        while(solution.contains("-") && remainingGuesses>0)
        {
            System.out.println(this.toString());
            String letter = inputLetter();
            guessedLetters += letter;
            List<Partition> partitions = getPartitionList(letter);
            removeSmallerPartitions(partitions);
            wordList = getWordsFromOptimalPartition(partitions);
            String temp = solution;
            substitute(wordList.get(0), letter);
            if(temp.equals(solution))
                remainingGuesses--;
        }
        if(remainingGuesses!=0)
            System.out.println("You win, congratulations!");
        else
            System.out.println("You lose, sorry!");
        System.out.println("The word was \"" + wordList.get((int) (Math.random()*wordList.size())) + "\"");
    }

    /**
     * Formats string to print remaining guesses
     * @return formatted string of most variables in formatted string.
     */
    public String toString()
    {
        if(debug)
            return String.format("\nRemaining guesses: %d", remainingGuesses) +
                    String.format("\nGuessed letters: %s", guessedLetters)+
                    String.format("\nSolution: %s", solution) +
                    String.format("\nRemaining words: %d ", wordList.size());

        return String.format("\nRemaining guesses: %d", remainingGuesses) +
                String.format("\nGuessed letters: %s", guessedLetters)+
                String.format("\nSolution: %s ", solution);
    }


    ////////// PRIVATE HELPER METHODS //////////

    /**
     * Gets a valid word length from the user.
     * @param file to read from.
     * @throws FileNotFoundException if there is not a file.
     */
    private void inputWords(String fileName) throws FileNotFoundException
    {
        wordList = new ArrayList<>();
        while(wordList.size()==0)
        {
            System.out.print("Word length? ");
            if(in.hasNext())
                wordLength = Integer.parseInt(in.next());
            Scanner file = new Scanner(new File(fileName));
            while(file.hasNext())
            {
                String nextWord = file.next();
                if (nextWord.length() == wordLength)
                    wordList.add(nextWord);
            }
            if(wordList.size()==0)
                System.out.println("There are no words with " + wordLength + " letters.");
        }
    }

    /**
     * returns the guessed letter from user
     * @return a valid guessed letter from the user
     */
    private String inputLetter()
    {
        while(true)
        {
            System.out.print("\nNext letter? ");
            String given = in.next().toUpperCase();
            if (given.matches("[A-Z]"))
                return given;
            System.out.println("Invalid input!");
        }
    }

    /**
     * gets the locations for the letter in the word
     * @param word the word searched for letter
     * @param letter single letter from user
     * @return a word with dashes except the locations with the letter there
     */
    private String getPattern(String word, String letter)
    {
        String guessed = "";
        for(int i = 0;i<word.length();i++)
            if(word.charAt(i) == letter.charAt(0))
                guessed += letter;
            else
                guessed += "-";
        return guessed;
    }

    /**
     * returns the list with partitions with the matching pattern
     * @param letter one letter
     * @return the list with partitions with the matching pattern
     */
    private List<Partition> getPartitionList(String letter)
    {
        ArrayList<Partition> partitions = new ArrayList<>();
        for(String word: wordList)
        {
            boolean checked = false;
            String pattern = getPattern(word, letter);
            for (Partition parts : partitions)
                if (parts.addIfMatches(pattern, word))
                    checked = true;
            if (!checked)
                partitions.add(new Partition(pattern, word));
        }
        return partitions;
    }

	/**
	 * removes any partition with less than the max length word
	 * @param partitions is the partition list with a certain pattern
	 */
    private void removeSmallerPartitions(List<Partition> partitions)
    {
        int max = 0;
        for(int i = 0;i<partitions.size();i++)
            if(partitions.get(i).getWords().size()>max)
                max = partitions.get(i).getWords().size();
        for(int i = 0;i<partitions.size();i++)
            if(partitions.get(i).getWords().size()<max)
            {
                partitions.remove(i);
                i--;
            }
    }

	/**
	 * returns words with the max partition dashes
	 * @param partitions is the partition list with a certain pattern
	 * @return returns the words with the max partition dashes
	 */
    private List<String> getWordsFromOptimalPartition(List<Partition> partitions)
    {
        Partition partition = partitions.get(0);
        for(Partition part: partitions)
            if(part.getPatternDashCount()>partition.getPatternDashCount())
                partition = part;
        return partition.getWords();
    }

	/**
	 * changes the remaining dashes with the guessed letter if it is correct
	 * @param found is the words that changes the letter with dashes
	 * @param letter is the letter the user guessed.
	 */
    private void substitute(String found, String letter)
    {
        for(int i = 0;i < found.length(); i++)
            if(found.charAt(i) == letter.charAt(0))
                solution  = solution.substring(0, i) + letter + solution.substring(i+1);
    }
}

/**
 *  Description: The main class to test if the WordList works as intended.
 *	it has methods contin method to check if the user wants to continue
 *	the program. and to get a valid file.
 *
 *  Teacher: Mrs. Ishman
 *  Collab: None
 *  Period: 2nd
 *  @author Mithul Manivannan
 *  Date: 10/21/19
 */

import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String firstFile = getFileName("Enter file to read: ");
        WordList parsedFile1 = new WordList(firstFile);
        print("Word list from " + firstFile, parsedFile1.getWords());

        String nextFile = getFileName("Enter another file to read to union with first: ");
        WordList parsedFile2 = new WordList(nextFile);
        Set<String> list = parsedFile2.getWords();

        print("Word list from " + nextFile, list);
        print("All words from " + firstFile + " and " + nextFile, parsedFile1.getAllWords(list));

        nextFile = getFileName("Enter another file to read to intersect with first: ");
        parsedFile2.setFile(nextFile);
        list = parsedFile2.getWords();

        print("Word list from " + nextFile, list);
        print("Common words from " + firstFile + " and " + nextFile, parsedFile1.getCommonWords(list));
    }

    /**
     *	returns if the user wants to continue
     *	@return a boolean if the user wants to continue the program
     */
    private static boolean contin()
    {
        System.out.print("Do you want to try again (y/n)? ");
        Scanner in = new Scanner(System.in);
        return in.next().matches("[yY].*");
    }

    /**
     *	prints statements to terminal
     *	@param statement A stirng to tell the user what user file's list is going to be printed
     *	@param set the set to print
     */
    private static void print(String statement, Set<String> set)
    {
        System.out.println(statement);
        System.out.println(set);
        System.out.println();
    }

    /**
     *	loops until the user enters an existing file
     *	@param statement the statement to print in terminal to get input from the user
     *	@return a file name from the user
     */
    private static String getFileName(String statement)
    {
        Scanner in = new Scanner(System.in);
        System.out.print(statement);
        String name = in.next();
        File inputFile = new File(name);
        while (!inputFile.exists())
        {
            System.out.print(name + " does not exist. ");
            if(!contin())
                System.exit(0);
            System.out.print("\n" + statement);
            name = in.next();
            inputFile = new File(name);
        }
        return name;
    }
}
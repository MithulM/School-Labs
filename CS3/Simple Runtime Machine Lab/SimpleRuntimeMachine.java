/**
 * I don't know what indentations you are talking about. It looks fine too me. 
 * 
 * Class that runs a programming language. It can print add memmory to ram, and add.
 * 
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 4/10/2020
 */

import java.util.*;
import java.io.*;

/**
 * SimpleRuntimeMachine excutes programs in a custom programming language.
 * This object will have an array of length 10 to represent the state of RAM.
 * It will also have a Stack of strings acting as the program stack.
 * Commands will be pushed and popped off the stack as the program executes.
 */
public class SimpleRuntimeMachine
{
  // These are your ONLY instance variables!
  // DO NOT USE ANY OTHER DATA STRUCTURES IN THIS LAB!
  private final int RAM_CAPACITY = 10;
  private int[] ram;
  private Stack<String> programStack;
  
  /**
   * This Construtor takes in a file name representing a program.
   * The construtor will then create a connection to the file using Scanner
   * and process the commands one line at a time.
   * 
   * @param fileName the name of a file, representing the program to execute
   */
  public SimpleRuntimeMachine(String fileName) throws IOException
  {
    ram = new int[RAM_CAPACITY];
    programStack = new Stack<String>();
    processCommands(fileName);
  }
  
  /**
   * processCommands method will process commands of the program
   * one line at a time, calling other methods as needed.
   * 
   * @param fileName the name of a file, representing the program to execute
   */
  public void processCommands(String fileName) throws IOException
  {
    Scanner fileReader = new Scanner(new File(fileName));
	while (fileReader.hasNextLine()) {
		Scanner input = new Scanner(fileReader.nextLine());
		switch (input.next()) {
			case "begin":
				programStack.add("begin");
				System.out.println("Starting the program...");
				break;
			case "end":
				programStack.pop();
				System.out.println("Stopping the program...");
				System.out.println("Runtime Machine DONE!");
				break;
			case "print":
				print(input);
				break;
			case "copy":
				copyOp(input);
				break;
		}
	}
  }
  
  /**
   * print method handles the print command in this language.
   * print displays information to the output window(System.out).
   * 
   * @param input the scanner object going through your current command
   */
  private void print(Scanner input)
  {
    programStack.add("print");
	while (input.hasNext()){
		String token = input.next();
		if(token.equals("add"))
			addOp(input);
		else
			programStack.push(token);
	}
	StringBuilder build = new StringBuilder();
	while (!programStack.peek().equals("print")) {
		String popped = programStack.pop();
		if (popped.charAt(0) == 'i' && Character.isDigit(popped.charAt(1)))
			build.insert(0, ram[popped.charAt(1)-48]);
		else
			build.insert(0, popped + " ");
	}
	build = new StringBuilder(build.toString());
	System.out.println(build);
	programStack.pop();
  }
  
  /**
   * copyOp method handles the copy command in this language.
   * copyOp copies an int value, constant or value stored in RAM,
   * into RAM at a certain index.
   * 
   * @param input the scanner object going through your current command
   */
  private void copyOp(Scanner input)
  {
    programStack.add("copy");
	while(input.hasNext())
	{
		String token = input.next();
		if(token.equals("add"))
			addOp(input);
		else
			programStack.push(token);
	}
	while(!programStack.peek().equals("copy"))
	{
	    String word = programStack.pop();
		int valueToAddRam;
		if(word.charAt(0) == 'i' && Character.isDigit(word.charAt(1)))
			valueToAddRam = ram[word.charAt(1)-48];
		else
			valueToAddRam = Integer.parseInt(word);
		String memLocal = programStack.pop();
		ram[memLocal.charAt(1)-48] = valueToAddRam;
	}
	programStack.pop();
  }
  
  /**
   * addOp method handles the add command in this language.
   * 
   * @param input the scanner object going through your current command
   */ 
  private void addOp(Scanner input)
  {
    programStack.add("add");
	String first = input.next();
	int val2;
	int val1;
	if(first.equals("add"))
		addOp(input);
	else
		programStack.push(first);
	String second = input.next();
	if(second.equals("add"))
	    addOp(input);
	else
		programStack.push(second);
	if(programStack.peek().charAt(0) == 'i' && Character.isDigit(programStack.peek().charAt(1)))
		val2 = ram[programStack.pop().charAt(1)-48];
	else
		val2 = Integer.parseInt(programStack.pop());
	if(programStack.peek().charAt(0) == 'i' && Character.isDigit(programStack.peek().charAt(1)))
		val1 = ram[programStack.pop().charAt(1)-48];
	else
		val1 = Integer.parseInt(programStack.pop());
	int sum = val1 + val2;
	programStack.pop();
	programStack.push(Integer.toString(sum));
  }
  
  /**
   * The toString method returns the state of RAM for testing purposes.
   * 
   * @return the current state of RAM
   */
  public String toString()
  {
  	return Arrays.toString(ram);
  }
}
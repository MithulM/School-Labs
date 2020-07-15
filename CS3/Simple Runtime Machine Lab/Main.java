/* Main class used to test SimpleRuntimeMachine
 * Created by Teachers at Plano
 * For use for Computer Science 3 Honors
 * Due Date: 04-7-2020
 * */

import java.util.*;
import java.io.*;

/**
 * This is the tester class for SimpleRuntimeMachine Objects.
 */ 
public class Main
{
  /**
   * This is the main method used to test SimpleRuntimeMachine.java.
   * 
   * How to use this main method.  As you complete method in SimpleRuntimeMachine,
   * move the /* to test more functionality.  The sample output in the instructions
   * has uncommented all of the code in the main method.
   * 
   * @param args this parameter is not being used in this lab
   */ 
  public static void main(String[] args) throws IOException
  {
  	
  	System.out.println ("Executing Program0.txt");
  	SimpleRuntimeMachine run0 = new SimpleRuntimeMachine("Program0.txt");
  	System.out.println ("\nState of RAM for Program0.txt\n"+run0+"\n");

  	System.out.println ("Executing ProgramPrint1.txt");
  	SimpleRuntimeMachine run1 = new SimpleRuntimeMachine("ProgramPrint1.txt");
  	System.out.println ("\nState of RAM for ProgramPrint1.txt\n"+run1+"\n");
  	
  	System.out.println ("Executing ProgramPrint2.txt");
  	SimpleRuntimeMachine run2 = new SimpleRuntimeMachine("ProgramPrint2.txt");
  	System.out.println ("\nState of RAM for ProgramPrint2.txt\n"+run2+"\n");
	
  	System.out.println ("Executing ProgramCopy1.txt");
  	SimpleRuntimeMachine run3 = new SimpleRuntimeMachine("ProgramCopy1.txt");
  	System.out.println ("\nState of RAM for ProgramCopy1.txt\n"+run3+"\n");
  	
  	System.out.println ("Executing ProgramCopy2.txt");
  	SimpleRuntimeMachine run4 = new SimpleRuntimeMachine("ProgramCopy2.txt");
  	System.out.println ("\nState of RAM for ProgramCopy2.txt\n"+run4+"\n");
  	
  	System.out.println ("Executing ProgramAdd1.txt");
  	SimpleRuntimeMachine run5 = new SimpleRuntimeMachine("ProgramAdd1.txt");
  	System.out.println ("\nState of RAM for ProgramAdd1.txt\n"+run5+"\n");
  	
  	System.out.println ("Executing ProgramAdd2.txt");
  	SimpleRuntimeMachine run6 = new SimpleRuntimeMachine("ProgramAdd2.txt");
  	System.out.println ("\nState of RAM for ProgramAdd2.txt\n"+run6+"\n");
  	
  	System.out.println ("Executing ProgramAdd3.txt");
  	SimpleRuntimeMachine run7 = new SimpleRuntimeMachine("ProgramAdd3.txt");
  	System.out.println ("\nState of RAM for ProgramAdd3.txt\n"+run7+"\n");

  	System.out.println ("Executing ProgramAdd4.txt");
  	SimpleRuntimeMachine run8 = new SimpleRuntimeMachine("ProgramAdd4.txt");
  	System.out.println ("\nState of RAM for ProgramAdd4.txt\n"+run8+"\n");

  	System.out.println ("Executing LongCommand.txt");
  	SimpleRuntimeMachine run9 = new SimpleRuntimeMachine("LongCommand.txt");
  	System.out.println ("\nState of RAM for LongCommand.txt\n"+run9+"\n");

  	
  }
}
/**
* Reads from a file for given data and generates random data to scan and print a 
* histogram counting the number of characters.
* 
* @author Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Ishman
* Period: 2
* Due Date: 3/31/2020
*/

import java.util.*;
import java.io.*;
public class Main 
{

public static void main(String[] args) throws IOException
  {
    Scanner in = new Scanner(new File("given_data.txt"));
    if(!in.hasNextLine())
        System.exit(0);
    Histogram histogram = new Histogram(in.nextLine());
    System.out.println(histogram.toString());
    while(in.hasNextLine())
    {
        String line = in.nextLine();
        System.out.println(line);
        histogram.setSentence(line);
        System.out.println(histogram.toString());
    }
    
    
    StringBuilder sampleData = new StringBuilder();
    int lines = (int) (Math.random() * 7) + 2;
    for(int i = 0; i < lines; i++)
        sampleData.append(randChars((int) (Math.random() * 36) + 5, 33, 126) + "\n");
    Scanner in1 = new Scanner(sampleData.toString());
    String line1 = in1.nextLine();
    System.out.println(line1);
    Histogram histogram1 = new Histogram(in1.nextLine());
    System.out.println(histogram1.toString());
    while(in1.hasNextLine())
    {
        String line = in1.nextLine();
        System.out.println(line);
        histogram1.setSentence(line);
        System.out.println(histogram1.toString());
    }
  }
  
  /**
   * randomly creates a line with random characters with ascii from start to end
   **/
  public static String randChars(int length, int start, int end)
  {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < length; i++)
            output.append((char) (Math.random() * (end - start + 1) + start) + " ");
        return output.toString();
  }
}
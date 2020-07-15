/* Main class used to test BirdMigration
 * Created by Plano ISD CS Teachers 
 * For use for Computer Science 3 Honors
 * Due Date: 04-16-2020
 */
 
import java.io.*;
import java.util.*;

public class Main
{
  public static void main(String[] args)
  {
    // Test a single Bird
    Bird test = new Bird("Mallard1", 23);
    System.out.println("test bird: " + test);
    test.makeLeader();
    System.out.println("test bird: " + test);
    System.out.println();
    
    // Read in data from file
    List<Bird> flock = loadBirdData("flock.txt");
    
    // Create a BirdMigration object and add birds
    BirdMigration firstFlock = new BirdMigration();
    System.out.println("firstFlock: " + firstFlock);
    for (Bird birdie : flock)
    {
      firstFlock.addBird(birdie);
    }
    System.out.println("firstFlock: " + firstFlock);
    System.out.println();
    
    // Test the leader falling back
    System.out.println("***Testing leader falling back***");
    Bird newLeader = firstFlock.leaderFallBack();
    Bird expected = flock.get(1);
    if (newLeader.equals(expected))
      System.out.println("Correct: new leader is " + newLeader);
    else
      System.out.println("ERROR: new leader not " + expected + "; is " + newLeader);
    System.out.println("After first fall back: " + firstFlock);
    System.out.println();

    newLeader = firstFlock.leaderFallBack();
    expected = flock.get(2);
    if (newLeader.equals(expected))
      System.out.println("Correct: new leader is " + newLeader);
    else
      System.out.println("ERROR: new leader not " + expected + "; is " + newLeader);
    System.out.println("After second fall back: " + firstFlock);
    System.out.println();
    
    // Test with empty list
    BirdMigration emptyFlock = new BirdMigration();
    newLeader = emptyFlock.leaderFallBack();
    if (newLeader == null)
      System.out.println("Correct: new leader of empty list is null");
    else
      System.out.println("ERROR: new leader of empty list not null; is " + newLeader);
    System.out.println();

    // Test removeWeakestBird
    System.out.println("***Testing remove weakest bird***");
    Bird weakest = firstFlock.removeWeakestBird();
    expected = flock.get(2);
    if (weakest.equals(expected))
      System.out.println("Correct: weakest is " + weakest);
    else
      System.out.println("ERROR: weakest not " + expected + "; is " + weakest);
    System.out.println("After first remove weakest: " + firstFlock);
    System.out.println();

    weakest = firstFlock.removeWeakestBird();
    expected = flock.get(19);
    if (weakest.equals(expected))
      System.out.println("Correct: weakest is " + weakest);
    else
      System.out.println("ERROR: weakest not " + expected + "; is " + weakest);
    System.out.println("After second remove weakest: " + firstFlock);
    System.out.println();

    // Test with empty list
    weakest = emptyFlock.removeWeakestBird();
    if (weakest == null)
      System.out.println("Correct: weakest of empty list is null");
    else
      System.out.println("ERROR: weakest of empty list not null; is " + weakest);
    System.out.println();
    
    // Test splitting an empty flock
    BirdMigration splitFlock = emptyFlock.splitFormation();
    if (splitFlock.size() == 0)
      System.out.println("Correct: splitting empty flock returned empty flock");
    else
      System.out.println("ERROR: splitting empty flock returned flock size " + splitFlock.size());
    System.out.println("After split, emptyFlock: " + emptyFlock);
    System.out.println("After split, splitFlock: " + splitFlock);
    System.out.println();
   
    // Test splitting a large flock (even size)
    int expectedSize = firstFlock.size() / 2;
    splitFlock = firstFlock.splitFormation();
    if (splitFlock.size() == expectedSize)
      System.out.println("Correct: splitting large flock returned correct size flock");
    else
      System.out.println("ERROR: splitting large flock returned flock size " + splitFlock.size());
    System.out.println("After 1st split, firstFlock: " + firstFlock);
    System.out.println("After 1st split, splitFlock: " + splitFlock);
    System.out.println();

    // Test splitting a large flock (odd size)
    expectedSize = firstFlock.size() / 2;
    splitFlock = firstFlock.splitFormation();
    if (splitFlock.size() == expectedSize)
      System.out.println("Correct: splitting large flock returned correct size flock");
    else
      System.out.println("ERROR: splitting large flock returned flock size " + splitFlock.size());
    System.out.println("After 2nd split, firstFlock: " + firstFlock);
    System.out.println("After 2nd split, splitFlock: " + splitFlock);
    System.out.println();
  }
  
  /**
   * Generates and returns a list of birds from the given file
   * @param fileName the name of the text file with birds
   * @return list of birds from the file
   */
  private static List<Bird> loadBirdData(String fileName)
  {
    List<Bird> list = new LinkedList<>();
    try
    {
      Scanner input = new Scanner(new File(fileName));
      while (input.hasNextLine())
      {
        int strength = input.nextInt();
        String type = input.nextLine().trim();
        Bird birdie = new Bird(type, strength);
        list.add(birdie);
      }
      input.close();
    }
    catch (FileNotFoundException exc)
    {
      System.out.println("Error reading " + fileName + "\n" + exc.getMessage());
      exc.printStackTrace();
      System.exit(0);
    }
    return list;
  }
}
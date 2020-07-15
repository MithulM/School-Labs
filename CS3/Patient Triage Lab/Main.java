/**
 * This class tests if the PatientTriage methods work properly. 
 * 
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 4/26/2020
 */

import java.util.*;
import java.io.*;

/**
 * This class is used to test the code of the Patient Triage
 * Lab for computer science 3 honors for Plano ISD.
 * 
 * @author Jonathan Yee, Tracy Ishman, Ann Horton
 * Date: 4/19/2020
 */
public class Main 
{
  /**
   * This method is used to test the code of the Patient Triage
   * Lab for computer science 3 honors for Plano ISD.
   *
   * @param args this parameter will not be used
   */ 
  public static void main(String[] args) 
  {
    //Testing the Patient Class's compareTo method
    System.out.println("Verifying your compareTo Priority is correct:");
    Patient surgery1 = new Patient("A", 40, true, true, true);
    Patient surgery2 = new Patient("Z", 80, true, true, true);
    Patient seniorVent = new Patient("B", 51, true, true, false);
    Patient childVent = new Patient("C", 11, true, true, false);
    Patient vent = new Patient("D", 30, false, true, false);
    Patient vent2 = new Patient("ABC", 30, false, true, false);
    Patient vent12 = new Patient("E", 12, false, true, false);
    Patient vent50 = new Patient("F", 50, false, true, false);
    Patient bed = new Patient("G", 60, true, false, false);
    Patient none = new Patient("H", 90, false, false, false);
    
    System.out.println("All test cases should be true!");
    System.out.println("Case 01: " + (surgery1.compareTo(surgery2) < 0));
    System.out.println("Case 02: " + (seniorVent.compareTo(surgery2) > 0));
    System.out.println("Case 03: " + (seniorVent.compareTo(vent50) < 0));
    System.out.println("Case 04: " + (childVent.compareTo(seniorVent) > 0));
    System.out.println("Case 05: " + (childVent.compareTo(vent12) < 0));
    System.out.println("Case 06: " + (none.compareTo(bed) > 0));
    System.out.println("Case 07: " + (vent.compareTo(vent12) < 0));
    System.out.println("Case 08: " + (vent.compareTo(vent50) < 0));
    System.out.println("Case 09: " + (vent.compareTo(bed) < 0));
    System.out.println("Case 10: " + (vent.compareTo(vent2) > 0));
    System.out.println("If you see a false, look at the Patient Objects used in that case!");
    
    // Testing PatientTriage Constructor, addPatient, and toString Method
    PatientTriage test = new PatientTriage();
    System.out.println("\nLoading Patients from Patients.txt!\n");
    loadPatientData(test, "Patients.txt");
    System.out.println("The Priority Queue of this PatientTriage object contains the following Patients:");
    System.out.println( test.getPQueue() );
    System.out.println();
    System.out.println("The Patients List in order is:");
    System.out.println(test);
    
    // Testing processPatient Method
    System.out.println("\nTesting processPatient method");
    Patient processed = test.processPatient();
    System.out.println(processed.getName() + " is getting aid from the hospital!");
    processed = test.processPatient();
    System.out.println(processed.getName() + " is getting aid from the hospital!");
    processed = test.processPatient();
    System.out.println(processed.getName() + " is getting aid from the hospital!");
    
    System.out.println("\nThe Patients List order is now:");
    System.out.println(test);
    
    // Testing process on an empty list.
    PatientTriage newList = new PatientTriage();
    System.out.println("Expected null value as there are no patients: " + newList.processPatient());
  }
  
    /** Creates a list of patients to use for testing from the given file
   *  @param fileName name of file containing patient data
   *  @return list of Patient objects read from fileName
   */
  private static void loadPatientData(PatientTriage list, String fileName)
  {
    try
    {
      Scanner input = new Scanner(new File(fileName));
      while (input.hasNextLine())
      {
        String name = input.nextLine().trim();
        int age = Integer.parseInt(input.nextLine().trim());
        boolean needsBed = input.nextBoolean();
        boolean needsVent = input.nextBoolean();
        boolean needsSurgery = input.nextBoolean();
        input.nextLine();
        Patient pat = new Patient(name, age, needsBed, needsVent, needsSurgery);
        list.addPatient(pat);
      }
      input.close();
    }
    catch (FileNotFoundException exc)
    {
      System.out.println("Error reading " + fileName + "\n" 
        + exc.getMessage());
      exc.printStackTrace();
      System.exit(0);
    }
  }
}
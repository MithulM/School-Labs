/**
* Main method that tests all methods in HospitalDispatch class.
* 
* @author Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Ishman
* Period: 2
* Due Date: 4/2/2020
*/

import java.util.*;
import java.io.*;


public class Main
{
  /** constants for testing */
  public static final int NUM_PATIENTS_TO_ADMIT = 25;
  public static final int NUM_PATIENTS_TO_DISCHARGE = 5;
  
  /** class variables for testing */
  private static List<Hospital> areaHospitals;
  private static List<Patient> patientList;
  private static HospitalDispatch dispatcher;
  
  public static void main(String[] args) 
  {
    // Load test data and create HospitalDispatch object
    areaHospitals = loadHospitalData("hospitals.txt");
    patientList = loadPatientData("patients.txt");
    dispatcher = new HospitalDispatch();
    
    // Begin testing if all 3 objects created successfully
    if (areaHospitals != null && patientList != null && dispatcher != null)
    {
      // Make sure nothing happens if try to admit patient empty map
      testEmptyMap();
  
      // Add all of the hospitals and then test admitting patients
      addHospitals();
      testAdmittingPatients();
      
      // Test discharging patients
      testDischarginPatients();
    }
    else if (areaHospitals == null)
      System.out.println("Error loading hospital data. Exiting program.");
    else if (patientList == null)
      System.out.println("Error loading patient data. Ending program.");
    else 
      System.out.println("Error creating HospitalDispatch object. Ending program.");
  }

  /** Test tyring to add a patient to an empty map. Should be unsuccessful
   */
  private static void testEmptyMap()
  {
    Patient firstPatient = patientList.get(0);
    Hospital patientHospital = dispatcher.admitPatient(firstPatient);
    System.out.println("Dispatcher after admitting to empty map:"); 
    System.out.println(dispatcher);
    if (patientHospital == null)
      System.out.println("Correct: unable to admit " + firstPatient);
    else  
      System.out.println("Error: empty map, admitted to: " + patientHospital);
    System.out.println();
  }
  
  /** Add all of the hospitals to the dispatcher
   */
  private static void addHospitals()
  {
    for (Hospital hosp : areaHospitals)
    {
      dispatcher.addHospital(hosp);
    }
    System.out.println("After adding all hospitals:");
    System.out.println(dispatcher);
    System.out.println();
  }
  
  /** Test admitting patients
   */
  private static void testAdmittingPatients()
  {
    Patient firstPatient = patientList.get(0);
    Hospital patientHospital = dispatcher.admitPatient(firstPatient);
    System.out.println("Dispatcher after admitting patient w/non-existent hospital:"); 
    System.out.println(dispatcher);
    if (patientHospital == null)
      System.out.println("Error: unable to admit " + firstPatient);
    else  
      System.out.println("Correct: " + firstPatient + " admitted to: " + patientHospital);
    System.out.println();
    
    // Admit more patients
    for (int index = 1; index < NUM_PATIENTS_TO_ADMIT; index++)
    {
      Patient pat = patientList.get(index);
      patientHospital = dispatcher.admitPatient(pat);
      System.out.print("Patient " + pat.getName());
      if (patientHospital == null)
        System.out.println(": ERROR unable to admit to hospital");
      else if (patientHospital.getName().equals(pat.getClosestHospital()))
        System.out.println(": admitted to closest hospital");
      else
        System.out.println(": admitted to " + patientHospital);
    }
    System.out.println("\nDispatcher after adding " + NUM_PATIENTS_TO_ADMIT + " patients:");
    System.out.println(dispatcher);
    System.out.println();
  }
  
  /** Test discharging of patients
   */
  private static void testDischarginPatients()
  {
    // Discharge some patients
    for (int index = 0; index < NUM_PATIENTS_TO_DISCHARGE; index++)
    {
      Patient pat = patientList.get(index);
      if (dispatcher.dischargePatient(pat))
        System.out.println("Discharged: " + pat);
      else
        System.out.println("ERROR discharging " + pat);
    }
    System.out.println("\nDispatcher after discharging " + NUM_PATIENTS_TO_DISCHARGE + " patients:");
    System.out.println(dispatcher);
    System.out.println();
    
    // Test discharging a non-existent patient
    Patient lastPatient = patientList.get(patientList.size() - 1);
    System.out.println("Discharging non-existent patient: " + lastPatient);
    boolean wasDischarged = dispatcher.dischargePatient(lastPatient);
    if (wasDischarged)
      System.out.println("ERROR: discharged non-existent patient");
    else
      System.out.println("Correctly did not discharge non-existent patient");
    System.out.println();
  }
  
  /** Creates a list of hospitals to use for testing from the given file
   *  @param fileName name of file containing hospital data
   *  @return list of Hospital objects read from fileName
   */
  private static List<Hospital> loadHospitalData(String fileName)
  {
    List<Hospital> list = new ArrayList<>();
    try
    {
      Scanner input = new Scanner(new File(fileName));
      while (input.hasNextLine())
      {
        String name = input.nextLine().trim();
        int numBeds = input.nextInt();
        int numVent = input.nextInt();
        input.nextLine();
        Hospital hosp = new Hospital(name, numBeds, numVent);
        list.add(hosp);
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
    return list;
  }

  /** Creates a list of patients to use for testing from the given file
   *  @param fileName name of file containing patient data
   *  @return list of Patient objects read from fileName
   */
  private static List<Patient> loadPatientData(String fileName)
  {
    List<Patient> list = new ArrayList<>();
    try
    {
      Scanner input = new Scanner(new File(fileName));
      while (input.hasNextLine())
      {
        String name = input.nextLine().trim();
        boolean needsBed = input.nextBoolean();
        boolean needsVent = input.nextBoolean();
        String hospName = input.nextLine().trim();
        Patient pat = new Patient(name, needsBed, needsVent, hospName);
        list.add(pat);
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
    return list;
  }
}
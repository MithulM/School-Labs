/**
 *  The Patient class repsents an individual patient at a hospital,
 *  maintaining whether or not the patient needs a bed or ventilator,
 *  and the name of the closest hospital.
 *  @author Tracy Ishman, Ann Horton, Jonathan Yee
 *  Date: 3/25/2020
 */
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Patient implements Comparable<Patient>
{
  /** name of the Patient, resource needs, and closest hospital */
  private String name;
  private boolean needsBed;
  private boolean needsVentilator;
  private String nameOfClosestHospital;  
  
  /** Creates a new hospital with the given number of resources
   *  @param name the name of the hospital
   *  @param needsBed true if patient needs hospital bed 
   *  @param needsVentilator true if patient needs ventilator 
   *  @param hospital the closest hospital to this patient
   */
  public Patient(String name, boolean needsBed, boolean needsVentilator, String hospital)
  {
    this.name = name;
    this.needsBed = needsBed;
    this.needsVentilator = needsVentilator;
    this.nameOfClosestHospital = hospital;
  }
  
  /** Retrieves the name of the patient
   *  @return name of the patient
   */
  public String getName()
  {
    return name;
  }
  
  /** Retrieves whether or not patient needs a bed for in-patient services
   *  @return true if patient requires a bed; false otherwise
   */
  public boolean needsBed()
  {
    return needsBed;
  }
  
  /** Retrieves whether or not patient needs a ventilator
   *  @return true if patient requires a ventilator; false otherwise
   */
  public boolean needsVentilator()
  {
    return needsVentilator;
  }

  /** Retrieves name of hospital closest to the patient
   *  @return name of closest hospital
   */
  public String getClosestHospital()
  {
    return nameOfClosestHospital;
  }
  
  /** Determines whether or not this patient is the same as other
   *  @param other the other patient to check
   *  @return true if this and other are the same; false otherwise
   */
  @Override
  public boolean equals(Object other)
  {
    if (other == null || !(other instanceof Patient))
      return false;
    
    Patient otherPat = (Patient) other;
    return name.equals(otherPat.name);
  }
  
  /** Determines order of this and other
   *  @param other the other patient to compare against
   *  @return 0 if this.equals(other), < 1 if this < other, > 1 if this > other
   */
  @Override
  public int compareTo(Patient other)
  {
    return name.compareTo(other.name);
  }
  
  /** Calculates and returns a hashcode for this patient
   *  @return hashcode
   */
  @Override 
  public int hashCode()
  {
    return name.hashCode();
  }
  
  /** Builds and returns this patient's information 
   *  @return string containing the patient data
   */
  @Override
  public String toString()
  {
    return String.format("%s bed-%b vent-%b %s",
      name, needsBed, needsVentilator, nameOfClosestHospital);
  }
}
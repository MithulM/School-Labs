/**
 *  The Hospital class repsents an individual hospital, maintaining its
 *  inventory of beds and ventilator machines, as well as the number of beds  
 *  and ventilator machines that are currently being used by a patient.
 *  @author Tracy Ishman, Ann Horton, Jonathan Yee
 *  Date: 3/25/2020
 */
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Hospital
{
  /** constants for hospitals created with 1-parameter constructor */
  public static final int DEFAULT_BED_CAPACITY = 100;
  public static final int DEFAULT_VENTILATOR_CAPACITY = 40;
  
  /** name of the hospital and inventory of resources */
  private String name;
  private int totalBeds;
  private int bedsInUse;
  private int totalVentilators;
  private int ventInUse;
  
  /** Creates a new hospital with a default number of resources
   *  @param name the name of the hospital
   */
  public Hospital(String name)
  {
    this(name, DEFAULT_BED_CAPACITY, DEFAULT_VENTILATOR_CAPACITY);
  }
  
  /** Creates a new hospital with the given number of resources
   *  @param name the name of the hospital
   *  @param numBeds the total number of beds in the hospital 
   *  @param numVentilators the total number of ventilators in inventory
   */
  public Hospital(String name, int numBeds, int numVentilators)
  {
    this.name = name;
    totalBeds = numBeds;
    bedsInUse = 0;
    totalVentilators = numVentilators;
    ventInUse = 0;
  }
  
  /** Retrieves the name of the hospital
   *  @return name of the hospital
   */
  public String getName()
  {
    return name;
  }
  
  /** Retrieves the total bed capacity in the hospital
   *  @return total number of beds in the hospital
   */
  public int getBedCapacity()
  {
    return totalBeds;
  }
  
  /** Determines whether or not there is a bed available
   *  @return true not all beds filled, false otherwise
   */
  public boolean hasBedAvailable()
  {
    return bedsInUse < totalBeds;
  }
  
  /** Fills an available bed 
   *  @return true if a bed available; false if no beds available
   */
  public boolean fillBed()
  {
    if (hasBedAvailable())
    {
      bedsInUse++;
      return true;
    }
    return false;
  }
  
  /** Make a bed available for another patient
   */
  public void emptyBed()
  {
    bedsInUse--;
  }
  
   /** Retrieves the total number of ventilators in inventory 
   *  @return total number of ventilators in the hospital
   */
  public int getVentilatorInventory()
  {
    return totalVentilators;
  }
  
  /** Determines whether or not there is a ventilator available
   *  @return true not all ventilators in use, false otherwise
   */
  public boolean hasVentilatorAvailable()
  {
    return ventInUse < totalVentilators;
  }

  /** Takes a ventilator from inventory for a patient 
   *  @return true if ventilator is available; false if none available
   */
  public boolean useVentilator()
  {
    if (hasVentilatorAvailable())
    {
      ventInUse++;
      return true;
    }
    return false;
  }
  
  /** Returns a ventilator machine to inventory
   */
  public void returnVentilator()
  {
    ventInUse--;
  }
  
  /** Determines whether or not this hospital is the same as other
   *  @param other the other Hospital to check
   *  @return true if this and other are the same; false otherwise
   */
  @Override
  public boolean equals(Object other)
  {
    if (other == null || !(other instanceof Hospital))
      return false;
    
    Hospital otherHosp = (Hospital) other;
    return name.equals(otherHosp.name);
  }
  
  /** Calculates and returns a hashcode for this hospital
   *  @return hashcode
   */
  @Override 
  public int hashCode()
  {
    return name.hashCode();
  }
  
  /** Builds and returns this hospital's information 
   *  @return string containing the hospital data
   */
  @Override
  public String toString()
  {
    return String.format("%s %d of %d beds %d of %d ventilators",
      name, bedsInUse, totalBeds, ventInUse, totalVentilators);
  }
}
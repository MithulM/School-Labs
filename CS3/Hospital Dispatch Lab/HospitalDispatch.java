/**
* Class that adds patients to the closest hospital that is avalable.
* 
* @author Mithul Manivannan
* Collaborators: None
* Teacher Name: Mrs. Ishman
* Period: 2
* Due Date: 4/2/2020
*/

import java.util.*;

public class HospitalDispatch 
{
  /** mapping of hospitals to their set of patients */
  private HashMap<Hospital, TreeSet<Patient>> areaHospitals;
  
  /**
   * creates new hashmap
   */
  public HospitalDispatch()
  {
    areaHospitals = new HashMap<>();
  }
  
  /**
   * adds a hopital to a list
   * @param newHospital the hospital to add
   * @return if hospital was added
   */
  public boolean addHospital(Hospital newHospital)
  {
    boolean has = areaHospitals.containsKey(newHospital);
    if(!has)
        areaHospitals.put(newHospital, new TreeSet<>());
    return !has;
  }
  
  /**
   * admits a patient to a hospital
   * @param newPatient the patient to add to a hospital
   * @return the hospital the patient was added too. null if the patient was not added.
   */
  public Hospital admitPatient(Patient newPatient)
  {
    Hospital closestHosp = findHospital(newPatient);
    if(closestHosp == null){
        return null;
    }
    if(hasResources(newPatient, closestHosp)){
        if(newPatient.needsBed())
            closestHosp.fillBed();
        if(newPatient.needsVentilator())
            closestHosp.useVentilator();
        areaHospitals.get(closestHosp).add(newPatient);
        return closestHosp;
    }
    return null;
  }
  
  /**
   * removes a person from a hopital
   * @param patient to remove from the hospital
   * @return if the patient was removed from a hospital
   */
  public boolean dischargePatient(Patient patient)
  {
    Hospital closestHosp = null;
    for(Map.Entry<Hospital, TreeSet<Patient>> area: areaHospitals.entrySet())
        if(area.getValue().contains(patient))
            closestHosp = area.getKey();
    if(closestHosp == null)
        return false;
    if(patient.needsBed())
        closestHosp.emptyBed();
    if(patient.needsVentilator())
        closestHosp.returnVentilator();
    areaHospitals.get(closestHosp).remove(patient);
    return true;
  }

  /**
   * a string with information on the mapping
   * @return a mapping and size of the areaHospitals map.
   */
  @Override
  public String toString()
  {
    StringBuilder build = new StringBuilder();
    build.append("Number of mappings: ");
    build.append("" + areaHospitals.size());
    build.append("\n");
    for(Map.Entry<Hospital, TreeSet<Patient>> area: areaHospitals.entrySet())
    {
        build.append(area.getKey().toString());
        build.append(" => ");
        build.append(area.getValue().toString());
        build.append("\n");
    }
    return build.toString();
  }

  /**
   * finds a hospital for a patient
   * @param pat the patient to find a hospital for
   * @return hospital for the patient to go to
   */
 private Hospital findHospital(Patient pat)
  {
    for(Map.Entry<Hospital, TreeSet<Patient>> area: areaHospitals.entrySet())
        if(area.getKey().getName().equals(pat.getClosestHospital()))
            if(hasResources(pat, area.getKey()))
                return area.getKey();
    for(Map.Entry<Hospital, TreeSet<Patient>> area: areaHospitals.entrySet())
        if(hasResources(pat, area.getKey()))
            return area.getKey();
    return null;
  }
  
  /**
   * checks if there are enough resources
   * @param patient is the patient to check if a hospital has enough resources.
   * @param hospital the hospital to check if it has enough resources for patient
   * @returns if a hospital can admit a patient
   */
  private boolean hasResources(Patient patient, Hospital hospital)
  {
    boolean goodBed = false;  
    if(patient.needsBed())
    {
        if(hospital.hasBedAvailable())
            goodBed = true;
    }
    else
        goodBed = true;
    boolean goodVent = false;  
    if(patient.needsVentilator())
    {
        if(hospital.hasVentilatorAvailable())
            goodVent = true;
    }
    else
        goodVent = true;
    return goodVent && goodBed;
  }
}
/**
 * This class prioritizes the the patients and treats them in order. It adds patients
 * and removes them.
 * 
 *
 * @author Mithul Manivannan
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 2
 * Due Date: 4/26/2020
 */
import java.util.Queue;
import java.util.PriorityQueue;


public class PatientTriage {
	/* List of patient waiting to be processed*/
	private Queue<Patient> patientList;

	/**
	 * Constructs the patientList as a priority queue which
	 * retrieves items using the comparator when poll()
	 * is called.
	 */
	public PatientTriage() {
		patientList = new PriorityQueue<>();
	}

	/**
	 * Adds the patient to the patient list.
	 * @param p patient to add to list
	 */
	public void addPatient(Patient p) {
		patientList.add(p);
	}

	/**
	 * Considers a patient processed, so removes highest priority
	 * patient from queue and returns it
	 * @return patient that was just processed
	 */
	public Patient processPatient() {
		return patientList.poll();
	}

	/**
	 * This method returns the list of patients on the triage list
	 * in the order of priority.
	 *
	 * POST CONDITION:
	 * All patients in patients list at the start of the method are still in
	 * the patientList AND the heap still maintains the order.
	 * Note: While the heap order is maintained, the locations of patients in the
	 * heap might change after this method is completed.
	 *
	 * @return the list of patients on the triage list in the order of priority.
	 */
	public String toString() {
		StringBuilder build = new StringBuilder();
		PriorityQueue<Patient> temp = new PriorityQueue<>(patientList);
		while (!temp.isEmpty())
			build.append(temp.poll()).append("\n");

		return build.toString();
	}

	/**
	 * This method returns the heap of Patients in its raw form.
	 * @return the heap of Patients in its raw form
	 */
	public Queue<Patient> getPQueue() {
		return patientList;
	}

}
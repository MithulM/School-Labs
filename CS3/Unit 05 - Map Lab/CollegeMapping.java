/**
 *  Description:
 *	updateStudent: adds or clears the set for a student.
 *	removeStudent: removes a student if they exist
 *	getAllStudent: returns a string of all students and their college choices
 *	getUniversityChoices: gets the list of university of a student
 *	getStudentAt: returns an arrayList of students who chose a given college
 *
 *  Teacher: Mrs. Ishman
 *  Collab: None
 *  Period: 2nd
 *  @author Mithul Manivannan
 *  Date: 10/28/19
 */

import java.util.*;

class CollegeMapping {

    final static String UNDECIDED = "Undecided";
    private Map<Student, Set<String>> studentChoices;

	/**
	 * Initializes studentChoices to a new TeeMap
	 */
    CollegeMapping(){
        studentChoices = new TreeMap<>();
    }

    /**
     * adds a college to a student
     * @param stu the student to update or add
     * @param univ The university to add to the students
     */
    void updateStudent(Student stu, String univ){
        boolean bad = univ.equals(UNDECIDED) || univ.isEmpty() || univ == null;
        if(!studentChoices.containsKey(stu) || bad)
            studentChoices.put(stu, new TreeSet<String>());
        if(!bad)
            studentChoices.get(stu).add(univ);
    }

    /**
     * removes a student
     * @param stu the student to remove
     */
    void removeStudent(Student stu){
        studentChoices.remove(stu);
    }

    /**
     * returns a mapping of students to with all their chosen colleges
     * @return a string with a mapping of colleges with all students in ID order
     */
    String getAllStudents(){
        String mapping = "";
        for(Map.Entry<Student, Set<String>> map: studentChoices.entrySet())
            mapping += map.getKey().toString() + " => " + map.getValue().toString() + "\n";
        if(mapping.length() == 0)
            return "No college mappings";
        return mapping;
    }

    /**
     * returns the university choices for a given student as a string
     * @param stu student to get university choices
     * @return the university choices for a given student as a string
     */
    String getUniversityChoices(Student stu){
        if(studentChoices.containsKey(stu))
            return studentChoices.get(stu).toString();
        return "Student not in list";
    }

    /**
     * Students for a chosen college
     * @param univ the university to check for students
     * @returns an ArrayList of students that have chosen the given university.
     */
    ArrayList<String> getStudentsAt(String univ){
        ArrayList<String> attending = new ArrayList<>();
        for(Map.Entry<Student, Set<String>> stu : studentChoices.entrySet())
            if((univ.equals(UNDECIDED) && stu.getValue().size() == 0) || stu.getValue().contains(univ))
                attending.add(stu.getKey().toString());
        return attending;
    }
}

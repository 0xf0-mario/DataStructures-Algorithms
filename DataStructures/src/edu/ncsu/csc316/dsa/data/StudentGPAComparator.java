package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 * @author Mario Medel
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order
	 * if multiple students have the same GPA then we use the natural ordering of students
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getGpa() == two.getGpa()) {
			return one.compareTo(two);
		} else if (one.getGpa() < two.getGpa()) {
			return 1;
		}
		return -1;
	}

}

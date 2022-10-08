package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 * @author Mario Medel
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 * @return 1 if student one should come after student two
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getId() == two.getId()) {
			return 0;
		} else if (one.getId() > two.getId()) {
			return 1;
		}
		return -1;
	}

}

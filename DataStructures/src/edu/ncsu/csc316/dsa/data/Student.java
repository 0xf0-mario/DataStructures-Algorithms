package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Mario Medel
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** the first name of THIS student object */
	private String first;
	/** the last name of THIS student object */
	private String last;
	/** the id number of THIS student object */
	private Integer id;
	/** the maximum credit hours that THIS student object is currently taking */
	private int creditHours;
	/** the current Grade Point Average of THIS student object */
	private double gpa;
	/** the unity ID for THIS student object */
	private String unityID;
	
	/**
	 * public constructor for the Student object which takes in the following fields: 
	 * @param fName the first name of this student object
	 * @param lName the last name of this student object
	 * @param id the id number of this student object
	 * @param creditH the credit hours that the student is currently taking
	 * @param gpa the current gpa of this student object
	 * @param uID the unity ID of this student object
	 */
	//FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
	public Student(String fName, String lName, String uID, int id,  double gpa, int creditH) {
		setFirst(fName);
		setLast(lName);
		setId(id);
		setCreditHours(creditH);
		setGpa(gpa);
		setUnityID(uID);
	}
	/**
	 * some tests follow a different format than the .csv files provided so an extra constructor will allow for that construction
	 * @param fName desired first name for this student object
	 * @param lName desired last name for this student object
	 * @param id desired id for this student object
	 * @param creditH desired credit hours for this student object
	 * @param gpa2 desired gpa for this student object
	 * @param uId desired unity ID for this student object
	 */
	public Student(String fName, String lName, int id, int creditH, double gpa2, String uId ){
		this(fName, lName, uId, id, gpa2, creditH);
	}
	/**
	 * sets the first name for this student object
	 * called by the public constructor to set the first name of this student object
	 * @param fName the desired first name to set for this student object
	 * @throws IllegalArgumentException if the first name string is null or an empty string
	 */
	public void setFirst(String fName) {
		if(fName == null || "".equals(fName))
			throw new IllegalArgumentException("Invalid first name");
		
		this.first = fName;
	}
	/**
	 * retrieves the first name for this student object
	 * @return the first name for this student object as a string
	 */
	public String getFirst() { return first; }
	/**
	 * sets the last name for this student object
	 * called by the public constructor to set the last name of this student object
	 * @param lName the desired last name to set for this student object
	 * @throws IllegalArgumentException if the last name string is null or an empty string
	 */
	public void setLast(String lName) {
		if (lName == null || "".equals(lName)) {
			throw new IllegalArgumentException("Invalid last name.");
		}
		
		this.last = lName;
	}
	/**
	 * retrieves the last name for this student object
	 * @return the last name for this student object as a string
	 */
	public String getLast() { return last; } 
	/**
	 * sets the id for this student object
	 * called by the public constructor when building a student object
	 * @param id the desired id number to set for this student object
	 * @throws IllegalArgumentException if the id is a negative integer
	 */
	public void setId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("Invalid id.");
		}
		
		this.id = id;
	}
	/**
	 * retrieves the identification number for this student object
	 * @return the identification number as an integer
	 */
	public int getId() { return id; }
	/**
	 * sets the credit hours for this student object
	 * called by the public constructor when building a student object
	 * @param creditH the desired credit hours to set
	 * @throws IllegalArgument Exception if the credit hours are negative or above 18 (18 is the max)
	 */
	public void setCreditHours(int creditH) {
		if (creditH < 0 || creditH > 18) {
			throw new IllegalArgumentException("Invalid credit hours.");
		}
		
		this.creditHours = creditH;
	}
	/**
	 * retrieves the credit hours for this student object
	 * @return the credit hours as an integer
	 */
	public int getCreditHours() { return creditHours; }
	/**
	 * used to set the Grade Point Average of this student object
	 * @param gpa desired GPA to set
	 * @throws IllegalArgumentException if the gpa is less than zero or greater than 4.0
	 */
	public void setGpa(double gpa) {
//		if (0 > gpa || gpa > 4.0) {
//			throw new IllegalArgumentException("Invalid gpa.");
//		}
		if (0 > gpa) {
			throw new IllegalArgumentException("Invalid gpa.");
		}
		
		this.gpa = gpa;
	}
	/**
	 * retrieves the gpa for this student object
	 * @return the double representation of the GPA
	 */
	public double getGpa() { return gpa; } 
	/**
	 * sets the unity ID for this student object
	 * called by the public constructor during instantiation
	 * @param uID desired unity ID to set
	 * @throws IllegalArgumentException if the String is null or empty
	 */
	public void setUnityID(String uID) {
		if (uID == null || "".equals(uID)) {
			throw new IllegalArgumentException("Invalid unity ID.");
		}
		
		this.unityID = uID;
	}
	/**
	 * retrieves the unity id for this student object
	 * @return the string representation of the unity id
	 */
	public String getUnityID() { return unityID; }
	/**
	 * defines the natural ordering of student objects
	 * based on last name (ascending), first name (ascending), then student ID
	 * @param o the student that this student object will be compared to
	 * @return a positive integer if this object is "greater" than other which means
	 */
	@Override
	public int compareTo(Student o) {
		if (last.equals(o.last)) {
			if (first.equals(o.first)) {
				return id.compareTo(o.id);
			}
			return first.compareTo(o.first);
		}
		return last.compareTo(o.last);
	}
	/**
	 * the string representation for this student object
	 * @return String representation of all the fields for this student object
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}
	/**
	 * generated hashcode method for hashing this student object
	 * @return integer hashcode for this student object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}
	/**
	 * two students are equals if they have the same first and last name and ID
	 * @return true if both students are equivalent
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (!first.equals(other.first))
			return false;
		if (!id.equals(other.id))
			return false;
//		if (!last.equals(other.last))
//			return false;
//		return true;
		return last.equals(other.last);
	} 
	
}

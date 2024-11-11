package src.Entity;

import src.Enums.*;

/**
 * The Staff class is an abstract class that represents a staff member in the hospital system.
 * It extends the User class and includes additional properties specific to hospital staff members, 
 * such as their role and age.
 */
public abstract class Staff extends User {

	/**
     * The role of the staff member, indicating their job title (e.g., Doctor, Nurse, etc.).
     */
	private Role role;

    /**
     * The age of the staff member.
     */
	private int age;

    /**
     * Constructs a Staff object with the specified hospital ID, password, name, gender, role, and age.
     *
     * @param hospitalId The hospital ID of the staff member.
     * @param password The password associated with the staff member's account.
     * @param name The name of the staff member.
     * @param gender The gender of the staff member.
     * @param role The role of the staff member (e.g., Doctor, Nurse).
     * @param age The age of the staff member.
     */

	public Staff(String hospitalId,String password, String name, String gender,Role role, int age) {

		super(hospitalId,password,name,gender);
		this.age = age;
		this.role = role;
	}

    /**
     * Gets the role of the staff member.
     *
     * @return The role of the staff member.
     */
	public Role getRole() {
		return role;
	}

    /**
     * Sets the role of the staff member.
     *
     * @param role The new role of the staff member.
     */
	public void setRole(Role role) {
		this.role = role;
	}

    /**
     * Gets the age of the staff member.
     *
     * @return The age of the staff member.
     */
	public int getAge() {
		return age;
	}

    /**
     * Sets the age of the staff member.
     *
     * @param age The new age of the staff member.
     */
	public void setAge(int age) {
		this.age = age;
	}

}
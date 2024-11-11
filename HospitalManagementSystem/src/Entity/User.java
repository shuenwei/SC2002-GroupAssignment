package src.Entity;

/**
 * The User class represents a generic user in the hospital system.
 * It contains basic user information such as hospital ID, password, name, and gender.
 * Subclasses will implement specific user roles and actions, such as displaying menus.
 */
public abstract class User {

    /**
     * The unique hospital ID of the user.
     */
	private String hospitalId;

    /**
     * The password associated with the user's account.
     * The default value is "password" for the sake of simplicity.
     */
	private String password = "password";

    /**
     * The name of the user.
     */
	private String name;

    /**
     * The gender of the user.
     */
	private String gender;

    /**
     * Constructs a User object with the specified hospital ID, password, name, and gender.
     *
     * @param hospitalId The hospital ID of the user.
     * @param password The password associated with the user's account.
     * @param name The name of the user.
     * @param gender The gender of the user.
     */
    public User(String hospitalId,String password, String name, String gender) {
		this.hospitalId = hospitalId;
		this.password = password;
		this.name = name;
		this.gender = gender;
    }
	
    /**
     * Abstract method that will be implemented by subclasses to display a menu specific to the user type.
     *
     * @param user The user who is viewing the menu.
     */
	public abstract void displayMenu(User user);
	
    /**
     * Gets the hospital ID of the user.
     *
     * @return The hospital ID of the user.
     */
	public String getHospitalId() {
		return this.hospitalId;
	}

    /**
     * Sets the hospital ID of the user.
     *
     * @param hospitalId The new hospital ID of the user.
     */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

    /**
     * Gets the password associated with the user's account.
     *
     * @return The password of the user.
     */
	public String getPassword() {
		return this.password;
	}

    /**
     * Sets the password associated with the user's account.
     *
     * @param password The new password of the user.
     */
	public void setPassword(String password) {
		this.password = password;
	}


    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
	public String getName() {
		return this.name;
	}

    /**
     * Sets the name of the user.
     *
     * @param name The new name of the user.
     */
	public void setName(String name) {
		this.name = name;
	}


    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
	public String getGender() {
		return this.gender;
	}

    /**
     * Sets the gender of the user.
     *
     * @param gender The new gender of the user.
     */
	public void setGender(String gender) {
		this.gender = gender;
	}

}
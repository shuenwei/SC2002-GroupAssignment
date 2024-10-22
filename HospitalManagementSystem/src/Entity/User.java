package Entity;

public abstract class User {

	private String hospitalId;
	private String password = "password";
	private String name;
	private String gender;

    public User(String hospitalId,String password, String name, String gender) {
		this.hospitalId = hospitalId;
		this.password = password;
		this.name = name;
		this.gender = gender;
    }
	
	public abstract void displayMenu(User user);
	
	public String getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
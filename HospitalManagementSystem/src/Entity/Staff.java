package Entity;

public abstract class Staff extends User {

	private String role;
	private int age;

	public Staff(String hospitalId,String password, String name, String gender,String role, int age) {
		super(hospitalId,password,name,gender);
		this.age = age;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
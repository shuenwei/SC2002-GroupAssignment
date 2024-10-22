package Entity;

public abstract class Staff extends User {

	private Enums.Role role;
	private int age;

	public Staff(String hospitalId,String password, String name, String gender,Enums.Role role, int age) {
		super(hospitalId,password,name,gender);
		this.age = age;
		this.role = role;
	}

	public Enums.Role getRole() {
		return role;
	}

	public void setRole(Enums.Role role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
package Entity;

public class Administrator extends Staff {
    public Administrator(String hospitalId,String password, String name, String gender,String role, int age) {
        super(hospitalId,password,name,gender,role,age);
    }

    public void displayMenu(User currentUser) {

    }
}
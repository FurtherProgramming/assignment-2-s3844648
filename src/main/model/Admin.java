package main.model;

public class Admin extends User{
    public int id;
    public String firstName;
    public String lastName;
    public int age;
    public String username;
    public String password;

    public Admin(int id, String fname, String lname, int age, String username) {
        this.id = id;
        firstName = fname;
        lastName = lname;
        this.age = age;
        this.username = username;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getUsername() {
        return null;
    }
}

package main.model;

public class Admin extends User{
    public int id;
    public String firstName;
    public String lastName;
    public String username;

    public Admin(int id, String fname, String lname, String username) {
        this.id = id;
        firstName = fname;
        lastName = lname;
        this.username = username;
    }

    @Override
    public String getName() {
        return firstName;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }
}

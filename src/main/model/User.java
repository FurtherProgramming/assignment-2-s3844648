package main.model;

public class User {
    public int userID;
    public String firstName;
    public String lastName;
    public int age;
    public String username;
    public Boolean activated;

    public User(int id, String fname, String lname, int age, String username, Boolean activated) {
        userID = id;
        firstName = fname;
        lastName = lname;
        this.age = age;
        this.username = username;
        this.activated = activated;
    }

    public String getName(){
        return firstName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public int getID(){
        return userID;
    }

    public String getUsername() {
        return username;
    }
}

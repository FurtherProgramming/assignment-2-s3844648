package main.model;

public class User {
    public int userID;
    public String firstName;
    public String lastName;
    public int age;
    public String username;

    public User(int id, String fname, String lname, int ag, String user) {
        userID = id;
        firstName = fname;
        lastName = lname;
        age = ag;
        username = user;
    }

    public String getName(){
        return firstName;
    }

    public int getID(){
        return userID;
    }
}

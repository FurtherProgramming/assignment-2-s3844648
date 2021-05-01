package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    Connection connection;

    public boolean isLoggedIn = false;
    public User currentUser;
    public int userID;
    public String firstName;
    public String lastName;
    public int age;
    public String username;

    public LoginModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean isLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ? and password= ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isLoggedIn = true;
                userID = resultSet.getInt(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                age = resultSet.getInt(4);
                username = resultSet.getString(5);

                currentUser = new User(userID, firstName, lastName, age, username);
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }finally {
           preparedStatement.close();
           resultSet.close();
        }

    }

    public void register(String firstname, String lastname, String age, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO Employee (firstname, lastname, age, username, password)"
                    + "VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);

            preparedStatement.execute();



        }catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }finally {
            preparedStatement.close();

        }

    }
}

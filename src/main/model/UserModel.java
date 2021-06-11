package main.model;

import main.SQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class UserModel {

    Connection connection;

    public boolean isLoggedIn = false;
    public Employee currentEmployee;
    public int userID;
    public String firstName;
    public String lastName;
    public int age;
    public String username;
    public String question;
    public String answer;
    public Boolean activated;

    public UserModel(){
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
                activated = resultSet.getBoolean(10);

                currentEmployee = new Employee(userID, firstName, lastName, age, username, activated);
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

    public Boolean isUsername(String user) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isLoggedIn = true;
                userID = resultSet.getInt(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                age = resultSet.getInt(4);
                username = resultSet.getString(5);
                question = resultSet.getString(8);
                answer = resultSet.getString(9);
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

    public void updatePassword(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Employee SET password = ? WHERE username = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pass);
            preparedStatement.setString(2, user);

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }

    }

    public void register(String firstname, String lastname, String age, String username, String password, String role, String question, String answer) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO Employee (firstname, lastname, age, username, password, role, question, answer)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, role);
            preparedStatement.setString(7, question);
            preparedStatement.setString(8, answer);

            preparedStatement.execute();
        }catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }finally {
            preparedStatement.close();
        }
    }

    public void addAdmin(String firstname, String lastname, String age, String username, String password, String role, String question, String answer) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO Admin (firstname, lastname, username, password, role, question, answer)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, role);
            preparedStatement.setString(6, question);
            preparedStatement.setString(7, answer);

            preparedStatement.execute();
        }catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }finally {
            preparedStatement.close();
        }
    }

    //function to generate a random string of length n
    //source: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    public static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public ArrayList<Employee> getEmployees() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;

        String query = "select * from employee";
        try {
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                userID = resultSet.getInt(1);
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                age = resultSet.getInt(4);
                username = resultSet.getString(5);
                activated = resultSet.getBoolean(10);

                Employee employee = new Employee(userID, firstName, lastName, age, username, activated);
                employees.add(employee);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return employees;
    }

    public Boolean isActivated(int employeeID) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where id = ? and activated = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeID);
            preparedStatement.setBoolean(2, true);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
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

    public void activateUser(int employeeID) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE employee SET activated = ? WHERE id = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, employeeID);

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }
    }

    public void deactivateUser(int employeeID) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE employee SET activated = ? WHERE id = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, employeeID);

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }
    }

    public void deleteUser(int employeeID) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM employee WHERE id = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeID);

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }
    }



    public Employee getCurrentUser(){
        return currentEmployee;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUsername() {
        return username;
    }
}

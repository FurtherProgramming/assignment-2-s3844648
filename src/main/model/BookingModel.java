package main.model;

import main.SQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingModel {
    Connection connection;
    private final int numOfDesks = 8;

    public BookingModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public ArrayList<Booking> getBookings(LocalDate date) throws SQLException {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (int i = 0; i < numOfDesks; i++){
            PreparedStatement preparedStatement = null;
            ResultSet resultSet=null;
            String query = "select * from Booking where date = ? and deskNum = ?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, Date.valueOf(date));
                preparedStatement.setInt(2, i);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    int empID = resultSet.getInt(2);
                    int table = resultSet.getInt(3);
                    LocalDate d = resultSet.getDate(4).toLocalDate();

                    Booking booking = new Booking(empID, table, d);
                    bookings.add(booking);

                }else{
                    bookings.add(null);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                preparedStatement.close();
                resultSet.close();
            }
        }
        return bookings;
    }

    public void saveBooking (Booking booking) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO Booking ( employeeid, deskNum, date)"
                    + "VALUES ( ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getEmployeeID());
            preparedStatement.setInt(2, booking.getDesk());
            preparedStatement.setDate(3, Date.valueOf(booking.getDate()));

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }
    }

    public boolean isValidBooking(Booking booking) throws SQLException {
        //check if table is locked

        //check if table has already been booked
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from Booking where deskNum = ? and date= ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getDesk());
            preparedStatement.setDate(2, Date.valueOf(booking.getDate()));

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("table has already been booked!");
                return false;
            }
        } catch (Exception e) {

        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        //check if user has already booked a seat
        preparedStatement = null;
        resultSet=null;
        query = "select * from Booking where employeeid = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getEmployeeID());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                LocalDate lastBooked = resultSet.getDate(4).toLocalDate();
                LocalDate today = LocalDate.now();
                int difference = today.compareTo(lastBooked);
                if (difference > 0){
                    System.out.println("User already has another booked seat!");
                    return false;
                }
            }
        } catch (Exception e) {

        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        //check if table is same as previously booked table
        preparedStatement = null;
        resultSet=null;
        query = "select * from Booking where employeeid = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getEmployeeID());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                if (booking.getDesk() == resultSet.getInt(3)){
                    System.out.println("User cannot book previously booked table!");
                    return false;
                }
            }

        } catch (Exception e) {

        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return true;
    }

    public Boolean isLocked(int deskNum) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from desk where deskNum = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, deskNum);


            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if(resultSet.getBoolean(2)){
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

    public void lockDesk(int deskNum) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE desk SET locked = ? WHERE deskNum = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, deskNum);

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }
    }

    public void unlockDesk(int deskNum) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE desk SET locked = ? WHERE deskNum = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, deskNum);

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }
    }
}

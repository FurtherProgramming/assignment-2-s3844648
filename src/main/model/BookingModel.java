package main.model;

import main.SQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingModel {
    Connection connection;
    private final int numOfTables = 8;

    public BookingModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public ArrayList<Booking> getBookings(LocalDate date) throws SQLException {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (int i = 0; i < numOfTables; i++){
            PreparedStatement preparedStatement = null;
            ResultSet resultSet=null;
            String query = "select * from Booking where date = ? and table = ?";
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

                    Booking booking = new Booking(id, empID, table, d);
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
            String query = "INSERT INTO Booking (id, employeeid, table, date)"
                    + "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getBookingID());
            preparedStatement.setInt(2, booking.getEmployeeID());
            preparedStatement.setInt(3, booking.getTable());
            preparedStatement.setDate(4, Date.valueOf(booking.getDate()));

            preparedStatement.execute();
        }
        catch (Exception e)
        {

        }finally {
            preparedStatement.close();
        }
    }
}

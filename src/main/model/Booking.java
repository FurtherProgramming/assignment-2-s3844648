package main.model;

import java.time.LocalDate;

public class Booking {


    public int bookingID;
    public int employeeID;
    public int table;
    public LocalDate date;

    public Booking(int bookingID, int employeeID, int table, LocalDate date){
        this.bookingID = bookingID;
        this.employeeID = employeeID;
        this.table = table;
        this.date = date;
    }

    public int getBookingID(){
        return bookingID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getTable() {
        return table;
    }

    public LocalDate getDate() {
        return date;
    }

}

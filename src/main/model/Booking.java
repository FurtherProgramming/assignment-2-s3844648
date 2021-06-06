package main.model;

import java.time.LocalDate;

public class Booking {


    public int bookingID;
    public int employeeID;
    public int desk;
    public LocalDate date;

    public Booking(int employeeID, int desk, LocalDate date){
        this.employeeID = employeeID;
        this.desk = desk;
        this.date = date;
    }

    public int getBookingID(){
        return bookingID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getDesk() {
        return desk;
    }

    public LocalDate getDate() {
        return date;
    }

}

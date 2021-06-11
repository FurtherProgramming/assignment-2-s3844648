package main.controller;

import javafx.stage.Stage;
import main.model.Booking;
import main.model.Employee;

import java.time.LocalDate;

public class Configuration {
    private static final Configuration conf = new Configuration();
    private Stage primaryStage;
    private Employee employee;
    private LocalDate selectedDate;
    private int selectedTable;
    private Booking booking;
    private boolean admin;

    public static LocalDate getSelectedDate() {
        return conf.selectedDate;
    }

    public static void setSelectedDate(LocalDate selectedDate) {
        conf.selectedDate = selectedDate;
    }

    public static int getSelectedTable() {
        return conf.selectedTable;
    }

    public static void setSelectedTable(int selectedTable) {
        conf.selectedTable = selectedTable;
    }

    public static void setUser(Employee employee){
        conf.employee = employee;
    }

    public static Employee getUser(){
        return conf.employee;
    }

    public static void setBooking(Booking booking){
        conf.booking = booking;
    }

    public static Booking getBooking(){
        return conf.booking;
    }

    public static void setPrimaryStage(Stage stage){
        conf.primaryStage = stage;
    }

    public static Stage getPrimaryStage(){
        return conf.primaryStage;
    }

    public static void setAdmin(boolean b){
        conf.admin = b;
    }

    public static boolean isAdmin(){
        if (conf.admin){
            return true;
        }else{
            return false;
        }

    }

    private Configuration(){}
}

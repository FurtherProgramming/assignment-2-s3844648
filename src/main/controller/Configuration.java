package main.controller;

import javafx.stage.Stage;
import main.model.User;

import java.time.LocalDate;

public class Configuration {
    private static final Configuration conf = new Configuration();
    private Stage primaryStage;
    private User user;
    private LocalDate selectedDate;
    private int selectedTable;

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

    public static void setUser(User user){
        conf.user = user;
    }

    public static User getUser(){
        return conf.user;
    }

    public static void setPrimaryStage(Stage stage){
        conf.primaryStage = stage;
    }

    public static Stage getPrimaryStage(){
        return conf.primaryStage;
    }

    private Configuration(){}
}

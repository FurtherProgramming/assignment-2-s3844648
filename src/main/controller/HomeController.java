package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.model.Booking;
import main.model.BookingModel;
import main.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private BookingModel bookingModel = new BookingModel();

    private Employee currentEmployee;
    private LocalDate selectedDate = null;
    private ArrayList<Booking> bookings;
    private ArrayList<Button> tables;

    @FXML
    private Label welcomeMessage;
    @FXML
    private DatePicker dp;
    @FXML
    private Button manageBookings;
    @FXML
    private Button manageEmployees;

    @FXML
    private Button table0;
    @FXML
    private Button table1;
    @FXML
    private Button table2;
    @FXML
    private Button table3;
    @FXML
    private Button table4;
    @FXML
    private Button table5;
    @FXML
    private Button table6;
    @FXML
    private Button table7;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedDate = null;
        if (Configuration.isAdmin()){
            manageBookings.setVisible(true);
            manageEmployees.setVisible(true);
        }else{
            currentEmployee = Configuration.getUser();
            welcomeMessage.setText("Welcome back, " + currentEmployee.getName());
        }

        tables = new ArrayList<Button>();
        tables.add(table0);
        tables.add(table1);
        tables.add(table2);
        tables.add(table3);
        tables.add(table4);
        tables.add(table5);
        tables.add(table6);
        tables.add(table7);
        refreshDesks();
    }

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void selectDate(ActionEvent actionEvent) throws SQLException {
        Configuration.setSelectedDate(dp.getValue());
        selectedDate = dp.getValue();
        bookings = bookingModel.getBookings(selectedDate);
        refreshDesks();
    }

    private void refreshDesks() {
        for (int i = 0; i < tables.size(); i++){
            int finalI = i;
            tables.get(finalI).setOnAction(event -> {
                if (Configuration.isAdmin()){
                    try {
                        if (bookingModel.isLocked(finalI)){
                            bookingModel.unlockDesk(finalI);
                            refreshDesks();
                        }else {
                            bookingModel.lockDesk(finalI);
                            refreshDesks();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }else {
                    Configuration.setBooking(new Booking(Configuration.getUser().getID(), finalI, selectedDate));
                    try {
                        confirmBooking();
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        //colour desks
        for (int i = 0;  i < tables.size(); i++){
            int finalI = i;
            tables.forEach((n) -> {
                try {
                    if (selectedDate != null && bookingModel.isBooked(finalI, selectedDate)){
                        tables.get(finalI).setStyle("-fx-background-color: rgba(198,0,0,0.98)");
                    }else if (bookingModel.isLocked(finalI)){
                        tables.get(finalI).setStyle("-fx-background-color: #ff5100");
                    }else {
                        tables.get(finalI).setStyle("-fx-background-color: #00b602");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
        }
    }

    public void confirmBooking() throws IOException, SQLException {
        if (bookingModel.isValidBooking(Configuration.getBooking())){
            Parent root = FXMLLoader.load(getClass().getResource("../ui/booking.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        }else
            welcomeMessage.setText("Invalid table, please choose another one!");
    }

    public void goToEmployeeManager(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/userManager.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        String css = this.getClass().getResource("../ui/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

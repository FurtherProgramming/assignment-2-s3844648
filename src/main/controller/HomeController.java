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
import main.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private BookingModel bookingModel = new BookingModel();

    private User currentUser;
    private LocalDate selectedDate;
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
        if (Configuration.isAdmin()){
            manageBookings.setVisible(true);
            manageEmployees.setVisible(true);
        }else{
            currentUser = Configuration.getUser();
            welcomeMessage.setText("Welcome back, " + currentUser.getName());
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
        refreshTables();
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
        refreshTables();
    }

    private void refreshTables() {
        for (int i = 0; i < tables.size(); i++){
            int finalI = i;
            tables.get(finalI).setOnAction(event -> {
                if (Configuration.isAdmin()){
                    try {
                        if (bookingModel.isLocked(finalI)){
                            bookingModel.unlockDesk(finalI);
                            refreshTables();
                        }else {
                            bookingModel.lockDesk(finalI);
                            refreshTables();
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

        //locked (orange)
        for (int i = 0;  i < tables.size(); i++){
            int finalI = i;
            tables.forEach((n) -> {
                try {
                    if (bookingModel.isLocked(finalI)){
                        tables.get(finalI).setStyle("-fx-background-color: #ff5100");
                    }else {
                        tables.get(finalI).setStyle("-fx-background-color: #223768");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
        }

        //booked (red)


        //available (green)


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
        Parent root = FXMLLoader.load(getClass().getResource("../ui/employeeManager.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        String css = this.getClass().getResource("../ui/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

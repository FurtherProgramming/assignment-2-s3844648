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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.model.Booking;
import main.model.BookingModel;
import main.model.LoginModel;
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
        tables = new ArrayList<Button>();
        tables.add(table0);
        tables.add(table1);
        tables.add(table2);
        tables.add(table3);
        tables.add(table4);
        tables.add(table5);
        tables.add(table6);
        tables.add(table7);

        currentUser = Configuration.getUser();

        welcomeMessage.setText("Welcome back, " + currentUser.getName());
    }

    public void tableTest(ActionEvent actionEvent) {
        table1.setStyle("-fx-background-color: #bd0606");
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
        showTables();
    }

    private void showTables() {
        welcomeMessage.setText(Configuration.getSelectedDate().toString());

        //locked (orange)
        tables.get(1).setStyle("-fx-background-color: #ff5100");
        tables.get(3).setStyle("-fx-background-color: #ff5100");
        tables.get(5).setStyle("-fx-background-color: #ff5100");
        tables.get(7).setStyle("-fx-background-color: #ff5100");

        //booked (red)


        //available (green)

    }

    public void table0(ActionEvent actionEvent) throws IOException {
        Configuration.setSelectedTable(0);
        Parent root = FXMLLoader.load(getClass().getResource("../ui/booking.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void table1(ActionEvent actionEvent) {
    }
}

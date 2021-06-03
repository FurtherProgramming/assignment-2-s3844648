package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.model.Booking;
import main.model.BookingModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    private BookingModel bookingModel = new BookingModel();
    private int selectedTable;
    private LocalDate selectedDate;
    private Booking booking;

    @FXML
    private Label bookingMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedTable = Configuration.getSelectedTable();
        selectedDate = Configuration.getSelectedDate();
        booking = new Booking(Configuration.getUser().getID(), selectedTable, selectedDate);
        bookingMessage.setText("Book table " + selectedTable + " for " + selectedDate.toString() + "?");
    }

    public void bookTable(ActionEvent actionEvent) throws SQLException {
        bookingModel.saveBooking(booking);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void close(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}

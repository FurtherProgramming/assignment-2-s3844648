package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import main.model.LoginModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    //public LoginModel loginModel = new LoginModel();
    @FXML
    private Label name;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    public void register(ActionEvent actionEvent) {

    }
}


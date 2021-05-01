package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.model.LoginModel;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private LoginModel loginModel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label welcomeMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //welcomeMessage.setText();
    }

    public void initData(LoginModel loginModel){
    }

}

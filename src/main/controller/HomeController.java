package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.model.LoginModel;
import main.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private LoginModel loginModel;
    private User currentUser;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label welcomeMessage;
    @FXML
    private Button table1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(User user){
        currentUser = user;
        welcomeMessage.setText("Welcome back, " + currentUser.getName());
    }

    public void tableTest(ActionEvent actionEvent) {
        table1.setStyle("-fx-background-color: #bd0606");
    }
}

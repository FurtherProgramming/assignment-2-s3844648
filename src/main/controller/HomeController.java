package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.model.LoginModel;
import main.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private LoginModel loginModel;
    private User currentUser;

    @FXML
    private Label welcomeMessage;
    @FXML
    private Button table1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}

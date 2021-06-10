package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private UserModel userModel = new UserModel();

    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (userModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }

    public void Login(ActionEvent actionEvent){
        Configuration.setAdmin(false);
        try {
            if (userModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                Configuration.setUser(userModel.getCurrentUser());
                isConnected.setText("Logged in successfully");
                goToHome(actionEvent);
            }else{
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void goToHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        String css = this.getClass().getResource("../ui/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void goToRegister(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/register.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void goToReset(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/resetPassword.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void loginAsAdmin(ActionEvent actionEvent) throws IOException {
        Configuration.setAdmin(true);
        goToHome(actionEvent);
    }
}

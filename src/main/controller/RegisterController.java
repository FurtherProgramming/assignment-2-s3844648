package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.model.LoginModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public LoginModel loginModel = new LoginModel();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    public void register(ActionEvent actionEvent) {
        try{
            loginModel.register(txtFirstName.getText(), txtLastName.getText(), txtAge.getText(), txtUsername.getText(), txtPassword.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


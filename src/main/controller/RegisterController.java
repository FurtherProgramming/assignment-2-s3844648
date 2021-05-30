package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public LoginModel loginModel = new LoginModel();

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
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtQuestion;
    @FXML
    private TextField txtAnswer;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    public void register(ActionEvent actionEvent) {
        try{
            loginModel.register(txtFirstName.getText(), txtLastName.getText(), txtAge.getText(), txtUsername.getText(), txtPassword.getText(), txtRole.getText(), txtQuestion.getText(), txtAnswer.getText());
            goToLogin(actionEvent);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


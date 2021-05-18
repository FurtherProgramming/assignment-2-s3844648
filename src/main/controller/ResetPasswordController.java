package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.LoginModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {
    public LoginModel loginModel = new LoginModel();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String username;
    private String answer;

    @FXML
    private TextField txtUsername;
    @FXML
    private Label question;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Label newPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void validateUsername(ActionEvent actionEvent) {
        try {
            if (loginModel.isUsername(txtUsername.getText())){
                username = loginModel.getUsername();
                question.setText(loginModel.getQuestion());
                answer = loginModel.getAnswer();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void resetPassword(ActionEvent actionEvent) {
        if (txtAnswer.getText().equals(answer)){
            //generate new password
            String randomPassword = "testPassword";

            try {
                loginModel.updatePassword(username, randomPassword);
                newPassword.setText("Your new Password is " + randomPassword);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void goToLogin(ActionEvent actionEvent) {
    }
}

package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {
    public UserModel userModel = new UserModel();

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
    private Label answerLabel;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Label errorMessage;
    @FXML
    private Button resetButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void validateUsername(ActionEvent actionEvent) {
        try {
            if (userModel.isUsername(txtUsername.getText())){
                username = userModel.getUsername();
                question.setText(userModel.getQuestion());
                answer = userModel.getAnswer();

                question.setVisible(true);
                answerLabel.setVisible(true);
                txtAnswer.setVisible(true);
                resetButton.setVisible(true);

                errorMessage.setText("");
            }else{
                errorMessage.setText("Invalid username");
                question.setVisible(false);
                answerLabel.setVisible(false);
                txtAnswer.setVisible(false);
                resetButton.setVisible(false);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void resetPassword(ActionEvent actionEvent) {
        if (txtAnswer.getText().equals(answer)){
            //generate new password
            String randomPassword = userModel.getAlphaNumericString(10);

            try {
                userModel.updatePassword(username, randomPassword);
                errorMessage.setText("Your new Password is " + randomPassword);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else
            errorMessage.setText("Incorrect Answer!");

    }

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/login.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

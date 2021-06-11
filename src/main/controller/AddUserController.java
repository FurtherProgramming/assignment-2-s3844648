package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;


public class AddUserController {
    private UserModel userModel = new UserModel();

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField question;
    @FXML
    private TextField answer;
    @FXML
    private TextField role;


    public void addEmployee(ActionEvent actionEvent) throws SQLException, IOException {
        userModel.register(firstname.getText(), lastname.getText(), "", username.getText(), password.getText(), role.getText(), question.getText(), answer.getText());
        goToEmployeeManager(actionEvent);
    }

    public void addAdmin(ActionEvent actionEvent) throws SQLException, IOException {
        userModel.addAdmin(firstname.getText(), lastname.getText(), "", username.getText(), password.getText(), role.getText(), question.getText(), answer.getText());
        goToEmployeeManager(actionEvent);
    }

    public void goToEmployeeManager(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/employeeManager.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        String css = this.getClass().getResource("../ui/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

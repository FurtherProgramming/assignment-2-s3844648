package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import main.model.User;
import main.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeManagerController implements Initializable {
    private UserModel userModel = new UserModel();
    private ArrayList<User> employees;

    @FXML
    private ListView employeeList;
    @FXML
    private FlowPane activateOptions;
    @FXML
    private FlowPane editOptions;
    @FXML
    private FlowPane deleteOptions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            employees = userModel.getEmployees();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        showEmployees();
    }

    private void showEmployees() {
        employees.forEach((n) -> {
            employeeList.getItems().add(n.getUsername() + " (" + n.getFullName() + ")");

            Button activation = new Button("Activate");
            activateOptions.getChildren().add(activation);

            Button edit = new Button("Edit");
            editOptions.getChildren().add(edit);

            Button delete = new Button("Delete");
            deleteOptions.getChildren().add(delete);
        });
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

}

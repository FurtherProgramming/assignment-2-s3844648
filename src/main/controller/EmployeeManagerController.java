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
import main.model.Employee;
import main.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeManagerController implements Initializable {
    private UserModel userModel = new UserModel();
    private ArrayList<Employee> employees;
    private ArrayList<Employee> admins;

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
        //clear items
        employeeList.getItems().clear();
        activateOptions.getChildren().clear();
        editOptions.getChildren().clear();
        deleteOptions.getChildren().clear();

        employees.forEach((n) -> {
            employeeList.getItems().add(n.getUsername() + " (" + n.getFullName() + ")");

            //activate & deactivate
            try {
                if (userModel.isActivated(n.getID())){
                    Button activate = new Button("Deactivate");
                    activate.setOnAction(event -> {
                        try {
                            userModel.deactivateUser(n.getID());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        showEmployees();
                    });
                    activateOptions.getChildren().add(activate);
                }else{
                    Button deactivate = new Button("Activate");
                    deactivate.setOnAction(event -> {
                        try {
                            userModel.activateUser(n.getID());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        showEmployees();
                    });
                    activateOptions.getChildren().add(deactivate);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            //edit
            Button edit = new Button("Edit");
            editOptions.getChildren().add(edit);

            //delete
            Button delete = new Button("Delete");
            delete.setOnAction(event -> {
                try {
                    userModel.deleteUser(n.getID());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                employees.remove(n);
                showEmployees();
            });
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

    public void addUser(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ui/addUser.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = Configuration.getPrimaryStage();
        String css = this.getClass().getResource("../ui/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void toggleUsers(ActionEvent actionEvent) {
    }
}

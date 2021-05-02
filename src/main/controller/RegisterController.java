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
    public LoginModel loginModel;

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
            goToRegister(actionEvent);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void goToRegister(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../ui/home.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Home");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}


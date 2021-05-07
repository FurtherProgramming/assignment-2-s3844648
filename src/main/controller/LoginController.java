package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.model.LoginModel;
import main.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private LoginModel loginModel = new LoginModel();
    private User currentUser;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent actionEvent){

        try {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                currentUser = loginModel.getCurrentUser();
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../ui/home.fxml"));
        root = loader.load();

        scene = new Scene(root);

        String css = this.getClass().getResource("../ui/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        HomeController controller = loader.getController();
        controller.initData(currentUser);

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Home");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public void goToRegister(ActionEvent actionEvent) throws IOException {

        root = FXMLLoader.load(getClass().getResource("../ui/register.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Register");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

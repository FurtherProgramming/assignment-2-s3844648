package main.controller;

import javafx.stage.Stage;
import main.model.User;

public class Configuration {
    private Stage primaryStage;
    private User user;
    private static final Configuration conf = new Configuration();

    private Configuration(){}

    public static void setPrimaryStage(Stage stage){
        conf.primaryStage = stage;
    }

    public static Stage getPrimaryStage(){
        return conf.primaryStage;
    }

    public static void setUser(User user){
        conf.user = user;
    }

    public static User getUser(){
        return conf.user;
    }
}

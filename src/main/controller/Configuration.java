package main.controller;

import javafx.stage.Stage;

public class Configuration {
    private Stage primaryStage;
    private String username;
    private static final Configuration conf = new Configuration();

    private Configuration(){}

    public static void setPrimaryStage(Stage stage){
        conf.primaryStage = stage;
    }

    public static Stage getPrimaryStage(){
        return conf.primaryStage;
    }

    public static void setUsername(String user){
        conf.username = user;
    }

    public static String getUsername(){
        return conf.username;
    }
}

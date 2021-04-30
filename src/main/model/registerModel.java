package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class registerModel {

    Connection connection;

    public RegisterModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public void register() {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Employee (firstname, lastname, age, username, password)"
                + "VALUES (?, ?, ?, ?', ?)";
    }
}

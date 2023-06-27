package com.example.delivery_service;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EntryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button entry;

    @FXML
    private Label error;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    void initialize() {
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");
            }
        });

        entry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String loginText = login.getText();
                String passwordText = password.getText();
                if (loginText == "" || passwordText == ""){
                    error.setText("Неверный логин или пароль");
                }
                try{
                if(loginUser(loginText, passwordText))
                    Transition.changeScene(event, "base.fxml", "Delivery Service");
                else
                    error.setText("Неверный логин или пароль");
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);}
                catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private boolean loginUser(String loginText, String passwordText) throws SQLException, ClassNotFoundException {
        boolean flag = false;
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = DatabaseConnection.getInstance().getUser(user);
        if(resultSet.next()) {
            flag = true;
        }
        return flag;
    }

}

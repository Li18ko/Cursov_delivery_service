package com.example.delivery_service;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
                if (loginText != "" && passwordText != ""){
                    try {
                        loginUser(loginText, passwordText);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                Transition.changeScene(event, "base.fxml", "Delivery Service");
            }
        });

    }

    private void loginUser(String loginText, String passwordText) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        DatabaseConnection.getInstance().getUser(user);

    }

}

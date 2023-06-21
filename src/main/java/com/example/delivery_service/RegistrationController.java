package com.example.delivery_service;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private Button backRegistration;

    @FXML
    private TextField loginRegistration;

    @FXML
    private TextField name;

    @FXML
    private TextField number;

    @FXML
    private PasswordField passwordRegistration;

    @FXML
    private Button registration;

    @FXML
    void initialize() {
        backRegistration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");
            }
        });
    }

}

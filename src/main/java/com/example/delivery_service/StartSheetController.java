package com.example.delivery_service;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartSheetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button entry;

    @FXML
    private Button registration;

    @FXML
    void initialize() {
        entry.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "entry.fxml", "Вход");
            }
        });
        registration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "registration.fxml", "Регистрация");
            }
        });
    }



}


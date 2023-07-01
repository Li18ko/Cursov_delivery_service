package com.example.delivery_service;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BaseAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> center_delivery_manager;

    @FXML
    private TableView<?> courier;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<?, ?> login_manager;

    @FXML
    private TableView<?> manager;

    @FXML
    private TableColumn<?, ?> name_courier;

    @FXML
    private TableColumn<?, ?> name_manager;

    @FXML
    private TableColumn<?, ?> number_manager;

    @FXML
    private Button registrCourier;

    @FXML
    private Button registrManager;

    @FXML
    void initialize() {

        registrCourier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "registrationCourier.fxml", "ADMIN");

            }
        });
        registrManager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "registrationManager.fxml", "ADMIN");

            }
        });

    }

}

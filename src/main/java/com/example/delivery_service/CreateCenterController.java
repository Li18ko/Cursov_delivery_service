package com.example.delivery_service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class CreateCenterController {
    @FXML
    private TextField address;

    @FXML
    private Label advertisement;

    @FXML
    private Button backRegistration;

    @FXML
    private TextField name;

    @FXML
    private Button registration;

    @FXML
    private Label warningAddress;

    @FXML
    private Label warningName;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        backRegistration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "baseAdmin.fxml", "ADMIN");
            }
        });

        registration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if ((name.getText() == "" || name.getText().length() > 50) || (address.getText() == "" ||
                            address.getText().length() > 60 || address.getText().length() < 15)) {


                        if (name.getText() == "" || name.getText().length() > 50)
                            name.setText("Название некорректное");


                        if (address.getText() == "" || address.getText().length() > 60 || address.getText().length() < 15)
                            address.setText("Адрес некорректный");

                    }

                    else {
                        createCenter();
                        Transition.changeScene(event, "baseAdmin.fxml", "ADMIN");

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);

                }
            }


        });
    }

    private void createCenter() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        String nameText = name.getText();
        String addressText = address.getText();

        Delivery_center delivery_center = new Delivery_center(nameText, addressText);

        DatabaseConnection.getInstance().createCenter(delivery_center);

    }

}
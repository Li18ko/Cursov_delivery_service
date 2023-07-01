package com.example.delivery_service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegistrationManagerController {
    @FXML
    private Label advertisement;

    @FXML
    private Button backRegistration;

    @FXML
    private TextField center_delivery;

    @FXML
    private TextField loginRegistration;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passwordRegistration;

    @FXML
    private Button registration;

    @FXML
    private Label warningCenter_Delivery;

    @FXML
    private Label warningLogin;

    @FXML
    private Label warningName;

    @FXML
    private Label warningPassword;

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
                    if ((name.getText() == "" || name.getText().length() > 30) || (loginRegistration.getText() == ""
                            || loginRegistration.getText().length() > 20 || DatabaseConnection.getInstance().isLoginExists(loginRegistration.getText()))
                            || (passwordRegistration.getText() == "" || passwordRegistration.getText().length() > 30
                            || passwordRegistration.getText().length() < 6) || center_delivery.getText() == "" || center_delivery.getText().length() > 50 ||
                            !DatabaseConnection.getInstance().checkDelivery_center(center_delivery.getText())){


                        if (name.getText() == "" || name.getText().length() > 30)
                            name.setText("Имя некорректное");


                        if (center_delivery.getText() == "" || center_delivery.getText().length() > 50 ||
                                !DatabaseConnection.getInstance().checkDelivery_center(center_delivery.getText())){
                            center_delivery.setText("Такого центра доставки не существует");
                        }

                        if (loginRegistration.getText() == "" || loginRegistration.getText().length() > 20 || DatabaseConnection.getInstance().isLoginExists(loginRegistration.getText())) {
                            if (DatabaseConnection.getInstance().isLoginExists(loginRegistration.getText())) {
                                loginRegistration.setText("Логин занят");
                            } else loginRegistration.setText("Логин некорректный");
                        }

                        if (passwordRegistration.getText() == "" || passwordRegistration.getText().length() > 30 || passwordRegistration.getText().length() < 6) {
                            warningPassword.setText("* Пароль должен быть от 6 до 30 символов");
                        }
                    }

                    else {
                        registerManager();
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

    private void registerManager() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        String nameText = name.getText();
        String center_deliveryText = center_delivery.getText();
        String loginRegistrationText = loginRegistration.getText();
        String passwordRegistrationText = passwordRegistration.getText();

        String hashedPassword = PasswordHasher.hashPassword(passwordRegistrationText);

        Manager manager = new Manager(center_deliveryText, nameText, loginRegistrationText, hashedPassword);

        DatabaseConnection.getInstance().registerManager(manager);

    }

}
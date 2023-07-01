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

public class RegistrationCourierController {

    @FXML
    private Label advertisement;

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
    private Label warningLogin;

    @FXML
    private Label warningName;

    @FXML
    private Label warningNumber;

    @FXML
    private Label warningPassword;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        backRegistration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "baseManager.fxml", "Manager");
            }
        });

        registration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if ((name.getText() == "" || name.getText().length() > 30) || (number.getText() == "" || number.getText().length() != 12 ||
                            (number.getText().charAt(0) != '+' && number.getText().charAt(1) != '7') ||
                            DatabaseConnection.getInstance().checkNumber(number.getText())) || (loginRegistration.getText() == ""
                            || loginRegistration.getText().length() > 20 || DatabaseConnection.getInstance().isLoginExists(loginRegistration.getText()))
                            || (passwordRegistration.getText() == "" || passwordRegistration.getText().length() > 30 || passwordRegistration.getText().length() < 6)) {


                        if (name.getText() == "" || name.getText().length() > 30)
                            name.setText("Имя некорректное");

                        if (number.getText() == "" || number.getText().length() != 12 ||
                                (number.getText().charAt(0) != '+' && number.getText().charAt(1) != '7') ||
                                DatabaseConnection.getInstance().checkNumber(number.getText())){
                            if (DatabaseConnection.getInstance().checkNumber(number.getText())) {
                                number.setText("Номер занят");
                            } else number.setText("Номер некорректный");
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
                        registerCourier();
                        Transition.changeScene(event, "baseManager.fxml", "Manager");

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);

                }
            }


        });
    }

    private void registerCourier() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        String nameText = name.getText();
        String numberText = number.getText();
        String loginRegistrationText = loginRegistration.getText();
        String passwordRegistrationText = passwordRegistration.getText();

        String hashedPassword = PasswordHasher.hashPassword(passwordRegistrationText);

        Courier courier = new Courier(nameText, numberText, loginRegistrationText, hashedPassword);

        DatabaseConnection.getInstance().registerCourier(courier);

    }

}
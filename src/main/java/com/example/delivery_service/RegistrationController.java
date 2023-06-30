package com.example.delivery_service;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private Label warningPassword;

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
    void initialize() throws SQLException, ClassNotFoundException {
        backRegistration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");
            }
        });

        registration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if ((name.getText() == "" || name.getText().length() > 30) || (number.getText() == "" || number.getText().length() != 12 ||
                            (number.getText().charAt(0) != '+' && number.getText().charAt(1) != '7') ||
                            DatabaseConnection.getInstance().checkNumber(number.getText())) || (address.getText() == "" ||
                            address.getText().length() > 100 || address.getText().length() < 15) || (loginRegistration.getText() == ""
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

                        if (address.getText() == "" || address.getText().length() > 100 || address.getText().length() < 15)
                            address.setText("Адрес некорректный");

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
                        registerUser();
                        Transition.changeScene(event, "startSheet.fxml", "Delivery Service");

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);

                }
            }


        });
    }

    private void registerUser() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        String nameText = name.getText();
        String numberText = number.getText();
        String addressText = address.getText();
        String loginRegistrationText = loginRegistration.getText();
        String passwordRegistrationText = passwordRegistration.getText();

        String hashedPassword = PasswordHasher.hashPassword(passwordRegistrationText);

        Client client = new Client(nameText, numberText, addressText, loginRegistrationText, hashedPassword);

        DatabaseConnection.getInstance().registerUser(client);

    }
}

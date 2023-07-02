package com.example.delivery_service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationManagerController {
    @FXML
    private Label advertisement;

    @FXML
    private Button backRegistration;


    @FXML
    private ComboBox<String> center_delivery;

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
        ArrayList<String> p = DatabaseConnection.getInstance().name_delivery_center();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            items.add(str);
        }
        center_delivery.setItems(items);

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
                            || passwordRegistration.getText().length() < 6) || center_delivery.getSelectionModel().isEmpty()){


                        if (center_delivery.getSelectionModel().isEmpty())
                            warningCenter_Delivery.setText(" Центр доставки не выбран");

                        if (name.getText() == "" || name.getText().length() > 30)
                            name.setText("Имя некорректное");

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
        String center_deliveryText = center_delivery.getValue();
        String loginRegistrationText = loginRegistration.getText();
        String passwordRegistrationText = passwordRegistration.getText();

        String hashedPassword = PasswordHasher.hashPassword(passwordRegistrationText);

        Manager manager = new Manager(center_deliveryText, nameText, loginRegistrationText, hashedPassword);

        DatabaseConnection.getInstance().registerManager(manager);

    }

}
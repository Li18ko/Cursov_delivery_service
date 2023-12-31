package com.example.delivery_service;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EntryController {
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

    private static String userLogin;
    private static int role;

    public static String getUserLogin() {
        return userLogin;
    }
    public static int getRole() {
        return role;
    }
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
                String passwordText = password.getText();
                String loginText = login.getText();

                if (loginText == "" || passwordText == ""){
                    error.setText("Неверный логин или пароль");
                }
                try{
                    String hashedPassword = PasswordHasher.hashPassword(passwordText);
                    role = DatabaseConnection.getInstance().checkRole(loginText);
                    if(loginUser(loginText, hashedPassword)){
                        userLogin = login.getText();
                        if (getRole() == 1){
                            Transition.changeScene(event, "baseClient.fxml", "Client");
                        }
                        else if (getRole() == 2){
                            Transition.changeScene(event, "baseManager.fxml", "Manager");
                        }
                        else if (getRole() == 3){
                            Transition.changeScene(event, "baseCourier.fxml", "Courier");
                        }
                        else if (getRole() == 4){
                            Transition.changeScene(event, "baseAdmin.fxml", "ADMIN");
                        }
                    }
                    else
                        error.setText("Неверный логин или пароль");
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);}
                catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private boolean loginUser(String loginText, String passwordText) throws SQLException, ClassNotFoundException {
        boolean flag = false;
        User user = new User(loginText, passwordText);
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = DatabaseConnection.getInstance().getUser(user);
        if(resultSet.next()) {
            flag = true;
        }
        return flag;
    }


}

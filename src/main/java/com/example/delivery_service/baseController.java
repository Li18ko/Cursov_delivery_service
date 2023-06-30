package com.example.delivery_service;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static com.example.delivery_service.EntryController.getUserLogin;

public class baseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button arrange;

    @FXML
    private Button exit;

    @FXML
    private Label id;

    @FXML
    private ComboBox typeDelivery;

    @FXML
    private TextField recipientName;

    @FXML
    private TextField recipientNumber;
    @FXML
    private TextField weight;

    private String parcle_id;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList("usual", "express");
        typeDelivery.setItems(list);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");
            }
        });

        arrange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (typeDelivery.getValue() == "" || weight.getText() == "" || recipientName.getText() == "" || recipientNumber.getText() == ""
                    ||  recipientNumber.getText().length() != 12 || (recipientNumber.getText().charAt(0) != '+'
                            && recipientNumber.getText().charAt(1) != '7') ||
                    !DatabaseConnection.getInstance().checkNumberName(recipientNumber.getText(), recipientName.getText()) ||
                                recipientName.getText().length() > 30 || DatabaseConnection.getInstance().checkLogin(recipientNumber.getText())) {
                        id.setText("Введены неверные данные");}
                    else{
                        makeOrder();
                        Transition.changeScene(event, "base.fxml", "Delivery Service");

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void makeOrder() throws SQLException, ClassNotFoundException {
        String typeDeliveryText = (String) typeDelivery.getValue();

        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String data = timestamp.format(formatter);
        
        DecimalFormat df = new DecimalFormat("0.00");
        String weightT = df.format(Double.valueOf(weight.getText().replace(',','.')));
        String weightText = weightT.replace(',', '.');

        String recipientNameText = recipientName.getText();
        String recipientNumberText = recipientNumber.getText();

        Parcle parcle = new Parcle(typeDeliveryText, weightText, data, recipientNameText, recipientNumberText);

        parcle_id = String.valueOf(DatabaseConnection.getInstance().makeOrder(parcle));

    }

}

package com.example.delivery_service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BaseController {
    @FXML
    private Button arrange;

    @FXML
    private Button exit;

    @FXML
    private Label id;

    @FXML
    private TableColumn<OrderRecipient, String> name_recepient;

    @FXML
    private TableColumn<OrderSender, String> name_sender;

    @FXML
    private TableColumn<OrderRecipient, String> number_resepient;

    @FXML
    private TableColumn<OrderSender, String> number_sender;

    @FXML
    private TableColumn<OrderRecipient, Button> ok;

    @FXML
    private TableColumn<OrderSender, String> parcels_dta;

    @FXML
    private TableColumn<OrderRecipient, String> parcels_dta_rec;

    @FXML
    private TableColumn<OrderSender, String> parcels_id;

    @FXML
    private TableColumn<OrderRecipient, String> parcels_id_rec;

    @FXML
    private TextField recipientName;

    @FXML
    private TextField recipientNumber;

    @FXML
    private TableColumn<OrderSender, String> res;

    @FXML
    private TableView<OrderRecipient> recep;

    @FXML
    private TableView<OrderSender> sender;

    @FXML
    private ComboBox<String> typeDelivery;

    @FXML
    private TextField weight;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        recepient_parcle();
        parcleStatus();
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
                        Transition.changeScene(event, "base.fxml", "Client");
                        parcleStatus();


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
        DatabaseConnection.getInstance().makeOrder(parcle);

    }

    private void parcleStatus() throws SQLException, ClassNotFoundException {
        ArrayList<String> p = DatabaseConnection.getInstance().parcleStatus();
        ObservableList<OrderSender> parcels = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            parcels.add(new OrderSender(s[0], s[1], s[2], s[3], s[4]));
        }

        parcels_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        parcels_dta.setCellValueFactory(new PropertyValueFactory<>("data"));
        name_recepient.setCellValueFactory(new PropertyValueFactory<>("name"));
        number_resepient.setCellValueFactory(new PropertyValueFactory<>("number"));
        res.setCellValueFactory(new PropertyValueFactory<>("status"));

        sender.setItems(parcels);
    }

    private void recepient_parcle() throws SQLException, ClassNotFoundException {
        res.setEditable(true);

        parcels_id_rec.setCellValueFactory(new PropertyValueFactory<>("rec_id"));
        parcels_dta_rec.setCellValueFactory(new PropertyValueFactory<>("rec_data"));
        name_sender.setCellValueFactory(new PropertyValueFactory<>("name"));
        number_sender.setCellValueFactory(new PropertyValueFactory<>("number"));
        ok.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok.setCellFactory(column -> {
            return new TableCell<OrderRecipient, Button>() {
                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(item);
                    }
                }
            };
        });

        ArrayList<String> p = DatabaseConnection.getInstance().recepient_parcle();
        ObservableList<OrderRecipient> data__ = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Подтвердить");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        parcelStatus(s[0]);
                        recepient_parcle();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
                data__.add(new OrderRecipient(s[0], s[1], s[2], s[3], button));
            }
        recep.setItems(data__);
        recep.refresh();

    }


    private void parcelStatus(String parcelId) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().recipientStatusParcle(parcelId);
    }

}

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
    private Button update;

    @FXML
    private Button exit;

    @FXML
    private Label clientNull;

    @FXML
    private Label error;

    @FXML
    private TableColumn<Parcle, String> name_recepient;

    @FXML
    private TableColumn<Parcle, String> name_sender;

    @FXML
    private TableColumn<Parcle, String> number_resepient;

    @FXML
    private TableColumn<Parcle, String> number_sender;

    @FXML
    private TableColumn<Parcle, Button> ok;

    @FXML
    private TableColumn<Parcle, String> parcels_data_send;

    @FXML
    private TableColumn<Parcle, String> parcels_dta_rec;

    @FXML
    private TableColumn<Parcle, String> parcels_id_send;

    @FXML
    private TableColumn<Parcle, String> parcels_id_rec;

    @FXML
    private TextField recipientName;

    @FXML
    private TextField recipientNumber;

    @FXML
    private TableColumn<Parcle, String> res;

    @FXML
    private TableView<Parcle> recep;

    @FXML
    private TableView<Parcle> sender;

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

        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "update.fxml", "Client");
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
                                recipientName.getText().length() > 30 || DatabaseConnection.getInstance().checkLogin(recipientNumber.getText()) ||
                    DatabaseConnection.getInstance().clientNull()) {
                    if (DatabaseConnection.getInstance().clientNull()){
                        clientNull.setText("Вам еще не присвоили ближайший центр доставки");
                    }
                    else
                       error.setText("Введены неверные данные");
                    }

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
        String typeDeliveryText = String.valueOf(typeDelivery.getValue());

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
        parcels_id_send.setCellValueFactory(new PropertyValueFactory<>("id"));
        parcels_data_send.setCellValueFactory(new PropertyValueFactory<>("data"));
        name_recepient.setCellValueFactory(new PropertyValueFactory<>("recipientName"));
        number_resepient.setCellValueFactory(new PropertyValueFactory<>("recipientNumber"));
        res.setCellValueFactory(new PropertyValueFactory<>("status"));

        ArrayList<String> p = DatabaseConnection.getInstance().parcleStatus();
        ObservableList<Parcle> parcels = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            parcels.add(new Parcle(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4]));
        }


        sender.setItems(parcels);
    }

    private void recepient_parcle() throws SQLException, ClassNotFoundException {
        res.setEditable(true);
        parcels_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
        parcels_dta_rec.setCellValueFactory(new PropertyValueFactory<>("data"));
        name_sender.setCellValueFactory(new PropertyValueFactory<>("nameSenders"));
        number_sender.setCellValueFactory(new PropertyValueFactory<>("numberSenders"));
        ok.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok.setCellFactory(column -> {
            return new TableCell<Parcle, Button>() {
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
        ObservableList<Parcle> data__ = FXCollections.observableArrayList();
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
                data__.add(new Parcle(s[0], s[1], s[2], s[3], button));
            }
        recep.setItems(data__);
        recep.refresh();

    }


    private void parcelStatus(String parcelId) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().recipientStatusParcle(parcelId);
    }

}

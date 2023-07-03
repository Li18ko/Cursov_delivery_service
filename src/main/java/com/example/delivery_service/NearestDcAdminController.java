package com.example.delivery_service;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class NearestDcAdminController {

    @FXML
    private TableColumn<Client, ComboBox<String>> address_cd;

    @FXML
    private TableColumn<Client, String> address_client;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<Client, String> id;

    @FXML
    private TableColumn<Client, Button> save;

    @FXML
    private TableView<Client> table;

    @FXML
    private Button button;

    private ComboBox<String> comboBox;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        table.setEditable(true);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        address_client.setCellValueFactory(new PropertyValueFactory<>("address"));
        address_cd.setCellValueFactory(new PropertyValueFactory<>("address_cd"));
        save.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        save.setCellFactory(column -> {
            return new TableCell<Client, Button>() {
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

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        address_client.setCellValueFactory(new PropertyValueFactory<>("address"));
        address_cd.setCellValueFactory(new PropertyValueFactory<>("address_cd"));

        // Установка фабрики значений для столбца "address_cd"
        address_cd.setCellFactory(column -> {
            return new TableCell<Client, ComboBox<String>>() {
                @Override
                protected void updateItem(ComboBox<String> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(item);
                    }
                }
            };
        });

        clientNearesrCD();

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "baseAdmin.fxml", "ADMIN");

            }
        });



    }

    private void clientNearesrCD() throws SQLException, ClassNotFoundException {
        ArrayList<String> p = DatabaseConnection.getInstance().clientNearesrCD();
        ObservableList<Client> items = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");


            comboBox = new ComboBox<String>();
            comboBox.setValue("Ближайшие цд");

            ArrayList<String> q = DatabaseConnection.getInstance().nearestCD(s[1]);
            ObservableList<String> items2 = FXCollections.observableArrayList();
            for (int j = 0; j < q.size(); j++){
                String str2 = q.get(j);
                items2.add(str2);
            }
            comboBox.setItems(items2);


            Button button = new Button("Сохранить");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        saveClient(s[0], comboBox.getValue());
                        clientNearesrCD();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

            items.add(new Client(s[0], s[1], comboBox, button));
        }

        table.setItems(items);
        table.refresh();

    }

    private void saveClient(String id, String address) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().updateClient(id, address);
    }

}

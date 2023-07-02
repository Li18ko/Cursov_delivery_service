package com.example.delivery_service;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BaseManagerController {

    @FXML
    private TableColumn<Manager, String> center_delivery;

    @FXML
    private TableColumn<Manager, String> data;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<Manager, Button> ok;


    @FXML
    private TableColumn<Manager, String> parcels_id;

    @FXML
    private TableView<Manager> table;

    @FXML
    private TableColumn<Manager, String> typeDelivery;

    @FXML
    private TableColumn<Manager, String> weight;
    private Button button;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        table.setEditable(true);


        parcels_id.setCellValueFactory(new PropertyValueFactory<>("parcle_id"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        typeDelivery.setCellValueFactory(new PropertyValueFactory<>("typeDelivery"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        center_delivery.setCellValueFactory(new PropertyValueFactory<>("center_delivery"));
        ok.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok.setCellFactory(column -> {
            return new TableCell<Manager, Button>() {
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

        dataManager();
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");

            }
        });


    }

    private void dataManager() throws SQLException, ClassNotFoundException {
        ArrayList<String> p = DatabaseConnection.getInstance().dataManager();
        ObservableList<Manager> datas = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Подтвердить");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        parcelStatus(s[0]);
                        dataManager();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            datas.add(new Manager(s[0], s[1], s[2], s[3], s[4], button));
        }

        table.setItems(datas);
        table.refresh();
    }

    private void parcelStatus(String parcelId) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().parcleStatusManager(parcelId);
    }


}

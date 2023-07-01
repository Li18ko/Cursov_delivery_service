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

public class BaseCourierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<DataCourier, String> address_recipient;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<DataCourier, String> name_recepient;

    @FXML
    private TableColumn<DataCourier, String> number_recepient;

    @FXML
    private TableColumn<DataCourier, Button> ok;

    @FXML
    private TableColumn<DataCourier, String> parcels_id;

    @FXML
    private TableView<DataCourier> table;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        table.setEditable(true);

        parcels_id.setCellValueFactory(new PropertyValueFactory<>("parcle_id"));
        name_recepient.setCellValueFactory(new PropertyValueFactory<>("name_recipient"));
        number_recepient.setCellValueFactory(new PropertyValueFactory<>("number_recipient"));
        address_recipient.setCellValueFactory(new PropertyValueFactory<>("address"));
        ok.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok.setCellFactory(column -> {
            return new TableCell<DataCourier, Button>() {
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

        dataCourier();
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");

            }
        });

    }

    private void dataCourier() throws SQLException, ClassNotFoundException {
        ArrayList<String> p = DatabaseConnection.getInstance().dataCourier();
        ObservableList<DataCourier> datas = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Подтвердить");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        parcelStatus(s[0]);
                        dataCourier();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            datas.add(new DataCourier(s[0], s[1], s[2], s[3], button));
        }

        table.setItems(datas);
        table.refresh();
    }

    private void parcelStatus(String parcelId) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().parcleStatusCourier(parcelId);
    }

}

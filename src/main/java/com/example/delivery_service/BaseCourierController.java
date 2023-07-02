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
    private TableColumn<Courier, String> address_recipient;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<Courier, String> name_recepient;

    @FXML
    private TableColumn<Courier, String> number_recepient;

    @FXML
    private TableColumn<Courier, Button> ok;

    @FXML
    private TableColumn<Courier, String> parcels_id;

    @FXML
    private TableView<Courier> table;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        table.setEditable(true);

        parcels_id.setCellValueFactory(new PropertyValueFactory<>("parcles_id"));
        name_recepient.setCellValueFactory(new PropertyValueFactory<>("name_recipient"));
        number_recepient.setCellValueFactory(new PropertyValueFactory<>("number_recipient"));
        address_recipient.setCellValueFactory(new PropertyValueFactory<>("address"));
        ok.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok.setCellFactory(column -> {
            return new TableCell<Courier, Button>() {
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
        ObservableList<Courier> dat = FXCollections.observableArrayList();
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
            dat.add(new Courier(s[0], s[1], s[2], s[3], button));
        }

        table.setItems(dat);
        table.refresh();
    }

    private void parcelStatus(String parcelId) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().parcleStatusCourier(parcelId);
    }

}

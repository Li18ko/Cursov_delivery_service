package com.example.delivery_service;

import java.sql.SQLException;
import java.util.ArrayList;

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
    private TableColumn<InformForCourier, String> address_recipient;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<InformForCourier, String> name_recepient;

    @FXML
    private TableColumn<InformForCourier, String> number_recepient;

    @FXML
    private TableColumn<InformForCourier, Button> ok;

    @FXML
    private TableColumn<InformForCourier, String> parcels_id;

    @FXML
    private TableView<InformForCourier> table;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        table.setEditable(true);
        parcels_id.setCellValueFactory(new PropertyValueFactory<>("parcels_id"));
        name_recepient.setCellValueFactory(new PropertyValueFactory<>("name"));
        number_recepient.setCellValueFactory(new PropertyValueFactory<>("number"));
        address_recipient.setCellValueFactory(new PropertyValueFactory<>("address"));
        ok.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok.setCellFactory(column -> {
            return new TableCell<InformForCourier, Button>() {
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
        ObservableList<InformForCourier> dat = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Подтвердить");
            InformForCourier informForCourier = new InformForCourier(s[0], s[1], s[2], s[3], button);
            dat.add(informForCourier);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        parcelStatus(informForCourier);
                        dataCourier();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        table.setItems(dat);
        table.refresh();
    }

    private void parcelStatus(InformForCourier informForCourier) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().parcleStatusCourier(informForCourier);
    }

}

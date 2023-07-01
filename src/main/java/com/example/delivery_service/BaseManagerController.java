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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<DataManager, String> center_delivery;

    @FXML
    private TableColumn<DataManager, String> data;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<DataManager, Button> ok;


    @FXML
    private TableColumn<DataManager, String> parcels_id;

    @FXML
    private Button registrCourier;

    @FXML
    private TableView<DataManager> table;

    @FXML
    private TableColumn<DataManager, String> typeDelivery;

    @FXML
    private TableColumn<DataManager, String> weight;

    private Button button;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        button = new Button("Подтвердить");

        parcels_id.setCellValueFactory(new PropertyValueFactory<>("parcle_id"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        typeDelivery.setCellValueFactory(new PropertyValueFactory<>("typeDelivery"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        center_delivery.setCellValueFactory(new PropertyValueFactory<>("center_delivery"));
        ok.setCellValueFactory(new PropertyValueFactory<>("button"));

        dataManager();
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");

            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataManager data = table.getSelectionModel().getSelectedItem();
                if (data != null) {
                    String parcleId = data.getParcle_id();
                    try {
                        parcleStatus(parcleId);
                        dataManager();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }

    private void dataManager() throws SQLException, ClassNotFoundException {
        ArrayList<String> p = DatabaseConnection.getInstance().dataManager();
        ObservableList<DataManager> datas = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            datas.add(new DataManager(s[0], s[1], s[2], s[3], s[4]));
        }
        table.setItems(datas);
    }

    private void parcleStatus(String id) throws SQLException, ClassNotFoundException {
        parcleStatus(id);
    }


}

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

public class BaseAdminController {

    @FXML
    private TableColumn<informAdmin, String> center_delivery_courier;

    @FXML
    private TableColumn<informAdmin, String> center_delivery_manager;

    @FXML
    private TableView<informAdmin> courier;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<informAdmin, String> login_courier;

    @FXML
    private TableColumn<informAdmin, String> login_manager;

    @FXML
    private TableView<informAdmin> manager;

    @FXML
    private TableColumn<informAdmin, String> name_courier;

    @FXML
    private TableColumn<informAdmin, String> name_manager;


    @FXML
    private TableColumn<informAdmin, String> number_courier;;

    @FXML
    private TableColumn<informAdmin, Button> ok_courier;

    @FXML
    private TableColumn<informAdmin, Button> ok_manager;

    @FXML
    private Button nearest_dc;

    @FXML
    private Button registrCourier;

    @FXML
    private Button registrManager;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        manager.setEditable(true);
        courier.setEditable(true);

        name_manager.setCellValueFactory(new PropertyValueFactory<>("name_manager"));
        login_manager.setCellValueFactory(new PropertyValueFactory<>("login_manager"));
        center_delivery_manager.setCellValueFactory(new PropertyValueFactory<>("center_delivery_manager"));
        ok_manager.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok_manager.setCellFactory(column -> {
            return new TableCell<informAdmin, Button>() {
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

        name_courier.setCellValueFactory(new PropertyValueFactory<>("name_courier"));
        number_courier.setCellValueFactory(new PropertyValueFactory<>("number_courier"));
        login_courier.setCellValueFactory(new PropertyValueFactory<>("login_courier"));
        center_delivery_courier.setCellValueFactory(new PropertyValueFactory<>("center_delivery_courier"));
        ok_courier.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok_courier.setCellFactory(column -> {
            return new TableCell<informAdmin, Button>() {
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

        dataADMIN();

        registrCourier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "registrationCourier.fxml", "ADMIN");

            }
        });
        registrManager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "registrationManager.fxml", "ADMIN");

            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "nearestDcAdmin.fxml", "Delivery Service");

            }
        });

        nearest_dc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "startSheet.fxml", "ADMIN");

            }
        });




    }

    private void dataADMIN() throws SQLException, ClassNotFoundException {
        ArrayList<String> p = DatabaseConnection.getInstance().adminCourier();
        ObservableList<informAdmin> datas = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Удалить");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        deleteCourier(s[2]);
                        dataADMIN();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            datas.add(new informAdmin(s[0], s[1], s[2], s[3], button));

        }

        ArrayList<String> q = DatabaseConnection.getInstance().adminManager();
        ObservableList<informAdmin> data = FXCollections.observableArrayList();
        for (int i = 0; i < q.size(); i++){
            String str = q.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Удалить");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        deleteManager(s[1]);
                        dataADMIN();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            data.add(new informAdmin(s[0], s[1], s[2], button));
        }

        courier.setItems(datas);
        manager.setItems(data);
        manager.refresh();
        courier.refresh();
    }

    private void deleteCourier(String login) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().deleteCourier(login);
    }

    private void deleteManager(String login) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().deleteManager(login);
    }


}

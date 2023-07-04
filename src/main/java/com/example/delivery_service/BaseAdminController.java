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
    private TableColumn<InformForAdmin, String> center_delivery_courier;

    @FXML
    private TableColumn<InformForAdmin, String> center_delivery_manager;

    @FXML
    private TableView<InformForAdmin> courier;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<InformForAdmin, String> login_courier;

    @FXML
    private TableColumn<InformForAdmin, String> login_manager;

    @FXML
    private TableView<InformForAdmin> manager;

    @FXML
    private TableColumn<InformForAdmin, String> name_courier;

    @FXML
    private TableColumn<InformForAdmin, String> name_manager;


    @FXML
    private TableColumn<InformForAdmin, String> number_courier;;

    @FXML
    private TableColumn<InformForAdmin, Button> ok_courier;

    @FXML
    private TableColumn<InformForAdmin, Button> ok_manager;

    @FXML
    private Button nearest_dc;

    @FXML
    private Button registrAdmin;

    @FXML
    private Button registrDelivery_center;

    @FXML
    private Button registrCourier;

    @FXML
    private Button registrManager;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        manager.setEditable(true);
        courier.setEditable(true);

        name_manager.setCellValueFactory(new PropertyValueFactory<>("name"));
        login_manager.setCellValueFactory(new PropertyValueFactory<>("login"));
        center_delivery_manager.setCellValueFactory(new PropertyValueFactory<>("center_delivery"));
        ok_manager.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok_manager.setCellFactory(column -> {
            return new TableCell<InformForAdmin, Button>() {
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

        name_courier.setCellValueFactory(new PropertyValueFactory<>("name"));
        number_courier.setCellValueFactory(new PropertyValueFactory<>("number"));
        login_courier.setCellValueFactory(new PropertyValueFactory<>("login"));
        center_delivery_courier.setCellValueFactory(new PropertyValueFactory<>("center_delivery"));
        ok_courier.setCellValueFactory(new PropertyValueFactory<>("button"));

        // Установка фабрики значений для столбца "ok"
        ok_courier.setCellFactory(column -> {
            return new TableCell<InformForAdmin, Button>() {
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
        registrAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "registrationAdmin.fxml", "ADMIN");

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
                Transition.changeScene(event, "startSheet.fxml", "Delivery Service");

            }
        });

        nearest_dc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "nearestDcAdmin.fxml", "ADMIN");

            }
        });
        registrDelivery_center.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Transition.changeScene(event, "createCenter.fxml", "ADMIN");

            }
        });




    }

    private void dataADMIN() throws SQLException, ClassNotFoundException {
        ArrayList<String> p = DatabaseConnection.getInstance().adminCourier();
        ObservableList<InformForAdmin> datas = FXCollections.observableArrayList();
        for (int i = 0; i < p.size(); i++){
            String str = p.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Удалить");
            InformForAdmin informForAdmin = new InformForAdmin(s[0], s[1], s[2], s[3], button);
            datas.add(informForAdmin);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        deleteCourier(informForAdmin);
                        dataADMIN();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });;

        }

        ArrayList<String> q = DatabaseConnection.getInstance().adminManager();
        ObservableList<InformForAdmin> data = FXCollections.observableArrayList();
        for (int i = 0; i < q.size(); i++){
            String str = q.get(i);
            String[] s = str.split("\\*");
            Button button = new Button("Удалить");
            InformForAdmin informForAdmin1 = new InformForAdmin(s[0], s[1], s[2], button);
            data.add(informForAdmin1);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        deleteManager(informForAdmin1);
                        dataADMIN();
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        courier.setItems(datas);
        manager.setItems(data);
        manager.refresh();
        courier.refresh();
    }

    private void deleteCourier(InformForAdmin informForAdmin) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().deleteCourier(informForAdmin);
    }

    private void deleteManager(InformForAdmin informForAdmin) throws SQLException, ClassNotFoundException {
        DatabaseConnection.getInstance().deleteManager(informForAdmin);
    }


}

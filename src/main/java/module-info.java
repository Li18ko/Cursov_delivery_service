module com.example.delivery_service {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.prefs;


    opens com.example.delivery_service to javafx.fxml;
    exports com.example.delivery_service;
}
module com.example.finalproject {
/**
 * Final project.
 */

    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalproject to javafx.fxml;
    exports com.example.finalproject;

    opens controller to javafx.fxml;
    exports controller;

    opens model to javafx.fxml;
    exports model;
}
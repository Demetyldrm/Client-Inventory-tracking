package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Inventory;

import java.io.IOException;

/**
 * This is the main application class. It is where the program starts and launches the main screen.
 */


public class MainApplication extends Application {

    /**
     * This is the start method.It will be called at when the launch method is called.
     * This is where the first FXML form is initialized.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainScreen.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 921, 575);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
        Inventory.addTestData();


    }

}


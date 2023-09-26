package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * * This class controls the Add Part form.
 */
public class AddPartController implements Initializable {

    /**
     * creates label to set the machine id.
     */

    public Label lblMachineID;


    public static int id = 6;

    /**
     * The JavaFX Stage class is the top level JavaFX container.
     * The primary Stage is constructed by the platform.
     * Additional Stage objects may be constructed by the application.
     * Stage objects must be constructed and modified on the JavaFX Application Thread.
     */

    @FXML
    private AnchorPane AnchorPaneAddPart;
    @FXML
    private HBox hBoxAddPart;
    @FXML
    private GridPane GridPane;
    @FXML
    private TextField txtID;
    @FXML
    private Label lblAddPart;
    @FXML
    private RadioButton rBAddPartOutsourced;
    @FXML
    private RadioButton rBAddPartInHouse;
    @FXML
    private Label lblInv;
    @FXML
    private Label lblMax;
    @FXML
    private TextField txtMin;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPrice;
    @FXML
    private Button btnSavePart;
    @FXML
    private Button btnCancelPart;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtMachineID;
    @FXML
    private TextField txtMax;
    @FXML
    private Label lblMin;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;

    /**
     * This method sets the label field to Company name when it is clicked.
     * @param event sets the label to Company name
     */
    @FXML
    void rBOutsourcedOnAction(ActionEvent event) {
        lblMachineID.setText("Company Name");
    }

    /**
     * This method sets the label field to Machine ID when it is clicked.
     * @param event machine id label is created.
     */
    @FXML
    void rBInHouseOnAction(ActionEvent event) {
        lblMachineID.setText("Machine ID");

    }
    /**
     * This method saves the data and adds it to the Main screen when it is clicked.
     * It throws exception when the text field is empty, or wrong input is entered.
     * @param event saves the data when save button is clicked.
     * @throws IOException
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        String name = txtName.getText();
        if (name.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Must enter value into field");
            alert.showAndWait();
            return;
        }
        String invString = txtInv.getText();
        int inv = 0;
        try {
            inv = Integer.parseInt(invString);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Must enter a number");
            alert.showAndWait();
            return;
        }
        String priceString = txtPrice.getText();
        double price = 0;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Must enter a number");
            alert.showAndWait();
            return;
        }
        String minS = txtMin.getText();
        int min = 0;
        try {
            min = Integer.parseInt(minS);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Please enter a number");
            alert.showAndWait();
            return;
        }
        String maxS = txtMax.getText();
        int max = 0;
        try {
            max = Integer.parseInt(maxS);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Please enter a number");
            alert.showAndWait();
            return;
        }
        if (min > max) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Minimum stock must be less than maximum stock");
            alert.showAndWait();
            return;
        }
        if ((inv > max) || (inv < min)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Inventory must be between Min and Max values");
            alert.showAndWait();
            return;
        }
        String dynamic = txtMachineID.getText();
        System.out.println("The Part " + name + " " + inv + " " + price + " " + min + " " + max + " " + "is added to the Inventory");
        if (rBAddPartInHouse.isSelected()) {
            int machineId = 0;
            try {
                machineId = Integer.parseInt(dynamic);
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Must enter a number");
                alert.showAndWait();
            }

            InHouse h7 = new InHouse(Inventory.generatePartID(), name, price, inv, min, max, machineId);
            Inventory.addPart(h7);
        }else {
            String companyName= dynamic;
            Part p = new OutSourced(Inventory.generatePartID(),name,price,inv,min,max,companyName);
            Inventory.addPart(p);
        }

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/finalproject/MainScreen.fxml"));
            Stage stage = (Stage) btnCancelPart.getScene().getWindow();
            Scene scene = new Scene(root, 921, 575);
            stage.setTitle("Inventory System");
            stage.setScene(scene);
            stage.show();

    }

    /**
     * This is the method for cancelling a part .It takes the user to the Main Screen when it is clicked.
     * @param event cancel button that takes the user to the main screen.
     * @throws IOException
     */
    @FXML
    void onActionCancelPart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/finalproject/MainScreen.fxml"));
        Stage stage = (Stage) btnCancelPart.getScene().getWindow();
        Scene scene = new Scene(root, 921, 575);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the initialize method for the Add Part controller.
     * @param url passes the data associated with the FXML file
     * @param resourceBundle manages location specific resources (strings, various data-types, etc)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}


package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
 * This class controls the Modify Part form.
 */

public class ModifyPartController implements Initializable {
    Parent scene;
    Stage stage;


    @FXML
    private Label lblModifyPart;
    @FXML
    private AnchorPane anchPane;
    @FXML
    private HBox hBox;
    @FXML
    private RadioButton rBInHouse;
    @FXML
    private RadioButton rBOutsourced;
    @FXML
    private Label lblID;
    @FXML
    private Label lblName;
    @FXML
    private Label lblInv;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblMax;
    @FXML
    private Label lblMin;
    @FXML
    private Label lblMachineId;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtMin;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtMachineID;

    /**
     * This method sets the label to Machine ID when In-House radio button is selected .
     * @param event radio button for the in-house parts .
     */
    @FXML
    void rBInHouseOnAction(ActionEvent event) {
        lblMachineId.setText("Machine ID");

    }

    /**
     * This method will erase all unsaved data in text fields and return to the main screen
     * @param event cancel button.
     * @throws IOException
     */
    @FXML
    void btnCancelOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/finalproject/MainScreen.fxml"));
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        Scene scene = new Scene(root, 921, 575);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method sets the label to Company name when Outsourced radio button is selected.
     * @param event outsourced radio button.
     * @throws IOException
     */
    @FXML
    void rBOutsourcedOnAction(ActionEvent event) {
        lblMachineId.setText("Company Name");

    }

    /**
     * This method saves the user input, validation, object creation and return to main screen.
     * @param event save button for modify part form.
     */
    @FXML
    void btnSaveOnAction(ActionEvent event) {

        try {
            int id = Integer.parseInt(txtID.getText());
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int stock = Integer.parseInt(txtInv.getText());
            int min = Integer.parseInt(txtMin.getText());
            int max = Integer.parseInt(txtMax.getText());

            if (MainScreenController.confirmDialog("Save", "Are you sure to save this part?"))
                if (max < min) {
                    MainScreenController.errorDialog("Input Error", "Error in Max/Min fields", "Min value must be less than Max");
                }
            if (stock < min || stock > max) {
                MainScreenController.errorDialog("Input Error", "Error in Inventory field", "Inventory value must be between Min and Max");
            } else {
                if (rBInHouse.isSelected()) {
                    int machineID = Integer.parseInt(txtMachineID.getText());
                    InHouse part = new InHouse(id, name, price, stock, min, max, machineID);
                    Inventory.updatePart(part.getId(), part);
                    System.out.println("the part is saved");
                } else {
                    String companyName = lblMachineId.getText();
                    OutSourced part = new OutSourced(id, name, price, stock, min, max, companyName);
                    Inventory.updatePart(part.getId(), part);

                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/com/example/finalproject/MainScreen.fxml"));
                stage.setTitle("Inventory Management System");
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is to set data in the text fields when a part object is selected from the main screen.
     * @param part sets the data when a part is selected and modified.
     */
    @FXML
    public void setPart(Part part) {
        txtID.setText(Integer.toString(part.getId()));
        txtName.setText(part.getName());
        txtInv.setText(Integer.toString(part.getStock()));
        txtPrice.setText(Double.toString(part.getPrice()));
        txtMax.setText(Integer.toString(part.getMax()));
        txtMin.setText(Integer.toString(part.getMin()));
        if (part instanceof InHouse) {
            rBInHouse.setSelected(true);
            lblMachineId.setText("Machine ID");
            txtMachineID.setText(Integer.toString(((InHouse) part).getMachineID()));

        } else {
            rBOutsourced.setSelected(true);
            lblMachineId.setText("Company Name");

        }
    }

    /**
     * This is to initialize method for the Modify Part controller.
     * @param url passes the data associated with the FXML file
     * @param resourceBundle manages location specific resources .
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls the modify product form
 * @author Demet Yildirim
 */
public class ModifyProductController implements Initializable {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private Product productModify;

    @FXML
    private AnchorPane anchPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label lblId;
    @FXML
    private TextField txtId;
    @FXML
    private Label lblInv;
    @FXML
    private Label lblMax;
    @FXML
    private Label lblPrice;
    @FXML
    private TableView<Part> tblView;
    @FXML
    private VBox vBox;
    @FXML
    private Button btnAdd;
    @FXML
    private Label lblModifyProduct;
    @FXML
    private Button btnCancel;
    @FXML
    private TableColumn<?, ?> tCol2Inv;
    @FXML
    private TableColumn<?, ?> tColInv;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtMax;
    @FXML
    private Label lblMin;
    @FXML
    private TextField txtMin;
    @FXML
    private Label lblName;
    @FXML
    private TextField txtName;
    @FXML
    private TableColumn<?, ?> tCol2Id;
    @FXML
    private TableColumn<?, ?> tColId;
    @FXML
    private TableColumn<?, ?> tCol2Name;
    @FXML
    private TableColumn<?, ?> tColName;
    @FXML
    private TableColumn<?, ?> tCol2Price;
    @FXML
    private TableColumn<?, ?> tColPrice;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<Part> tblView2;

    /**
     * This method is for adding a part to the associated parts table.
     * @param event add button for Modify product screen.
     */

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Part selectedPart = (Part) tblView.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedParts.add(selectedPart);

        }
        else {
            MainScreenController.errorDialog("Error", "A Part must be selected", "Select a Part to Add to Product");
        }
        tblView2.setItems(associatedParts);
        tCol2Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tCol2Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tCol2Inv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tCol2Price.setCellValueFactory(new PropertyValueFactory<>("price"));


    }


    /**
     * This method cancels the inputs without saving any data and takes the user to the Main Screen.
     * @param event cancel button for modify product screen.
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
     * This method removes the selected part from the associated parts list.
     * @param event remove button that removes parts from the associated parts list
     */
    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        Part selectedPart = tblView2.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
            if (MainScreenController.confirmDialog("Removing Part", "Are you sure to remove the part?")) {
                associatedParts.remove(selectedPart);

            }
        }
        else {
            MainScreenController.errorDialog("Error", "Select a Part", "A part must be selected to remove from Product");
        }

        }

    /**
     * @param event saves the product in the product table and returns to the main screen.
     */
    @FXML
    void btnSaveProductOnAction (ActionEvent event) throws IOException {

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
        if (min > max || min<1){
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

        int index = Inventory.getAllProducts().indexOf(productModify);
        Product product = new Product(productModify.getId(), name, price,inv, min, max, associatedParts);
//        for (Part p:associatedParts){
//            product.addAssociatedPart(p);
//        }
        System.out.println(product.getAllAssociatedParts().size());
        Inventory.updateProduct(index, product);

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/finalproject/MainScreen.fxml"));
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        Scene scene = new Scene(root, 921, 575);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();

        }

    /**
     * This is the initialize method for the Modify Product controller.
     * @param url passes the data associated with the FXML file
     * @param resourceBundle manages location specific resources .
     */
    @Override
        public void initialize (URL url, ResourceBundle resourceBundle) {

        tblView.setItems(Inventory.getAllParts());
        tColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tColInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        tblView2.setItems(associatedParts);
        tCol2Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tCol2Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tCol2Inv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tCol2Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        }

    public void setProduct (Product product){

        productModify = product;
        associatedParts.addAll(productModify.getAllAssociatedParts());
        txtId.setText(Integer.toString(product.getId()));
        txtName.setText(product.getName());
        txtInv.setText(Integer.toString(product.getStock()));
        txtPrice.setText(Double.toString(product.getPrice()));
        txtMax.setText(Integer.toString(product.getMax()));
        txtMin.setText(Integer.toString(product.getMin()));
        }

    /**
     * This method allows users to search for a part by part ID or name.
     * @param actionEvent search field for modify product screen.
     */
    public void txtSearchOnAction (ActionEvent actionEvent){
            String q = txtSearch.getText();

            ObservableList<Part> parts = Inventory.lookupPart(q);

            if (parts.size() == 0) {
                try {
                    int id = Integer.parseInt(q);
                    parts = Inventory.lookupPart(id);

                } catch (NumberFormatException e) {
                    if (parts.size() == 0) {
                        MainScreenController.errorDialog("Error", "Part was not found", "Search for Part by Name or ID");
                    }


                }


            }
            tblView.setItems(parts);
        }
    }





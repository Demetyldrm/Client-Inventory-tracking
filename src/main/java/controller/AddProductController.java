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
import java.util.EventObject;
import java.util.ResourceBundle;

/**
 * This class will utilize the AddProduct form which creates a new instance of a product to be added into the inventory.
 *  This class controls the Add product form
 */
public class AddProductController implements Initializable {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    //Product product1 = new Product(0, "Mountain Bike", 150.00, 5, 1, 6, associatedParts);


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
    private Button btnAddProduct;
    @FXML
    private Label lblAddProduct;
    @FXML
    private Button btnCancelProduct;
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
    private TableColumn<?, ?> tColId;
    @FXML
    private TableColumn<?, ?> tColName;
    @FXML
    private TableColumn<?, ?> tColInv;
    @FXML
    private TableColumn<?, ?> tColPrice;
    @FXML
    private TableColumn<?, ?> tCol2Id;
    @FXML
    private TableColumn<?, ?> tCol2Name;
    @FXML
    private TableColumn<?, ?> tCol2Inv;
    @FXML
    private TableColumn<?, ?> tCol2Price;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnRemoveProduct;
    @FXML
    private Button btnSaveProduct;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<Part> tblView2;

    /**
     * Add method for the product controller. It adds a selected product to the associated part table.
     * Throws exception when a part is not selected.
     * @param event is triggered when add button is clicked .
     */
    @FXML
    void btnAddProductOnAction(ActionEvent event) {
        Part partSelect = tblView.getSelectionModel().getSelectedItem();
        if (partSelect == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Please select a Part from top table");
            alert.showAndWait();
            return;
        } else {
            associatedParts.add(partSelect);
        }

    }

    FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.getResource("controller/MainScreenController.java"));

    /**
     * This is the cancel button for the Add Product Controller. It takes the user to the Main Screen.
     * @param event cancel button is clicked.
     * @throws IOException
     */
    @FXML
    void btnCancelProductOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/finalproject/MainScreen.fxml"));
        Stage stage = (Stage) btnCancelProduct.getScene().getWindow();
        Scene scene = new Scene(root, 921, 575);
        stage.setTitle("Inventory System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the search method for the Add Product controller .
     * User can search for products using product full or partial name , or Product ID.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    void txtSearchOnAction(ActionEvent actionEvent) throws IOException {
        int ID = -1;
        String Name = "";
        try {

            ID = Integer.parseInt(txtSearch.getText());
            tblView.setItems(Inventory.lookupPart(ID));

        } catch (Exception e) {
            Name = txtSearch.getText();
            tblView.setItems(Inventory.lookupPart(Name));

        }

        tColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tColInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * this Remove method deletes the selected part from the Associated part table.
     * @param event
     */
    @FXML
    void btnRemoveProductOnAction(ActionEvent event) {

    }
    /**
     * This is the initialize method for the Add Product controller class.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    /**
     * This is the Save button for the Add Product screen. It adds the input product data to the Main Screen.
     * @param actionEvent
     * @throws IOException
     */
    public void btnSaveProductOnAction(ActionEvent actionEvent) throws IOException {


        String name = null;
        int id = 0;
        double price = 0;
        int min = 0;
        int max = 0;
        int inv = 0;
        try {
            id = Inventory.generateProductID();
            name = txtName.getText();
            price = Double.parseDouble(txtPrice.getText());
            inv = Integer.parseInt(txtInv.getText());
            min = Integer.parseInt(txtMin.getText());
            max = Integer.parseInt(txtMax.getText());
        } catch (NumberFormatException e) {
            MainScreenController.errorDialog("Input Error", "Error in saving product", "Check fields for proper input");
        }

            if (max < min) {
                MainScreenController.errorDialog("Input Error", "Error in Max/Min fields", "Min value must be less than Max");
                return;

            }

            if (inv < min || inv > max) {
                MainScreenController.errorDialog("Input Error", "Error in Inventory field", "Inventory value must be between Min and Max");
                return;
            }

            if (MainScreenController.confirmDialog("Save", "Are you sure to save this product?")) {

                Product product = new Product(id,name, price, inv, min, max,associatedParts);
                Inventory.addProduct(product);

                Parent root = FXMLLoader.load(getClass().getResource("/com/example/finalproject/MainScreen.fxml"));
                Stage stage = (Stage) btnCancelProduct.getScene().getWindow();
                Scene scene = new Scene(root, 921, 575);
                stage.setTitle("Inventory System");
                stage.setScene(scene);
                stage.show();

            }


//
//            Product P1 = new Product(id, name, price, inv, min, max, associatedParts);
//            for (Part P : associatedParts) {
//                P1.addAssociatedPart(P);
//            }
//            Inventory.addProduct(P1);



    }
}









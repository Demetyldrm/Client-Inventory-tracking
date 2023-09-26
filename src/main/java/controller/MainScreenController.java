package controller;

import com.example.finalproject.MainApplication;
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
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Main screen controller class. This class utilizes the methods for the main screen form.
 * It is the home page for the application.
 */
public class MainScreenController implements Initializable {


     Stage stage;
     Parent scene;

    @FXML
    private Button btnAddPart;
    @FXML
    private Button btnAddProduct;
    @FXML
    private AnchorPane anchPane;
    @FXML
    private Button btnDeletePart;
    @FXML
    private Button btnDeleteProduct;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblInv;
    @FXML
    private Label lblParts;
    @FXML
    private Label lblProducts;
    @FXML
    private Button btnModifyPart;
    @FXML
    private Button btnModifyProduct;
    @FXML
    private TableColumn<?, ?> partId;
    @FXML
    private TableColumn<?, ?> partName;
    @FXML
    private TableColumn<?, ?> partInv;
    @FXML
    private TableColumn<?, ?> partPrice;
    @FXML
    private TableColumn<?, ?> productId;
    @FXML
    private TableColumn<?, ?> productName;
    @FXML
    private TableColumn<?, ?> productInv;
    @FXML
    private TableColumn<?, ?> productPrice;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TextField txtSearchPart;
    @FXML
    private TextField txtSearchProduct;

    /**
     * These methods contain the observable lists.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static URL getResource(String s) {
        return null;
    }

    /**
     * This is the Add method on the Main Screen controller. It adds a new part to the part table when it is clicked.
     * @param event
     * @throws IOException
     */
    @FXML
    void OnActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AddPart.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), 921, 575);
        stage.setTitle("Add Part");
        stage.setScene(scene1);
        stage.show();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        System.out.println("Part added");

    }

    /**
     * This is the Add method on the Main Screen controller . It adds the product when the user enters the data in the necessary fields.
     * And shows it on the Main Screen.
     * @param event occurs when the add button is clicked.
     * @throws IOException
     */
    @FXML
    void OnActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addproduct.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 921, 700);
        stage.setTitle("Add Product");
        stage.setScene(scene2);
        stage.show();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        System.out.println("Product Added");

    }

    /**
     * This method deletes the selected part from the Parts table.
     * It throws exception when a part is not selected.
     * @param event delete button .
     * @throws IOException
     */
    @FXML
    void OnActionDeletePart(ActionEvent event) throws IOException {

        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Please select a part");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Parts");
        alert.setHeaderText("Delete");
        alert.setContentText("Do you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            btnDeletePart(selectedPart);
            Inventory.deletePart(partTable.getSelectionModel().getSelectedItem());

        }
    }

    /**
     * this is the button for deleting a part.
     * @param selectedPart the button deletes the selected part.
     */
    private void btnDeletePart(Part selectedPart) {
    }

    /**
     * This method is for deleting a product from the product table.
     * It throws an exception when a product is not selected.
     * @param event the button deletes the product.
     * @throws IOException
     */
    @FXML
    void OnActionDeleteProduct(ActionEvent event) throws IOException {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Please select a product");
            alert.showAndWait();
            return;
        }

        try {
            Product product = productTable.getSelectionModel().getSelectedItem();

        }
        catch (Exception e) {
            MainScreenController.errorDialog("Selection Error", "No Product Selected", "Select Product to delete.");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Products");
        alert.setHeaderText("Delete");
        alert.setContentText("Do you want to delete this product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            btnDeleteProduct(selectedProduct);
            Inventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * This method is for exiting the application.It exits the application when it is clicked.
     * @param event exits the main form .
     * @throws IOException
     */
    @FXML
    void OnActionExitMainForm(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

        }
        System.exit(0);
    }

    /**
     * This method is for modifying the parts table.
     * When the modify button is clicked, it sends the user to the ModifyPart screen.
     * An error will occur if no part is selected in the part table view.
     * This exception would occur when the modify button was selected without first selected a part to modify.
     * @param event occurs when the modify part button is selected .
     * @throws IOException
     */
    @FXML
    void OnActionModifyPart(ActionEvent event) throws IOException {

        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Please select a part");
            alert.showAndWait();
            return;
        }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/finalproject/modifypart.fxml"));
            Parent parent = loader.load();
            ModifyPartController modifyPartController = loader.getController();
            modifyPartController.setPart(partTable.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(parent, 640, 480);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }

    /**
     * This method is for modifying the Product table. It takes the user to the Modify Product screen.
     * @param event gets triggered when the modify product button is clicked.
     * RUNTIME ERROR : I got a runtime error when I tried to modify products. It was not saving the modifications to the associated part table.
     * error is fixed by  changing the products to parts in the product class as followed .ObservableList<Part> associatedParts.
     * FUTURE ENHANCEMENT: I would want to be able to see in-house and outsourced parts on the Main Form separately.
     * @throws IOException
     */

    @FXML
            void OnActionModifyProduct (ActionEvent event) throws IOException {

                Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
                if (selectedProduct == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("Please select a product");
                    alert.showAndWait();
                    return;
                }

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/finalproject/ModifyProduct.fxml"));
                Parent parent = loader.load();
                ModifyProductController modifyProductController = loader.getController();
                modifyProductController.setProduct(productTable.getSelectionModel().getSelectedItem());
                Scene scene = new Scene(parent, 930, 680);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

                }

    /**
     * Adding the try and catch fixed that problem.
     * This is the Search method on the Main screen.
     * It allows user to search a port by the full or partial name or part ID.
     * @param actionEvent search text field.
     */
    @FXML
    public void SearchPart (ActionEvent actionEvent){
            int ID = -1;
            String Name = "";
            try {

                ID = Integer.parseInt(txtSearchPart.getText());
                partTable.setItems(Inventory.lookupPart(ID));

            } catch (Exception e) {
                Name = txtSearchPart.getText();
                partTable.setItems(Inventory.lookupPart(Name));

            }

            partId.setCellValueFactory(new PropertyValueFactory<>("id"));
            partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            partInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
            partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        }

    /**
     * This is the search method on the Main Screen controller. It allows user to search for a product.
     * It allows users to search for a product by the full or partial product name or product ID.
     * @param event search product text field.
     */
    @FXML
        void SearchProduct (ActionEvent event){
            int ID = -1;
            String Name = "";
            try {

                ID = Integer.parseInt(txtSearchProduct.getText());
                productTable.setItems(Inventory.lookupProduct(ID));

            } catch (Exception e) {
                Name = txtSearchProduct.getText();
                productTable.setItems(Inventory.lookupProduct(Name));

            }

            productId.setCellValueFactory(new PropertyValueFactory<>("id"));
            productName.setCellValueFactory(new PropertyValueFactory<>("name"));
            productInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
            productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        }

    /**
     * This method contains the parameters for error messages. Error windows open when a wrong input enters to the fields.
     * @param title parameters for the error dialog boxes.
     * @param header parameters for the error dialog boxes.
     * @param message parameters for the error dialog boxes.
     */
    static void errorDialog(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * This is the confirmation dialog method. It opens windows when user adds and deletes parts and products and when user wants to exit the application.
     * @param title parameters for the confirmation dialog boxes.
     * @param content parameters for the confirmation dialog boxes.
     * @return parameters for the error confirmation boxes.
     */

    static boolean confirmDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is the initialize method for the Main Screen controller.
     * @param url passes the data associated with the FXML file
     * @param resourceBundle manages location specific resources .
     */


    @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            //Inventory.addTestData();
            partTable.setItems(Inventory.getAllParts());
            partId.setCellValueFactory(new PropertyValueFactory<>("id"));
            partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            partInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
            partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


            productTable.setItems(Inventory.getAllProducts());
            productId.setCellValueFactory(new PropertyValueFactory<>("id"));
            productName.setCellValueFactory(new PropertyValueFactory<>("name"));
            productInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
            productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        }

    /**
     * This is the delete product button for the Main screen.
     * @param selectedProduct deletes the selected product.
     */
    private void btnDeleteProduct(Product selectedProduct) {
    }
}
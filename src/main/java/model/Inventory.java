package model;
/**
 *
 * @author Demet Yildirim
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the inventory class which contains parts and products sub-classes..
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partID =1;
    private static int productID=1;

    public static int generatePartID()
    {
        return partID++;
    }
    public static int generateProductID() {return productID++;}

    /**
     * This is the lookupPart method using the partial name.
     * helps the user searches for a part by using full or partial name.
     * @param partialName partial name is used for searching for a part.
     * @return returns the part matches with the partial name.
     */
    public static ObservableList<Part> lookupPart(String partialName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partialName.toLowerCase())) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }

    /**
     * This method is for looking up a part using a part ID.
     * @param ID parameter for ID for searching a part.
     * @return the part with the matched part ID
     */
    public static ObservableList<Part> lookupPart(int ID) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getId() == ID) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }

    /**
     * This is the lookupProduct method that allows to look up a product using partial name .
     * @param partialName product searches using partial name.
     * @return returns the matched product searched with the partial name.
     */
    public static ObservableList<Product> lookupProduct(String partialName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(partialName.toLowerCase())) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    /**
     * This method is for looking up a part using the product ID.
     * @param ID lookup by ID number
     * @return returns the matched product searched with the ID number.
     */
    public static ObservableList<Product> lookupProduct(int ID) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getId() == ID) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    /**
     * This method is for adding a new part. Adds part to the observable list.
     * @param newPart is to add a new part.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * This method gets all parts from the observable list.
     * @return returns all the parts .
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * addProduct method for adding a new product .
     * @param newProduct new product.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * this method gets all products from the observable product list.
     * @return returns all products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Update part method to update a part based on index and part id.
     * Updates the part and adds it to the observable list.
     * @param index index
     * @param part part
     */
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);

    }

    /**
     * This method is for updating a product using index and product ID.
     * It updates the fields and adds the product to the observable list .
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);

    }
    /**
     * This method deletes selected parts.
     * @param selectedPart selected part.
     * @return returns a boolean true if the remove was successful, false if unsuccessful
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * This method deletes selected products.
     * @param selectedProduct selected product.
     * @return returns a boolean true if the remove was successful, false if unsuccessful
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     *This method adds test data to the application.
     */
    public static void addTestData() {
        InHouse Brake = new InHouse(generatePartID(), "Brake", 150.00, 10, 11, 20, 1);
        InHouse Seat = new InHouse(generatePartID(), "Seat", 25.00, 16, 1, 20, 3);

        OutSourced Pedal = new OutSourced(generatePartID(), "Pedal", 35.00, 10, 1, 20, "BMX");
        OutSourced HandleBar = new OutSourced(generatePartID(), "Handle Bar", 10.00, 5, 1, 40, "Schwinn");
        ObservableList<Part> nullParts = FXCollections.observableArrayList();
        ObservableList<Part> associatedParts = null;
        Product MountainBike = new Product(generateProductID(),"Mountain Bike", 650.00, 3, 1, 30);
        Product CityBike = new Product(generateProductID(),"City Bike",800.00,5,1,35);
        Product RaceBike = new Product(generateProductID(),"Race Bike",1800.00,7,1,38);

        Inventory.addPart(Brake);
        Inventory.addPart(Pedal);
        Inventory.addPart(Seat);
        Inventory.addPart(HandleBar);
        Inventory.addProduct(MountainBike);
        Inventory.addProduct(CityBike);
        Inventory.addProduct(RaceBike);


    }

}
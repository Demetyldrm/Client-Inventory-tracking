package model;
/**
 *
 * @author Demet Yildirim
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the Product Class
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * This is the product constructor that contains the parameters for the constructor.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max

*/
    public Product(int id, String name, double price, int stock, int min, int max) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.min = min;
    this.max = max;

    }
    public Product(int id, String name, double price, int stock, int min, int max, ObservableList<Part> associatedParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = associatedParts;

    }

    /**
     * returns the ID
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * ID to set
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * name to set
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the price
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * price to set
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * returns the stock
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     * stock to set
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * returns the Min value
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     * min value to set
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * returns the max value
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     * max value to set
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * returns the part
     * @param part
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.addAll(part);
    }

    /**
     * returns the selected associated part
     * @param selectedAssociatedPart
     * @return returns the selected parts.
     */

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     * returns the associated part
     * @return returns all associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;

    }
}
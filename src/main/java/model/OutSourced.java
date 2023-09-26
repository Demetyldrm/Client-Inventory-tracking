package model;
/**
 *
 * @author Demet Yildirim
 */

/**
 * This is the Outsourced class that inherits from the abstract Part class.
 */
public class OutSourced extends Part {

    private String companyName;

    /**
     * Constructor for the Outsourced class that contains the parameters.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Set method for the company name.
     * @param companyName company name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Return method for the company name.
     * @return
     */
    public String getCompanyName() {

        return companyName;
    }
}
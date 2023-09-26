package model;
/**
 *
 * @author Demet Yildirim
 */

/**
 * In-House class inherits from the abstract Part class.
 *
 */

public class InHouse extends Part {


        private int machineID;
        /**
         *This is the constructor for the In-House class.It contains the parameters for the constructor.
         *
         * @param id
         * @param name
         * @param price
         * @param stock
         * @param min
         * @param max
         * @param machineID
         */
public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
        }
        public void setMachineID() {
                setMachineID(0);
        }

        /**
         * This is the machine ID set method.
         * @param machineID
         */
        public void setMachineID(int machineID) {
                this.machineID = machineID;
        }
        /**
         * This is the machine ID return method.
         * @return
         */
        public int getMachineID(){
                return machineID;
        }

}




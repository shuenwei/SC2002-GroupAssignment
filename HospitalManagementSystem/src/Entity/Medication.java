package src.Entity;

/**
 * The {@code Medication} class represents a medication with attributes such as name, stock level,
 * and a low-stock threshold. It provides methods to get and set these attributes and to check
 * if the medication stock is below the threshold.
 */
public class Medication {
    private String medicineName;
    private int stock;
    private int lowStockThreshold;

    /**
     * Constructs a {@code Medication} object with the specified name, stock, and low-stock threshold.
     *
     * @param medicineName      the name of the medication
     * @param stock             the current stock level of the medication
     * @param lowStockThreshold the threshold at which the medication stock is considered low
     */
    public Medication(String medicineName, int stock, int lowStockThreshold) {
        this.medicineName = medicineName;
        this.stock = stock;
        this.lowStockThreshold = lowStockThreshold;
    }

    /**
     * Returns the name of the medication.
     *
     * @return the name of the medication
     */
    public String getMedicineName() {
        return this.medicineName;
    }

    /**
     * Sets the stock level of the medication.
     *
     * @param stock the new stock level
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns the current stock level of the medication.
     *
     * @return the current stock level
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * Sets the low-stock threshold for the medication.
     *
     * @param stockThreshold the new low-stock threshold
     */
    public void setStockThreshold(int stockThreshold) {
        this.lowStockThreshold = stockThreshold;
    }

    /**
     * Returns the low-stock threshold for the medication.
     *
     * @return the low-stock threshold
     */
    public int getStockThreshold() {
        return this.lowStockThreshold;
    }

    /**
     * Checks if the medication stock is below or equal to the low-stock threshold.
     *
     * @return {@code true} if the stock level is at or below the low-stock threshold, {@code false} otherwise
     */
    public boolean getIsLowStock() {
        if(this.stock - getStockThreshold() <= 0){
            return true;
        }
        else return false;
    }
}

package Entity;

public class Medication {
    private String medicineName;
    private int stock;
    private int lowStockThreshold;
    private Boolean isLowStock = false;

    public Medication(String medicineName, int stock, int lowStockThreshold) {
        this.medicineName = medicineName;
        this.stock = stock;
        if (stock <= this.lowStockThreshold) {
            this.isLowStock = true;
        }
    }
    public String getMedicineName() {
        return this.medicineName;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getStock() {
        return this.stock;
    }
    public Boolean getStockAlert() {
        return this.isLowStock;
    }
}
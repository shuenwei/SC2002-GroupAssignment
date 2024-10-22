package Entity;

public class Medication {
    private String medicineName;
    private int stock;
    private int lowStockThreshold;
    private String isLowStock = "NO";

    public Medication(String medicineName, int stock, int lowStockThreshold) {
        this.medicineName = medicineName;
        this.stock = stock;
        this.lowStockThreshold = lowStockThreshold;
        if (stock <= this.lowStockThreshold) {
            this.isLowStock = "YES";
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
    public void setStockThreshold(int stockThreshold) {
        this.lowStockThreshold = stockThreshold;
    }
    public int getStockThreshold() {
        return this.lowStockThreshold;
    }
    public void setIsLowStock(String isLowStock) {
        this.isLowStock = isLowStock;
    }
    public String getIsLowStock() {
        return this.isLowStock;
    }
}
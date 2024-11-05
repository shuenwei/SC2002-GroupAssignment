package Entity;

public class Medication {
    private String medicineName;
    private int stock;
    private int lowStockThreshold;

    public Medication(String medicineName, int stock, int lowStockThreshold) {
        this.medicineName = medicineName;
        this.stock = stock;
        this.lowStockThreshold = lowStockThreshold;
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

    public boolean getIsLowStock() {
        
        if(this.stock - getStockThreshold() <= 0){
            return true;
        }
        else return false;
    }
}
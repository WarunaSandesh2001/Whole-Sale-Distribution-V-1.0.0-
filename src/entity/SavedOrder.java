package entity;

public class SavedOrder {
    private String oId;
    private String custNIC;
    private String itemCode;
    private String itemDescription;
    private int quantity;
    private double discount;
    private double total;

    public SavedOrder(String oId, String custNIC, String itemCode, String itemDescription, int quantity, double discount, double total) {
        this.oId = oId;
        this.custNIC = custNIC;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.discount = discount;
        this.total = total;
    }

    public SavedOrder() {
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getCustNIC() {
        return custNIC;
    }

    public void setCustNIC(String custNIC) {
        this.custNIC = custNIC;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SavedOrder{" +
                "oId='" + oId + '\'' +
                ", custNIC='" + custNIC + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}

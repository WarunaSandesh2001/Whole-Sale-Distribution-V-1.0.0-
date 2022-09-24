package entity;

public class Order_Detail {

    private String oID;
    private String iCode;
    private int orderQTY;
    private double discount;
    private double price;

    public Order_Detail(String oID, String iCode, int orderQTY, double discount, double price) {
        this.oID = oID;
        this.iCode = iCode;
        this.orderQTY = orderQTY;
        this.discount = discount;
        this.price = price;
    }

    public Order_Detail() {
    }

    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order_Detail{" +
                "oID='" + oID + '\'' +
                ", iCode='" + iCode + '\'' +
                ", orderQTY=" + orderQTY +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }
}

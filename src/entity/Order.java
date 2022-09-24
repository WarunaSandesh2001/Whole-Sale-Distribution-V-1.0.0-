package entity;

import java.time.LocalDate;

public class Order {
    private String orderID;
    private LocalDate orderDate;
    private String orderTime ;
    private String cID ;
    private double totalDiscount ;
    private double totalPrice ;

    public Order(String orderID, LocalDate orderDate, String orderTime, String cID, double totalDiscount, double totalPrice) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cID = cID;
        this.totalDiscount = totalDiscount;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate=" + orderDate +
                ", orderTime='" + orderTime + '\'' +
                ", cID='" + cID + '\'' +
                ", totalDiscount=" + totalDiscount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import db.DbConnection;
import javafx.collections.ObservableList;
import view.tm.SavedOrderDetailsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO{

    @Override
    public void saveOrderToOrderDetailTable(String orderID , ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {
        try {
            for (SavedOrderDetailsTM o : obList) {
                CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?,?)",orderID,o.getItemCode(),o.getQtyForSell(),o.getDiscount(),o.getTotal());
            }

        }catch (Exception e){}
    }
}

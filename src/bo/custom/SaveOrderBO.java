package bo.custom;

import dto.CustomerDTO;
import dto.ItemDTO;
import bo.SuperBO;
import view.tm.ItemDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SaveOrderBO extends SuperBO {
    List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException;
    ItemDTO search(String descript) throws SQLException, ClassNotFoundException;
    CustomerDTO getCustomer(String nic) throws SQLException, ClassNotFoundException;
    boolean update(CustomerDTO c) throws SQLException, ClassNotFoundException;
    ItemDTO getItem(String code) throws SQLException, ClassNotFoundException;
    boolean saveOrder(String orderId, String custNIC, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException;
    void saveIDTOTempTable(String orderID) throws SQLException, ClassNotFoundException;
    String setCustomerIDS() throws SQLException, ClassNotFoundException;
    String setOrderIDS() throws SQLException, ClassNotFoundException;
    boolean add(CustomerDTO c) throws SQLException, ClassNotFoundException;
}

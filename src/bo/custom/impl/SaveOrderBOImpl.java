package bo.custom.impl;

import dto.CustomerDTO;
import dto.ItemDTO;
import bo.custom.SaveOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import entity.Customer;
import entity.Item;
import view.tm.ItemDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaveOrderBOImpl implements SaveOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    private final SavedOrderDAO savedOrderDAO = (SavedOrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SAVEDORDER);
    private final TempOrderIDDAO tempOrderID = (TempOrderIDDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEMPORDERID);


    @Override
    public List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItemDescriptions();
    }

    @Override
    public ItemDTO search(String descript) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(descript);
        return new ItemDTO(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand(),item.getDiscount());
    }

    @Override
    public CustomerDTO getCustomer(String nic) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.getCustomer(nic);
        return new CustomerDTO(customer.getCustID(),customer.getCustTitle(),customer.getCustName(),customer.getCustAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode(),customer.getNationalID());
    }

    @Override
    public boolean update(CustomerDTO c) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(c.getCustomerID(),c.getCustomerTitle(),c.getCustomerName(),c.getCustomerAddress(),c.getCity(),c.getProvince(),c.getPostalCode(),c.getNationalID()));
    }

    @Override
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.getItem(code);
    }

    @Override
    public boolean saveOrder(String orderId, String custNIC, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        return savedOrderDAO.saveOrder(orderId,custNIC,items);
    }

    @Override
    public void saveIDTOTempTable(String orderID) throws SQLException, ClassNotFoundException {
        tempOrderID.saveIDTOTempTable(orderID);
    }

    @Override
    public String setCustomerIDS() throws SQLException, ClassNotFoundException {
        return customerDAO.setCustomerIDS();
    }

    @Override
    public String setOrderIDS() throws SQLException, ClassNotFoundException {
        return tempOrderID.setOrderIDS();
    }

    @Override
    public boolean add(CustomerDTO c) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(c.getCustomerID(),c.getCustomerTitle(),c.getCustomerName(),c.getCustomerAddress(),c.getCity(),c.getProvince(),c.getPostalCode(),c.getNationalID()));
    }
}

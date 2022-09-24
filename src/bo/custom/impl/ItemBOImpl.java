package bo.custom.impl;

import dto.ItemDTO;
import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item i : all) {
            allItems.add(new ItemDTO(i.getItemCode(),i.getDescription(),i.getPackSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getDiscount()));
        }
        return allItems;
    }

    @Override
    public boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getPackSize(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getDiscount()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getPackSize(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getDiscount()));
    }

    @Override
    public boolean ifItemExist(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExist(id);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return itemDAO.setItemIDS();
    }
}

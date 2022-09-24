package dao.custom.impl;

import dto.ItemDTO;
import dao.CrudUtil;
import dao.custom.ItemDAO;
import db.DbConnection;
import entity.Item;
import javafx.collections.ObservableList;
import view.tm.SavedOrderDetailsTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?)",i.getItemCode(),i.getDescription(),i.getPackSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getDiscount());
    }

    @Override
    public List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        List<String> descriptions= new ArrayList<>();
        while (rst.next()){
            descriptions.add(
                    rst.getString(2)
            );
        }
        return descriptions;
    }

    @Override
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE itemCode='" + code + "'");
        if (rst.next()){
            return new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            );
        }else{
            return null;
        }
    }

    @Override
    public String setItemIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT itemCode FROM Item ORDER BY itemCode DESC LIMIT 1");
        if (rst.next()){
            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "I-00"+tempId;
            }else if(tempId<=99){
                return "I-0"+tempId;
            }else{
                return "I-"+tempId;
            }

        }else{
            return "I-001";
        }
    }

    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET itemCode=?, description=?, packSize=?, unitPrice=?, qtyOnHand=?, discount=?  WHERE itemCode='"+i.getItemCode()+"'",i.getItemCode(),i.getDescription(),i.getPackSize(),i.getUnitPrice(),i.getQtyOnHand(),i.getDiscount());
    }

    @Override
    public boolean delete(String iCode) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE itemCode='"+iCode+"'");
    }

    @Override
    public Item search(String descript) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE description='" + descript + "'");
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            );
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            ));
        }
        return items;
    }

    @Override
    public void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Item` SET qtyOnHand=?  WHERE itemCode=?");
        for(SavedOrderDetailsTM s : obList) {
            ItemDTO i =new ItemDAOImpl().getItem(s.getItemCode());
            int qty = i.getQtyOnHand() - s.getQtyForSell();
            stm.setObject(1,qty);
            stm.setObject(2,s.getItemCode());

            stm.executeUpdate();
        }

    }

    @Override
    public String getItemDescriptionToLable(String iCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT description FROM Item WHERE itemCode='"+iCode+"'");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean ifItemExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT itemCode FROM Item WHERE itemCode=?", id).next();
    }
}

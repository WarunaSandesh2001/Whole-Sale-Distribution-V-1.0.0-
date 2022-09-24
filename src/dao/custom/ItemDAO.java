package dao.custom;

import dto.ItemDTO;
import dao.CrudDAO;
import entity.Item;
import javafx.collections.ObservableList;
import view.tm.SavedOrderDetailsTM;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item, String> {
    List<String> getAllItemDescriptions() throws SQLException, ClassNotFoundException;

    ItemDTO getItem(String code) throws SQLException, ClassNotFoundException;

    String setItemIDS() throws SQLException, ClassNotFoundException;

    void updateItemTable(ObservableList<SavedOrderDetailsTM> obList) throws SQLException, ClassNotFoundException ;

    String getItemDescriptionToLable(String iCode) throws SQLException, ClassNotFoundException ;

    boolean ifItemExist(String id) throws SQLException, ClassNotFoundException;

}

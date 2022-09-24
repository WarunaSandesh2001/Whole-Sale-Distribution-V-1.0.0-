package dao.custom.impl;

import dto.UserDTO;
import dao.CrudUtil;
import dao.custom.UserDAO;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean setInfo(UserDTO u) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO LoginDetail VALUES(?,?,?,?,?,?)",u.getFirstName(),u.getLastName(),u.getUserType(),u.getEmail(),u.getUserName(),u.getPassword());
    }

    @Override
    public ArrayList<String> getUserTypes() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM LoginDetail");
        ArrayList<String> users = new ArrayList<>();
        while (rst.next()) {
            users.add(rst.getString(3));
        }
        return users;
    }

    @Override
    public String getUserInfo(String type, String txtUserName,String txtPassword) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `LoginDetail` WHERE userType='"+type+"'");
        while (rst.next()){
            if(rst.getString(5).equals(txtUserName) && rst.getString(6).equals(txtPassword)){
                return rst.getString(1);
            }
        }
        return null;
    }
}

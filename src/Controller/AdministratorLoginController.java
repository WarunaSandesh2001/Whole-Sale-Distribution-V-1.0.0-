package Controller;

import dao.custom.impl.UserDAOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministratorLoginController {
    public AnchorPane AdministratorContext;
    public Button btnAdministrator;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Label lblNotAAdmin;
    public JFXButton btnSignUP;
    Stage stage = null;

    static String firstNameOfAdmin;

    public void initialize() throws SQLException, ClassNotFoundException {
        btnAdministrator.setDisable(true);
        ArrayList<String> userTypes = new UserDAOImpl().getUserTypes();
        for(String user : userTypes){
            if(user.equals("Admin")){
                btnSignUP.setVisible(false);
                btnSignUP.setDisable(true);
                lblNotAAdmin.setVisible(false);
            }
        }
    }

    public void goToCashierLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        AdministratorContext.getChildren().clear();
        AdministratorContext.getChildren().add(load);
    }

    public void goToRegisterFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegisterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        AdministratorContext.getChildren().clear();
        AdministratorContext.getChildren().add(load);
    }

    public void loginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String include = new UserDAOImpl().getUserInfo("Admin",txtUserName.getText(),txtPassword.getText());
        if(include!=null) {
            URL resource = getClass().getResource("../view/AdminDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            AdministratorContext.getChildren().clear();
            AdministratorContext.getChildren().add(load);
        }else {
            new Alert(Alert.AlertType.WARNING, "User Name or Password is incorrect..please Try again..!").show();
        }
    }

    public void goToPasswordTXT(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void goToLoginBTN(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        loginOnAction(actionEvent);
    }

    public void closeTheProgramOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void closeTheProgramOnAction1(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void minimizeOnAction2(MouseEvent mouseEvent) {
        stage = (Stage) AdministratorContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void minimizeOnAction1(MouseEvent mouseEvent) {
        stage = (Stage) AdministratorContext.getScene().getWindow();
        stage.setIconified(true);
    }
}

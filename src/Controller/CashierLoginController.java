package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.UserDAOImpl;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierLoginController {
    public AnchorPane cashierContext;
    public Button btnCashier;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    static String firstNameOfCashier;
    Stage  stage = null;

    public void initialize(){
        btnCashier.setDisable(true);
    }

    public void goToAdminstratorLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AdministratorLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierContext.getChildren().clear();
        cashierContext.getChildren().add(load);
    }

    public void goToRegisterFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegisterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierContext.getChildren().clear();
        cashierContext.getChildren().add(load);
    }

    public void closeTheProgramOnAction1(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void closeTheProgramOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String include = new UserDAOImpl().getUserInfo("Cashier",txtUserName.getText(),txtPassword.getText());
        if(include!=null) {
            URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            cashierContext.getChildren().clear();
            cashierContext.getChildren().add(load);
        }else {
            new Alert(Alert.AlertType.WARNING, "User Name or Password is incorrect..please Try again..!").show();
        }
    }

    public void goToPasswordTXT(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void goToLoginBTN(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        loginOnAction(actionEvent);
    }

    public void minimizeOnAction2(MouseEvent mouseEvent) {
        stage = (Stage) cashierContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void minimizeOnAction1(MouseEvent mouseEvent) {
        stage = (Stage) cashierContext.getScene().getWindow();
        stage.setIconified(true);
    }
}

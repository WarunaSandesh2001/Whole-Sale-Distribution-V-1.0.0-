package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public AnchorPane dashBoardContext;
    Stage stage = null;

    public void goToCashierLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(load);
    }

    public void goToAdministratorLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AdministratorLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(load);
    }

    public void closeTheProgramOnAction1(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void closeTheProgramOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void minimizeOnAction1(MouseEvent mouseEvent) {
        stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void minimizeOnAction2(MouseEvent mouseEvent) {
        stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setIconified(true);
    }
}

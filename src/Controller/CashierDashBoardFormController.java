package Controller;

import bo.BoFactory;
import bo.custom.SaveOrderBO;
import dao.custom.impl.*;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.ValidationUtil;
import view.tm.Carttm;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.ItemDetails;
import view.tm.ItemDetailtm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import static Controller.CashierLoginController.firstNameOfCashier;

public class CashierDashBoardFormController {
    public AnchorPane cashierDashBoardContext;
    public JFXTextField txtSearchCustomerID;
    public Label lblCustomerID;
    public TextField txtCustomerAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtPostalCode;
    public TextField txtCustomerTitle;
    public TextField txtNationalID;
    public TextField txtCustomerName;
    public Button btnUpdate;
    public Button btnAdd;
    public Label lblCashierName;
    public ComboBox<String> cmbItemDescription;
    public TableView tblItemDetail;
    public TableColumn colItemCode1;
    public TableColumn colDescription1;
    public TableColumn colPackSize1;
    public TableColumn colUnitPrice1;
    public TableColumn colQTYOnHand;
    public TextField txtQTYForSell;
    public TableView tblListOfItem;
    public TableColumn colItemCode2;
    public TableColumn colItemDescription;
    public TableColumn colOrderQuantity;
    public TableColumn colDiscount;
    public TableColumn colPrice;
    public Button btnAddToCart;
    public TextField txtUpdateOrderQTY;
    public Label lblGrossAmount;
    public Label lblTotalDiscount;
    public Label lblTotalNetAmount;
    public Label lblOrderID;
    public TextField txtDate;
    public TextField txtTime;

    private final SaveOrderBO saveOrderBO = (SaveOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.SAVE_ORDER);

    Stage stage = null;
    ObservableList<Carttm> obList = FXCollections.observableArrayList();
    int cartSelectedRow = -1;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern customerNamePattern = Pattern.compile("^[A-z.[ ]]{2,}$");
    Pattern addressPattern = Pattern.compile("^[#./0-9a-zA-Z,-[ ]]{5,}$");
    Pattern postalCodePattern = Pattern.compile("^[A-z[ ]0-9[-]]$");
    Pattern NationalIDPattern = Pattern.compile("^[0-9]{12}|[0-9]{9}[V,v]$");
    Pattern cityPattern = Pattern.compile("^[A-z.[ ]]{2,}$");
    Pattern provincePattern = Pattern.compile("^[A-z.[ ]]{2,}$");
    Pattern titlePattern = Pattern.compile("^[A-z.[ ]]{2,}$");

    public void initialize() throws SQLException, ClassNotFoundException {
        btnAddToCart.setDisable(true);
        txtQTYForSell.setEditable(false);
        btnUpdate.setDisable(true);
        btnAdd.setDisable(true);
        lblCustomerID.setText(saveOrderBO.setCustomerIDS());
        lblOrderID.setText(saveOrderBO.setOrderIDS());
        lblCashierName.setText(firstNameOfCashier);
        loadDateAndTime();
        List temp = saveOrderBO.getAllItemDescriptions();
        cmbItemDescription.getItems().addAll(temp);
        cmbItemDescription.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ItemDTO item = saveOrderBO.search(newValue);

                ItemDetailtm i = new ItemDetailtm(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand());
                ArrayList<ItemDetailtm> temp1 = new ArrayList<>();
                temp1.add(i);
                colItemCode1.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
                colDescription1.setCellValueFactory(new PropertyValueFactory<>("description"));
                colPackSize1.setCellValueFactory(new PropertyValueFactory<>("packSize"));
                colUnitPrice1.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
                colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

                tblItemDetail.setItems(FXCollections.observableArrayList(temp1));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            txtQTYForSell.requestFocus();

        });

        tblListOfItem.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRow = (int) newValue;
        });

        storeValidations1();
    }

    private void storeValidations1() {
        map.put(txtCustomerName, customerNamePattern);
        map.put(txtCustomerAddress, addressPattern);
        map.put(txtPostalCode, postalCodePattern);
        map.put(txtNationalID, NationalIDPattern);
        map.put(txtCity, cityPattern);
        map.put(txtProvince, provincePattern);
        map.put(txtCustomerTitle, titlePattern);
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void closeTheProgramOnAction1(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void closeTheProgramOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void SearchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String nic = txtSearchCustomerID.getText();
        CustomerDTO c1= saveOrderBO.getCustomer(nic);
        if (c1==null) {
            refreshOnAction(actionEvent);
            new Alert(Alert.AlertType.WARNING, "No Customer for this ID...").show();
        } else {
            btnAddToCart.setDisable(false);
            txtQTYForSell.setEditable(true);
            btnUpdate.setDisable(false);
            btnAdd.setDisable(true);
            txtCustomerName.setText(c1.getCustomerName());
            txtCustomerAddress.setText(c1.getCustomerAddress());
            txtPostalCode.setText(c1.getPostalCode());
            txtNationalID.setText(c1.getNationalID());
            txtCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtCustomerTitle.setText(c1.getCustomerTitle());
            lblCustomerID.setText(c1.getCustomerID());
        }
    }

    public void customerAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if(!txtCustomerName.getText().equals("") && !txtCustomerAddress.getText().equals("") && !txtCustomerTitle.getText().equals("") && !lblCustomerID.getText().equals("") && !txtCity.getText().equals("") && !txtProvince.getText().equals("") && !txtPostalCode.getText().equals("") && !txtNationalID.getText().equals("")) {
            CustomerDTO c1 = new CustomerDTO(
                    lblCustomerID.getText(), txtCustomerTitle.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText(),
                    txtNationalID.getText()
            );
            if (saveOrderBO.add(c1)) {
                btnAdd.setDisable(true);
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").showAndWait();
                refreshOnAction(actionEvent);
            }
            else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please fill all fields and try again..").show();
        }
    }

    public void customerUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if(!txtCustomerName.getText().equals("") && !txtCustomerAddress.getText().equals("") && !txtCustomerTitle.getText().equals("") && !lblCustomerID.getText().equals("") && !txtCity.getText().equals("") && !txtProvince.getText().equals("") && !txtPostalCode.getText().equals("") && !txtNationalID.getText().equals("")) {
            CustomerDTO c1 = new CustomerDTO(
                    lblCustomerID.getText(), txtCustomerTitle.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText(),
                    txtNationalID.getText()
            );
            if (saveOrderBO.update(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").showAndWait();
                refreshOnAction(actionEvent);
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "Please fill all fields and try again..").show();
        }
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierDashBoardContext.getChildren().clear();
        cashierDashBoardContext.getChildren().add(load);
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierDashBoardContext.getChildren().clear();
        cashierDashBoardContext.getChildren().add(load);
    }

    public void goToBTNAddToCart(ActionEvent actionEvent) {
        addToCartOnAction(actionEvent);
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        if (!txtQTYForSell.getText().equals("") ) {
            try {
                ItemDTO item = saveOrderBO.search(cmbItemDescription.getSelectionModel().getSelectedItem());
                if (obList.isEmpty()) {
                    double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQTYForSell.getText());
                    double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQTYForSell.getText()));

                    Carttm carttm = new Carttm(item.getItemCode(), item.getDescription(), Integer.parseInt(txtQTYForSell.getText()), totDiscount, price);

                    if (item.getQtyOnHand() < carttm.getOrderQuantity()) {
                        new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                        txtQTYForSell.clear();
                    } else {
                        obList.add(carttm);
                        calculateCost();
                        txtQTYForSell.clear();
                    }

                    colItemCode2.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
                    colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
                    colOrderQuantity.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
                    colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
                    colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

                    tblListOfItem.setItems(FXCollections.observableArrayList(obList));
                    tblItemDetail.refresh();

                } else {
                    try {
                        for (Carttm c : obList) {
                            if (c.getItemCode().equals(item.getItemCode())) {
                                double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQTYForSell.getText());
                                double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQTYForSell.getText()));

                                if (item.getQtyOnHand() < c.getOrderQuantity() +(Integer.parseInt(txtQTYForSell.getText()))) {
                                    new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                                    txtQTYForSell.clear();
                                } else {
                                    c.setOrderQuantity(c.getOrderQuantity() + Integer.parseInt(txtQTYForSell.getText()));
                                    c.setDiscount(c.getDiscount() + totDiscount);
                                    c.setPrice(c.getPrice() + price);
                                    calculateCost();
                                    tblListOfItem.refresh();
                                    txtQTYForSell.clear();
                                }
                                return;
                            }
                        }
                        double totDiscount = Integer.parseInt(item.getPackSize()) * (item.getDiscount()) * Integer.parseInt(txtQTYForSell.getText());
                        double price = item.getUnitPrice() * Integer.parseInt(item.getPackSize()) * (Integer.parseInt(txtQTYForSell.getText()));

                        Carttm carttm = new Carttm(item.getItemCode(), item.getDescription(), Integer.parseInt(txtQTYForSell.getText()), totDiscount, price);
                        if (item.getQtyOnHand() < carttm.getOrderQuantity()) {
                            new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                            txtQTYForSell.clear();
                        } else {
                            calculateCost();
                            obList.add(carttm);
                            txtQTYForSell.clear();
                        }
                        colItemCode2.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
                        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
                        colOrderQuantity.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
                        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
                        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

                        tblListOfItem.setItems(FXCollections.observableArrayList(obList));

                    } catch (Exception e) {
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            calculateCost();
        }else{
            new Alert(Alert.AlertType.WARNING, "Please enter order Quantity and try again..!").show();
        }
    }

    public void deleteItemQTYOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRow);
            calculateCost();

            colItemCode2.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
            colOrderQuantity.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
            colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            tblListOfItem.setItems(FXCollections.observableArrayList(obList));
        }
    }

    public void UpdateItemQTYOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            Carttm c = obList.get(cartSelectedRow);

            ItemDTO i = saveOrderBO.getItem(c.getItemCode());
            if(Integer.parseInt(txtUpdateOrderQTY.getText()) > i.getQtyOnHand()){
                new Alert(Alert.AlertType.WARNING, "Order quantity is out of stock...!").show();
                txtUpdateOrderQTY.clear();
            }else{
                c.setOrderQuantity(Integer.parseInt(txtUpdateOrderQTY.getText()));
                double tot = (Integer.parseInt(txtUpdateOrderQTY.getText()))* i.getUnitPrice() * Integer.parseInt(i.getPackSize());
                c.setPrice(tot);
                double discount = (Integer.parseInt(txtUpdateOrderQTY.getText()))* i.getDiscount() * Integer.parseInt(i.getPackSize());
                c.setDiscount(discount);
                obList.remove(cartSelectedRow);
                obList.add(cartSelectedRow,c);
                calculateCost();
                tblListOfItem.refresh();
                txtUpdateOrderQTY.clear();
            }
        }
    }
    void calculateCost(){
        double ttl=0;
        double dis=0.0;
        double netTot = 0.0;
        for (Carttm tm:obList
        ) {
            ttl+=tm.getPrice();
            dis+=tm.getDiscount();
            netTot+=ttl-dis;
        }
        lblGrossAmount.setText(ttl+" /=");
        lblTotalDiscount.setText(dis+" /=");
        lblTotalNetAmount.setText(netTot+" /=");
    }

    public void goToSavedFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderSavedForm.fxml");
        Parent load = FXMLLoader.load(resource);
        cashierDashBoardContext.getChildren().clear();
        cashierDashBoardContext.getChildren().add(load);
    }

    public void cancelOrderOnAction(ActionEvent actionEvent) throws IOException {
        refreshOnAction(actionEvent);
    }

    public void saveOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<ItemDetails> items = new ArrayList<>();
        for (Carttm c : obList) {
            items.add(new ItemDetails(
                    c.getItemCode(),
                    c.getItemDescription(),
                    c.getOrderQuantity(),
                    c.getDiscount(),
                    c.getPrice()
            ));
        }
        OrderDTO order = new OrderDTO(lblOrderID.getText(),txtNationalID.getText(),items);
        if(new SavedOrderDAOImpl().saveOrder(order.getOrderId(),order.getCustNIC(),order.getItems())){
            saveOrderBO.saveIDTOTempTable(lblOrderID.getText());
            new Alert(Alert.AlertType.CONFIRMATION, "Order Saved Successfully...!").show();
            refreshOnAction(actionEvent);
        }else{
            new Alert(Alert.AlertType.WARNING, "Try again!").show();
        }
    }

    public void minimizeOnAction1(MouseEvent mouseEvent) {
        stage = (Stage) cashierDashBoardContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void minimizeOnAction2(MouseEvent mouseEvent) {
        stage = (Stage) cashierDashBoardContext.getScene().getWindow();
        stage.setIconified(true);
    }

    public void textFieldsForAdaANDUpdate_key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateForNormalTextFields2(map,btnAdd,btnUpdate);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {}
        }
    }
}

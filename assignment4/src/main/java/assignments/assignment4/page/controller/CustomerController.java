package assignments.assignment4.page.controller;

import assignments.assignment3.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment3.Order;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.CustomerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class CustomerController {
    public MainApp mainApp;
    public Stage stage;
    public CustomerMenu customerMenu;
    
    public Label labelUser;
    public Label saldo;

    public ObservableList<String> restoList = FXCollections.observableArrayList(DepeFood.getRestoNameList());
    public ObservableList<String> orders = FXCollections.observableArrayList(DepeFood.getUserLoggedIn().getOrderString());
    public ObservableList<String> notFinishedOrder = FXCollections.observableArrayList(DepeFood.getUserLoggedIn().getNotFinishedOrderString());

    public Button back;

    public Button logoutButton;
    public Button buttonBuatPesanan;
    public Button buttonCetakBill;
    public Button buttonBayarBill;
    public Button buttonCekSaldo;

    public ComboBox<String> comboBoxResto = new ComboBox<>();
    public ListView<String> listMenu;
    public Label labelHarga;
    public Button pesan;
    public DatePicker tanggal;
    public String tanggalPesan;

    public ListView<String> listOrder = new ListView<>();
    public Button cetakBill;
    public Label labelBill;
    public Rectangle rectangleCetakBill;

    public ListView<String> listOrderNotFinished = new ListView<>();
    public Button payWithCreditCard;
    public Button payWithDebit;

    // Fungsi-fungsi berikut memungkinkan kelas ini berinteraksi dengan 
    // kelas CustomerMenu
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setCustomerMenu(CustomerMenu customerMenu){
        this.customerMenu = customerMenu;
    }
    
    // Hal yang perlu diinisiasi ketika launch scene
    public void initialize() {
        try {
            labelUser.setText("Selamat datang, "+ DepeFood.getUserLoggedIn().getNama());
        } catch (Exception e) {}
        
        comboBoxResto.setItems(restoList);
        listOrder.setItems(orders);
        listOrderNotFinished.setItems(notFinishedOrder);
    }

    // fungsi-fungsi berikut dipakai untuk membuat pesanan
    public void switchToBuatPesanan(){
        if (restoList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Resto masih kosong :(\nMohon tunggu update dari admin ya!");
            alert.showAndWait();
            return;
        }
        mainApp.setScene(customerMenu.getScene("page/customer/BuatPesanan.fxml"));
    }

    public void selectResto(){
        Restaurant resto = DepeFood.getRestaurantByName(comboBoxResto.getValue());
        ObservableList<String> menu = FXCollections.observableArrayList(resto.getMenuName());
        listMenu.setItems(menu);
        listMenu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listMenu.setStyle("-fx-background-color: transparent;");
    }

    public void pesan(){
        ObservableList<String> selectedItems = listMenu.getSelectionModel().getSelectedItems();
        if (selectedItems.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Mohon Pilih Menu!");
            alert.showAndWait();
            return;
        }
        if (tanggal.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Mohon Pilih Tanggal!");
            alert.showAndWait();
            return;
        }

        String date = tanggal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        DepeFood.handleBuatPesanan(comboBoxResto.getValue(),date, selectedItems.size(), selectedItems);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Pesanan Berhasil Dibuat!");
        alert.showAndWait();
        return;

    }
/////////////////////////////////////////////////

    // Fungsi-fungsi berikut digunakan untuk mencetak bill
    public void switchToCetakBill(){
        if (DepeFood.getUserLoggedIn().getOrderHistory().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Kamu belum memiliki order nih, buat dulu yuk!");
            alert.showAndWait();
            return;
        }
        mainApp.setScene(customerMenu.getScene("page/customer/CetakBill.fxml"));
    }

    public void cetakBill(){
        String orderID = listOrder.getSelectionModel().getSelectedItem();
        Order order = DepeFood.getOrderOrNull(orderID);
        if (order == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText("Mohon Pilih Order ID!");
            alert.showAndWait();
            return;
        }
        String bill = DepeFood.cetakBill(order);
        listOrder.setVisible(false);
        cetakBill.setVisible(false);
        rectangleCetakBill.setVisible(false);
        labelBill.setVisible(true);
        labelBill.setText(bill);
    }
/////////////////////////


    // Fungsi-fungsi berikut digunakan untuk membayar bill
    public void switchToBayarBill(){
        if (notFinishedOrder.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Kamu belum memiliki order yang belum dibayar nih, buat dulu yuk!");
            alert.showAndWait();
            return;
        }
        mainApp.setScene(customerMenu.getScene("page/customer/BayarBill.fxml"));
    }

    public void payWithCreditCard(){
        String orderID = listOrderNotFinished.getSelectionModel().getSelectedItem();
        Order order = DepeFood.getOrderOrNull(orderID);

        if (order == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText("Mohon Pilih Order ID!");
            alert.showAndWait();
            return;
        }

        if (!DepeFood.getUserLoggedIn().getPaymentSystem().getClass().getSimpleName().equals("CreditCardPayment")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText("Anda Tidak Memiliki Metode Pembayaran Ini!");
            alert.showAndWait();
            return;
        }

        DepeFood.handleBayarBill(orderID, "Credit Card");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String berhasil = String.format("Berhasil Bembayar Bill sebesar Rp%.0f dengan biaya transaksi sebesar Rp%.0f", order.getTotalHarga(), order.getTotalHarga()*0.02);

        notFinishedOrder = FXCollections.observableArrayList(DepeFood.getUserLoggedIn().getNotFinishedOrderString());
        listOrderNotFinished.setItems(notFinishedOrder);

        alert.setTitle("DepeFood");
        alert.setHeaderText(berhasil);
        alert.showAndWait();
    }

    public void payWithDebit(){
        String orderID = listOrderNotFinished.getSelectionModel().getSelectedItem();
        Order order = DepeFood.getOrderOrNull(orderID);
        if (order == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText("Mohon Pilih Order ID!");
            alert.showAndWait();
            return;
        }

        if (DepeFood.getUserLoggedIn().getPaymentSystem().getClass().getSimpleName().equals("CreditCardPayment")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText("Anda Tidak Memiliki Metode Pembayaran Ini!");
            alert.showAndWait();
            return;
        }

        if (order.getTotalHarga() < 50_000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText("Total Harga Kurang dari 50.000 \nSilahkan Menggunakan Metode Pembayaran Lain!");
            alert.showAndWait();
            return;
        }

        DepeFood.handleBayarBill(orderID, "Debit");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String berhasil = String.format("Berhasil Bembayar Bill sebesar Rp%.0f.", order.getTotalHarga());

        alert.setTitle("DepeFood");
        alert.setHeaderText(berhasil);
        alert.showAndWait();

        notFinishedOrder = FXCollections.observableArrayList(DepeFood.getUserLoggedIn().getNotFinishedOrderString());
        listOrderNotFinished.setItems(notFinishedOrder);
    }
//////////////////

    public void back(){
        mainApp.setScene(customerMenu.getScene("page/CustomerMenu.fxml"));
    }

    public void logout(){
        mainApp.logout();
    }

    public void showSaldo(){
        saldo.setText("Saldo : Rp" + DepeFood.getUserLoggedIn().getSaldo());
    }

    public void hideSaldo(){
        saldo.setText("Saldo : ------");
    }


}

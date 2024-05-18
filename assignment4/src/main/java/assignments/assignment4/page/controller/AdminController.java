package assignments.assignment4.page.controller;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import assignments.assignment3.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.net.URL;

public class AdminController {
    public MainApp mainApp;
    public Stage stage;
    public Button logout;
    public Button tambahRestoran;
    public Button tambahMenu;
    public Button lihatDaftar;
    public Button buatResto;
    public TextField buatNamaResto;
    public Label daftarMenuMakanan;
    public AdminMenu adminMenu;
    public Button back;


    public ObservableList<String> restoList = FXCollections.observableArrayList(DepeFood.getRestoNameList());
    public ComboBox<String> comboBox = new ComboBox<>();
    public TextField namaMakanan;
    public TextField hargaMakanan;
    public Button tambah;


    public void initialize() {
        comboBox.setItems(restoList);
}
    public void switchToAddResto(){
        mainApp.setScene(adminMenu.getScene("page/admin/TambahRestorant.fxml"));
    }   

    public void addResto(){
        Alert alert;
        String namaResto = buatNamaResto.getText();
        if (DepeFood.findRestaurant(namaResto) == null && namaResto.length() >= 4) {
            DepeFood.handleTambahRestoran(namaResto);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DepeFood");
            alert.setHeaderText(namaResto + " berhasil dibuat!");
            alert.showAndWait();
        } 
        else if (namaResto.length() < 4) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText("Mohon Masukkan Panjang Nama Resto Lebih dari 4 Huruf!");
            alert.showAndWait();
        } else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DepeFood");
            alert.setHeaderText(namaResto + " sudah pernah dibuat!");
            alert.showAndWait();
        }
    }

    public void switchToAddMenu(){
        mainApp.setScene(adminMenu.getScene("page/admin/TambahMenu.fxml"));
    }
    
    public void addMenu(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DepeFood");

        try {
            String namaResto = comboBox.getValue();
            String makanan = namaMakanan.getText();
            double harga = Double.parseDouble(hargaMakanan.getText());
            Restaurant restaurant = DepeFood.getRestaurantByName(namaResto);

            if (harga < 0 ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Mohon Masukkan Harga Berupa Bilangan Positif!");
                alert.showAndWait();
                return;
            }

            if (restaurant.menuExist(makanan)) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Menu Sudah Ada!");
                alert.showAndWait();
                return;
            }


            DepeFood.handleTambahMenuRestoran(restaurant, makanan, harga);
            alert.setHeaderText("Menu Berhasil Ditambahkan!");
            alert.showAndWait();

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Mohon Masukkan Harga Berupa Angka!");
            alert.showAndWait();
        }

    }

    public void switchToLihatMenu(){
        mainApp.setScene(adminMenu.getScene("page/admin/LihatMenu.fxml"));
    }

    public void quitProgram(){
        mainApp.logout();
    }

    public void back(){
        mainApp.setScene(adminMenu.getScene("page/AdminMenu.fxml"));
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setAdminMenu(AdminMenu adminMenu){
        this.adminMenu = adminMenu;
    }

    public void cetakMenu(){
        Restaurant resto = DepeFood.getRestaurantByName(comboBox.getValue());
        daftarMenuMakanan.setText(resto.printMenu());
    }

}

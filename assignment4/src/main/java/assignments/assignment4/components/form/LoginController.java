package assignments.assignment4.components.form;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LoginController{
    public MainApp mainApp;
    public Stage stage;
    public Button login;
    public TextField uname;
    public PasswordField notelpon;
    public Rectangle usernameBox;
    public Rectangle boxTelp;
    public Label wrongPassword;

    public void quitProgram(){

        String username = uname.getText();
        String noTelp = notelpon.getText();

        User userLoggedIn = DepeFood.getUser(username, noTelp);

        if (userLoggedIn == null) {
            usernameBox.setStyle("-fx-fill: rgba(255, 0, 0, 0.09);-fx-arc-height: 50; -fx-arc-width: 25;");
            boxTelp.setStyle("-fx-fill: rgba(255, 0, 0, 0.09);-fx-arc-height: 50; -fx-arc-width: 25;");

            // Validasi
            if (username.isEmpty()){
                wrongPassword.setText("Mohon isi Username");
            } else if (noTelp.isEmpty()) {
                wrongPassword.setText("Mohon isi Nomor Telpon");
            } else if (username.isEmpty() && noTelp.isEmpty()) {
                wrongPassword.setText("Mohon isi Username dan Nomor Telpon");
            } else {
                wrongPassword.setText("Password atau Nomor Telpon Salah!");
            }
            wrongPassword.setStyle("-fx-text-fill: red;");
            return;
        } 

        // Membersihkan isi textbox dan merubah warnanya kembali jika sempat terjadi error
        uname.clear();
        notelpon.clear();
        usernameBox.setStyle("-fx-fill: transparent;-fx-arc-height: 50; -fx-arc-width: 25;");
        boxTelp.setStyle("-fx-fill: transparent;-fx-arc-height: 50; -fx-arc-width: 25;");
        wrongPassword.setText("");
        wrongPassword.setStyle("-fx-text-fill: transparent;");

        MainApp.setUser(userLoggedIn);
        DepeFood.handleLogin(username, noTelp);
        
        if (MainApp.getUser().getRole().equals("Admin")) {
            AdminMenu adminMenu = new AdminMenu(stage, mainApp, userLoggedIn);
            mainApp.setScene(adminMenu.getScene("page/AdminMenu.fxml"));
        } else{
            CustomerMenu customerMenu = new CustomerMenu(stage, mainApp, userLoggedIn);
            mainApp.setScene(customerMenu.getScene("page/CustomerMenu.fxml"));
        }

    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}

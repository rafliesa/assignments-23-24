package assignments.assignment4.components.form;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
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

    // Event handler ketika user menekan tombol login
    public void login(){

        // mengambil username dan no telpon user dari textfieldnya masing-masing
        String username = uname.getText();
        String noTelp = notelpon.getText();

        // Mengambil Objek user yang sesuai dengan informasi login tersebut jika ada
        // jika tidak sesuai maka objek berikut akan bernilai null
        User userLoggedIn = DepeFood.getUser(username, noTelp);

        // kondisi ketika username dan no telpon salah, objek userLoggedIn bernilai null
        if (userLoggedIn == null) {

            // mengatur gaya box username dan no telpon
            usernameBox.setStyle("-fx-fill: rgba(255, 0, 0, 0.09);-fx-arc-height: 50; -fx-arc-width: 25;");
            boxTelp.setStyle("-fx-fill: rgba(255, 0, 0, 0.09);-fx-arc-height: 50; -fx-arc-width: 25;");

            // validasi
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

        // kita set user sesuai dengan objek userLoggedIn
        MainApp.setUser(userLoggedIn);
        // Melakukan login pada kelas DepeFood yang ada di tp3
        DepeFood.handleLogin(username, noTelp);
        
        // Percabangan sesuai dengan role masing-masing user
        // akan pergi ke scene atau menunya masing-masing
        if (MainApp.getUser().getRole().equals("Admin")) {
            AdminMenu adminMenu = new AdminMenu(stage, mainApp, userLoggedIn);
            mainApp.setScene(adminMenu.getScene("page/AdminMenu.fxml"));
        } else{
            CustomerMenu customerMenu = new CustomerMenu(stage, mainApp, userLoggedIn);
            mainApp.setScene(customerMenu.getScene("page/CustomerMenu.fxml"));
        }

    }

    // kedua fungsi ini berfungsi untuk memberikan objek mainApp dan stage dari kelas
    // loginForm
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

}

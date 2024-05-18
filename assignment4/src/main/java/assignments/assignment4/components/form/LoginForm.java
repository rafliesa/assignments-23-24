package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import java.net.URL;
import java.lang.ClassLoader;

import java.util.function.Consumer;

import javax.sound.midi.ControllerEventListener;

public class LoginForm {
    private Stage stage;
    private MainApp mainApp; // MainApp instance
    

    public LoginForm(Stage stage, MainApp mainApp) { // Pass MainApp instance to constructor
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
    }

    private Scene createLoginForm() {
        try {
            ClassLoader classloader = getClass().getClassLoader();
            URL url = classloader.getResource("components/LoginForm.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            LoginController controller = loader.<LoginController>getController();
            controller.setMainApp(this.mainApp);
            controller.setStage(this.stage);
            stage.setScene(scene);
            stage.show();
            
            return scene;

        } catch (Exception e) {
            System.out.println("gAADA");
            return null;
        }
    }

    public Scene getScene(){
        return this.createLoginForm();
    }

    public void adminMenu(){
        User userLoggedIn = mainApp.getUser();
        AdminMenu adminMenu = new AdminMenu(this.stage, this.mainApp, userLoggedIn);
        
    }
}
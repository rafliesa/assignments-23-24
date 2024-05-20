package assignments.assignment4.page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import assignments.assignment3.DepeFood;
import assignments.assignment3.Menu;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.BillPrinter;
import assignments.assignment4.page.controller.AdminController;
import assignments.assignment4.page.controller.CustomerController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMenu extends MemberMenu{
    private Stage stage;
    private MainApp mainApp;
    private User user;


    public CustomerMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
    }


    private Scene createCustomerMenu(String location) {
        try {
            ClassLoader classloader = getClass().getClassLoader();
            URL url = classloader.getResource(location);
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            CustomerController controller = loader.<CustomerController>getController();

            controller.setMainApp(this.mainApp);
            controller.setStage(this.stage);
            controller.setCustomerMenu(this);
            
            System.out.println(url);
            stage.setScene(scene);
            stage.show();
            
            return scene;

        } catch (Exception e) {
            return null;
        }
    }

    public Scene getScene(String location){
        return createCustomerMenu(location);
    }
}
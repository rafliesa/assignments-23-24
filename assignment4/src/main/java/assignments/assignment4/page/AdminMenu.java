package assignments.assignment4.page;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import assignments.assignment3.DepeFood;
import assignments.assignment3.Restaurant;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.form.LoginController;
import assignments.assignment4.page.controller.AdminController;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu{
    private Stage stage;
    private Scene scene;
    private User user;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    private ArrayList<String> restoNameList = new ArrayList<String>();
    private MainApp mainApp; // Reference to MainApp instance
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private ListView<String> menuItemsListView = new ListView<>();

    public AdminMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addRestaurantScene = createAddRestaurantForm();
        this.addMenuScene = createAddMenuForm();
        this.viewRestaurantsScene = createViewRestaurantsForm();
    }

    @Override
    public Scene createBaseMenu() {
        // TODO: Implementasikan method ini untuk menampilkan menu untuk Admin
        VBox menuLayout = new VBox(10);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createAddRestaurantForm() {
        // TODO: Implementasikan method ini untuk menampilkan page tambah restoran
        VBox layout = new VBox(10);

        return new Scene(layout, 400, 600);
    }

    private Scene createAddMenuForm() {
        // TODO: Implementasikan method ini untuk menampilkan page tambah menu restoran
        VBox layout = new VBox(10);
    
        return new Scene(layout, 400, 600);
    }
    
    
    private Scene createViewRestaurantsForm() {
        // TODO: Implementasikan method ini untuk menampilkan page daftar restoran
        VBox layout = new VBox(10);
    
        return new Scene(layout, 400, 600);
    }

    private Scene createAdminMenu(String location) {
        try {
            ClassLoader classloader = getClass().getClassLoader();
            URL url = classloader.getResource(location);
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            AdminController controller = loader.<AdminController>getController();

            controller.setMainApp(this.mainApp);
            controller.setStage(this.stage);
            controller.setAdminMenu(this);

            stage.setScene(scene);
            stage.show();
            
            return scene;

        } catch (Exception e) {
            return null;
        }
    }
    public Scene getScene(String URL){
        return this.createAdminMenu(URL);
    }

    public String[] getRestoNameList(){
        return this.restoNameList.toArray(new String[0]);
    }
}

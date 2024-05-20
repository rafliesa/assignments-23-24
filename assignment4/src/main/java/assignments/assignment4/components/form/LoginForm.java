package assignments.assignment4.components.form;
import assignments.assignment3.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import java.net.URL;
import java.lang.ClassLoader;


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
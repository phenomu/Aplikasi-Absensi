package app.admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DashboardAdminController implements Initializable {

    Connection connection = null;
    String uname; 

    @FXML
    private Button back, show_log, edit_profile;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        show_log.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("log.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Logs Login");
                stage.show();
            } catch (IOException e1) {  
                e1.printStackTrace();
            }
        });

        edit_profile.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Edit Profile");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        back.setOnAction(e -> {
            try {
                back.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Admin Login");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

}
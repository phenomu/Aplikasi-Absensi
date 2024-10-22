package app.admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import app.data.Session;
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
    private Button back, show_log, edit_profile, add_dosen, edit_dosen, add_mahasiswa, edit_mahasiswa;
    
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

        add_dosen.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addDataDosen.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Add Dosen");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        edit_dosen.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editDataDosen.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Edit Data Dosen");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        add_mahasiswa.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addDataMahasiswa.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Add Mahasiswa");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        edit_mahasiswa.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editDataMahasiswa.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Edit Data Mahasiswa");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        back.setOnAction(e -> {
            Session.clearSession();
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
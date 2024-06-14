package app.admin;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class login {

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Button submit;
    
    @FXML
    private Button back;
    
    @FXML
    private Label res;

    @FXML
    void initialize() {
        submit.setOnAction(e -> {
            String user = username.getText();
            String pwd = password.getText();
            if (user.equals("admin") && pwd.equals("admln")) {
                res.setText("");
                try {
                    submit.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardAdminController.fxml"));
                    Parent root;
                    root = (Parent) loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Dashboard Admin");
                    stage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else{
                res.setText("Username / Password Salah!");
            }
        });
        back.setOnAction(e -> {
            try {
                back.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainframe.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Login Page");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

}

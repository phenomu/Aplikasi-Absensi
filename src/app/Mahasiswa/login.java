package app.Mahasiswa;

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
import app.data.Student;

public class login {

    static String user;

    @FXML private TextField username;
    
    @FXML private PasswordField password;
    
    @FXML private Button submit;
    
    @FXML private Button logout;

    @FXML private Label res;

    public static String getUser(){
        return user;
    }

    @FXML
    void initialize() {


        submit.setOnAction(e -> {
            user = username.getText();

            if (user.length() == 15 ) {
                int check = 0;
                for (Student students : Student.Student) {
                    if(user.equals(students.getNim())){
                        check = 1;
                        break;
                    }
                }
                if(check > 0){
                    res.setText("");
                    try {
                        submit.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardUserController.fxml"));
                        Parent root;
                        root = (Parent) loader.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Dashboard User");
                        stage.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else{
                    res.setText("NIM Tidak Ditemukan!");
                }
			}else{
				res.setText("Nim Harus 15 Digit!");
			}
        });
        
        logout.setOnAction(e -> {
            try {
                logout.getScene().getWindow().hide();
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

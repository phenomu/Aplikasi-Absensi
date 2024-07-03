package app.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.data.Hash;
import app.data.filter;
import app.config.Conn;

public class addDataMahasiswa {

    Connection connection = null;
    static String user;
    String pwd;
    PreparedStatement statement;

    @FXML private Stage stage;
    @FXML private TextField username;
    @FXML private Button submit;
    @FXML private Button logout;
    @FXML private Label res;
    @FXML private TextField nims;
    @FXML private TextField namas;
    @FXML private TextField passwords;
    @FXML private String nim;
    @FXML private String nama;
    @FXML private String password;

    public static String getUser(){
        return user;
    }

    public boolean addDosen(String nim, String nama, String password){
        try{
            Hash md5 = new Hash();
            connection = Conn.getConnection();
            String query = "INSERT INTO mahasiswa (id, nim, nama, password) VALUES (NULL, ?, ?, ?);";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nim);
            statement.setString(2, nama);
            statement.setString(3, md5.md5(password));
            int res = statement.executeUpdate();
            if(res > 0){
                return true;
            }
            return false;
        }catch(SQLException er){
            System.out.println(er);
            return false;
        }
    }

    @FXML
    void initialize() {

        submit.setOnAction(e -> {
            nim = nims.getText();
            if (nim.length() != 15 ) {
                res.setText("Nim Harus 15 Digit!");
            }else{
                nama = namas.getText();
                password = passwords.getText();
                if (filter.hasSQLinjection(nim)==true || filter.hasSQLinjection(nama)==true || filter.hasSQLinjection(password)==true ) {
                    System.out.println("SQL Injection DETECTED!!");
                    System.exit(0);
                }
                if(addDosen(nim, nama, password)){
                    System.out.println("Data Berhasil Ditambahkan");
                    res.setTextFill(Color.GREEN);
                    res.setText("Data Berhasil Ditambahkan.");
                }else{
                    res.setTextFill(Color.FIREBRICK);
                    res.setText("Data Gagal Ditambahkan.");
                }
            }
        });
        
        logout.setOnAction(e -> {
            logout.getScene().getWindow().hide();
        });
    }

}

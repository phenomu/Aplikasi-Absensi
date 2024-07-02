package app.Dosen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.data.Session;
// import app.data.filter;
import app.config.Conn;

public class addData {

    DashboardDosenController dashboardController = DashboardDosenController.getInstance();

    Connection connection = null;
    static String user;
    String pwd;
    PreparedStatement statement;

    @FXML private Stage stage;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button submit;
    @FXML private Button logout;
    @FXML private Label res;
    @FXML private TextField matkuls;
    @FXML private TextField skss;
    @FXML private TextField kelass;
    @FXML private TextField ruangs;
    @FXML private TextField jams;
    @FXML private String matkul;
    @FXML private Integer sks;
    @FXML private String kelas;
    @FXML private String ruang;
    @FXML private String jam;

    public static String getUser(){
        return user;
    }

    public boolean addJadwal(String matkul,int sks, String kelas, String ruang, String jam, int dosen_id){
        try{
            connection = Conn.getConnection();
            String query = "INSERT INTO matakuliah (nama, sks, kelas, ruang, jam, active) VALUES (?, ?, ?, ?, ?, 0)";
            String query2 = "INSERT INTO dosen_matakuliah (dosen_id, matakuliah_id) VALUES (?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, matkul);
            statement.setInt(2, sks);
            statement.setString(3, kelas);
            statement.setString(4, ruang);
            statement.setString(5, jam);
            int res = statement.executeUpdate();
            if(res > 0){
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int matakuliahId = generatedKeys.getInt(1);
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setInt(1, dosen_id);
                statement2.setInt(2, matakuliahId);
                int res2 = statement2.executeUpdate();
                if(res2 > 0){
                    System.out.println("Data added!");
                    return true;
                }
            }
        }
            return false;
        }catch(SQLException er){
            System.out.println(er);
            return false;
        }
    }

    @FXML
    void initialize() {

        // Hash md5 = new Hash();
        submit.setOnAction(e -> {
            matkul = matkuls.getText();
            sks = Integer.parseInt(skss.getText());
            ruang = ruangs.getText();
            kelas = kelass.getText();
            jam = jams.getText();
            if(addJadwal(matkul, sks, kelas, ruang, jam,Integer.parseInt(Session.userId))){
                System.out.println("Data Berhasil Ditambahkan");
                res.setTextFill(Color.GREEN);
                res.setText("Data Berhasil Ditambahkan.");
                dashboardController.refreshTableView();
            }else{
                res.setTextFill(Color.FIREBRICK);
                res.setText("Data Gagal Ditambahkan.");
            }
        });
        
        logout.setOnAction(e -> {
            logout.getScene().getWindow().hide();
        });
    }

}

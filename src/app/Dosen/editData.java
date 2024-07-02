package app.Dosen;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import app.data.Session;
// import app.data.filter;
import app.config.Conn;

public class editData implements Initializable{

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
    @FXML private TextField ids;
    @FXML private TextField matkuls;
    @FXML private TextField skss;
    @FXML private TextField kelass;
    @FXML private TextField ruangs;
    @FXML private TextField jams;
    @FXML private String matkul;
    @FXML private Integer id;
    @FXML private Integer sks;
    @FXML private String kelas;
    @FXML private String ruang;
    @FXML private String jam;

    public void initData(Integer id, String matkul, Integer sks, String kelas, String ruang, String jam) {
        this.id = id;
        this.sks = sks;
        this.kelas = kelas;
        this.ruang = ruang;
        this.matkul = matkul;
        this.jam = jam;
        ids.setText(String.valueOf(id));
        skss.setText(String.valueOf(sks));
        matkuls.setText(matkul);
        kelass.setText(kelas);
        ruangs.setText(ruang);
        jams.setText(jam);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setOnAction(e -> {
            matkul = matkuls.getText();
            sks = Integer.parseInt(skss.getText());
            ruang = ruangs.getText();
            kelas = kelass.getText();
            jam = jams.getText();
            if(editJadwal(matkul, sks, kelas, ruang, jam,Integer.parseInt(Session.userId))){
                System.out.println("Data Berhasil Ditambahkan");
                res.setTextFill(Color.GREEN);
                res.setText("Data Berhasil Ditambahkan.");
            }else{
                res.setTextFill(Color.FIREBRICK);
                res.setText("Data Gagal Ditambahkan.");
            }
        });
        
        logout.setOnAction(e -> {
            logout.getScene().getWindow().hide();
        });
    }

    public static String getUser(){
        return user;
    }

    public boolean editJadwal(String matkul,int sks, String kelas, String ruang, String jam, int dosen_id){
        try{
            connection = Conn.getConnection();
            String query = "UPDATE matakuliah SET nama =?, sks =?, kelas =?, ruang =?, jam =? WHERE id =?";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, matkul);
            statement.setInt(2, sks);
            statement.setString(3, kelas);
            statement.setString(4, ruang);
            statement.setString(5, jam);
            statement.setInt(6, id);
            int res = statement.executeUpdate();
            if(res > 0){
                dashboardController.refreshTableView();
                System.out.println("Data Updated!");
                return true;
            }return false;
        }catch(SQLException er){
            System.out.println(er);
            return false;
        }
    }
}
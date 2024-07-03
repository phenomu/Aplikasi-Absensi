package app.Mahasiswa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import app.config.Conn;
import app.data.Jadwal;
import app.data.Session;


public class DashboardUserController implements Initializable {

    Connection connection = null;
    String uname; 
    
    private ObservableList<Jadwal> Jadwals = FXCollections.observableArrayList();
    @FXML private TableView<Jadwal> tableView1;
    @FXML private TableColumn<Jadwal, Integer> id;
    @FXML private TableColumn<Jadwal, Integer> no;
    @FXML private TableColumn<Jadwal, String> matkul;
    @FXML private TableColumn<Jadwal, String> sks;
    @FXML private TableColumn<Jadwal, String> kelas;
    @FXML private TableColumn<Jadwal, String> dosen;
    @FXML private TableColumn<Jadwal, String> jam;
    @FXML private TableColumn<Jadwal, String> ruang;
    @FXML private AnchorPane add_books;
    @FXML private Label nim;
    @FXML private Label name;
    @FXML private Label alert;
    @FXML private TextField idkelas;
    @FXML private Button submitid;
    @FXML private Button logout;
    @FXML private TableColumn<Jadwal, Void> presensi;
    PreparedStatement statement;
    

    public boolean show(){
        Jadwals.clear();
        try{
            connection = Conn.getConnection();
            String query = "SELECT m.nama AS matkul,m.sks AS sks,m.jam AS jam,m.ruang AS ruang,m.kelas AS kelas,m.id AS id,d.nama AS dosen FROM mahasiswa ma JOIN mahasiswa_matakuliah mm ON ma.id = mm.mahasiswa_id JOIN matakuliah m ON mm.matakuliah_id = m.id JOIN dosen_matakuliah dm ON m.id = dm.matakuliah_id JOIN dosen d ON dm.dosen_id = d.id WHERE ma.id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, Session.userId);
            try(ResultSet res = statement.executeQuery()){
                int i = 1;
                while(res.next()){
                    Jadwals.add(new Jadwal(res.getInt("id"),i,res.getString("matkul"),res.getString("sks"),res.getString("kelas"),res.getString("dosen"),res.getString("ruang"),res.getString("jam")));
                    i++;
                }
                    return (true);
                }
            }catch(SQLException e){
                System.out.println(e);
                return false;
            }
    }

    public ArrayList<Integer> getListActive(){
        try{
            connection = Conn.getConnection();
            String query = "SELECT m.id FROM mahasiswa ma JOIN mahasiswa_matakuliah mm ON ma.id = mm.mahasiswa_id JOIN matakuliah m ON mm.matakuliah_id = m.id WHERE ma.id =? AND m.active = 1";
            statement = connection.prepareStatement(query);
            statement.setString(1, Session.userId);
            try(ResultSet res = statement.executeQuery()){
                ArrayList<Integer> idArray = new ArrayList<>();
                while(res.next()){
                    idArray.add(res.getInt("id"));
                }
                    return idArray;
                }
            }catch(SQLException e){
                System.out.println(e);
            }
        return null;
    }

    public boolean present(int id){
        LocalDate today = LocalDate.now();
        String dateString = today.toString();
        try{
            connection = Conn.getConnection();
            String query = "INSERT INTO absensi (mahasiswa_id, matakuliah_id, tanggal, hadir) SELECT ?, ?, ?, 1 FROM DUAL WHERE NOT EXISTS ( SELECT 1 FROM absensi WHERE tanggal =? AND matakuliah_id =? AND mahasiswa_id =?);";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(Session.userId));
            statement.setInt(2, id);
            statement.setString(3, dateString);
            //Untuk mencegah data duplicate
            statement.setString(4, dateString);
            statement.setInt(5, id);
            statement.setInt(6, Integer.valueOf(Session.userId));
            int affectedRows = statement.executeUpdate();
            if(affectedRows>0){
                return true;
            }
            }catch(SQLException e){
                System.out.println(e);
            }
        
        return false;
    }

    public boolean addKelas(int id){
        try{
            connection = Conn.getConnection();
            String query = "INSERT INTO mahasiswa_matakuliah (mahasiswa_id, matakuliah_id) SELECT ?, ? FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM mahasiswa_matakuliah WHERE mahasiswa_id = ? AND matakuliah_id = ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.valueOf(Session.userId));
            statement.setInt(2, id);
            statement.setInt(3, Integer.valueOf(Session.userId));
            statement.setInt(4, id);
            int affectedRows = statement.executeUpdate();
            if(affectedRows>0){
                return true;
            }
            }catch(SQLException e){
                System.out.println(e);
            }
        
        return false;
    }
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nim.setText(Session.getNim());
        name.setText(Session.getName());
        
        //Cek Apakah User Yang Masuk Telah Memiliki Sesi Yang Valid
        if(!Session.isValid()){
            System.out.println("Illegal Access to Dashboard!");
            System.exit(0);
        }
        show();
                    
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        matkul.setCellValueFactory(new PropertyValueFactory<>("matkul"));
        sks.setCellValueFactory(new PropertyValueFactory<>("sks"));
        kelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
        dosen.setCellValueFactory(new PropertyValueFactory<>("dosen"));
        ruang.setCellValueFactory(new PropertyValueFactory<>("ruang"));
        jam.setCellValueFactory(new PropertyValueFactory<>("jam"));
        //Function untuk menambahkan button pada kolom presensi
        presensi.setCellFactory((Callback<TableColumn<Jadwal, Void>, TableCell<Jadwal, Void>>) new Callback<TableColumn<Jadwal, Void>, TableCell<Jadwal, Void>>() {
            @Override
            public TableCell<Jadwal, Void> call(final TableColumn<Jadwal, Void> presensi) {
                final TableCell<Jadwal, Void> cell = new TableCell<Jadwal, Void>() {
                    final Button btn = new Button("Present");
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Jadwal jadwal = getTableView().getItems().get(getIndex());
                            List<Integer> matkulValues = getListActive();
                            for (Integer matkul : matkulValues) {
                                if (matkul == jadwal.getId()) {
                                    btn.setOnAction(event -> {
                                        System.out.println("Interaksi Terhadap ID : " + jadwal.getId() + " & Matkul : " + jadwal.getMatkul());
                                        if(present(jadwal.getId())){
                                            alert.setTextFill(Color.GREEN);
                                            alert.setText("Presensi Berhasil Dilakukan");
                                            tableView1.refresh();
                                        }else{
                                            alert.setTextFill(Color.FIREBRICK);
                                            alert.setText("Anda Sudah Melakukan Presensi");
                                            tableView1.refresh();
                                        }
                                    });
                                    setGraphic(btn);
                                }
                            }
                        }
                    }
                };
                return cell;
            }
        });
        tableView1.setItems(Jadwals);
        
        submitid.setOnAction(e -> {
            int idkelass = Integer.valueOf(idkelas.getText());
            if(addKelas(idkelass)){
                alert.setTextFill(Color.GREEN);
                alert.setText("anda berhasil ditambahkan ke Kelas");
                show();
                tableView1.refresh();
            }else{
                alert.setTextFill(Color.FIREBRICK);
                alert.setText("anda gagal ditambahkan ke kelas");
                tableView1.refresh();
            }
        });

        logout.setOnAction(e -> {
            Session.clearSession();
            try {
                logout.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root;
                root = (Parent) loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Mahasiswa Login");
                stage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }
}
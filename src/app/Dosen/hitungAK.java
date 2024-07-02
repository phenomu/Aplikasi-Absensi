package app.Dosen;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class hitungAK {

    //Function LoadFXML Digunakan Untuk Memuat file FXML lain
    public void LoadFXML(String target, String title){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(target));
            Parent root;
            root = (Parent) loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        }catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //Function clearResult digunakan untuk membuat semua label yang ada di dalamnya menjadi kosong / null
    public void clearResult(){
        huas.setText("");ketuas.setText("");
        hket.setText("");ketketerangan.setText("");
        hgrade.setText("");ketgrade.setText("");
        //Digunakan untuk mengosongkan semua textfield
        nim.clear(); nama.clear(); absen.clear(); tugas.clear(); uts.clear(); uas.clear();
    }

    //Function getData digunakan untuk menghitung nilai akhir
    public String[] getData(Integer absen, Integer tugas, Integer uts, Integer uas){
        //Varibale res digunakan untuk menyimpan sum dari nilai absen, tugas, uts, uas
        double res = (0.2*absen) + (0.25*tugas) + (0.25*uts) + (0.3*uas);
        String grade = "";
        String lulus = "0";
        if (res >= 101){
            grade = "?";
            lulus = "2";
        }
        else if (res >= 80){
            grade = "A";
            lulus = "1";
        }else if(res >= 70){
            grade = "B";
            lulus = "1";
        }else if(res >= 60){
            grade = "C";
            lulus = "1";
        }else if(res >= 50){
            grade = "D";
        }else{
            grade = "E";
        }
        //Mengembalikan nilai berupa String Array, yg isinya Nilai Akhir, Grade, dan Lulus / Tidak
        //format("%.2f",res) digunakan untuk mengambil 2 angka dibelakang koma 
        return new String[]{String.format("%.2f",res),grade,lulus};
    }

    public void showResult(String nim, String nama, String absen, String tugas, String uts, String uas){
        //Validasi apakah data ada yang kosong atau tidak
        if (nim.length()>0 && nama.length()>0 && absen.length()>0 && tugas.length()>0 && uts.length()>0 && uas.length()>0) {
            //Variable data digunakan untuk menyimpan result dari function getData()
            String data[] = getData(Integer.valueOf(absen), Integer.valueOf(tugas), Integer.valueOf(uts), Integer.valueOf(uas));
            //data[0] = Nilai Akhir
            //data[1] = Grade (A B C D E)
            //data[2] = Kelulusan 1 = lulus, 0 = tidak lulus, 2 = Kesalahan Input Data
            hket.setText("Keterangan");
            if(data[2].equals("0")){
                //jika nilai data[2] == 0, maka keterangan yang akan muncul adalah "Maaf Anda Tidak Lulus!"
                ketketerangan.setTextFill(Color.FIREBRICK);
                ketketerangan.setText("Maaf Anda Tidak Lulus!");
            }else if(data[2].equals("2")){
                //jika nilai data[2] == 2, maka keterangan yang akan muncul adalah "Pastikan Data Sudah Benar!"
                ketketerangan.setTextFill(Color.FIREBRICK);
                ketketerangan.setText("Pastikan Data Sudah Benar!");
            }else{
                //jika nilai data[2] adalah selain 0 & 2, maka keterangan yang akan muncul adalah "Selamat Anda Lulus!"
                ketketerangan.setTextFill(Color.GREEN);
                ketketerangan.setText("Selamat Anda Lulus!");
            }
            //Untuk Pengkondisian dibawah ini untuk mengubah warna dari grade, jika grade A maka warnanya akan menjadi hijau
            //Jika Grade nya D & E, maka warnanya akan menjadi merah
            //Selain itu warnanya Gradenya akan tetap Putih
            if(data[1].equals("A")){
                ketgrade.setTextFill(Color.GREEN);
            }else if(data[1].equals("D")||data[1].equals("E")){
                ketgrade.setTextFill(Color.FIREBRICK);
            }else{
                ketgrade.setTextFill(Color.WHITE);
            }
            huas.setText("Nilai Akhir");ketuas.setText(data[0]);
            hgrade.setText("Grade");ketgrade.setText(data[1]);
        }else{
            //Jika Ada Data yang kosong, maka munculkan keterangan "Data Tidak Lengkap!"
            hket.setTextFill(Color.FIREBRICK);
            hket.setText("Keterangan");
            ketketerangan.setTextFill(Color.FIREBRICK);
            ketketerangan.setText("Data Tidak Lengkap!");
        }
    }

    @FXML private Stage stage;
    @FXML private TextField nim, nama, absen, tugas, uts, uas;
    @FXML private Label huas, hket, hgrade;
    @FXML private Label ketuas, ketketerangan, ketgrade;
    @FXML private Button submit, reset, logout;

    @FXML
    void initialize(){
        clearResult();
        
        submit.setOnAction(e -> {
            //Mengambil semua data dari formulir yang ada
            String nims = nim.getText();
            String namas = nama.getText();
            String absens = absen.getText();
            String tugass = tugas.getText();
            String utss = uts.getText();
            String uass = uas.getText();
            //Memanggil Function showResult
            try{
                ketketerangan.setTextFill(Color.WHITE);
                showResult(nims, namas, absens, tugass, utss, uass);
            }catch(Exception er){
                //Untuk handler jika ada tipe data yang salah saat memasukan nilai
                hket.setTextFill(Color.FIREBRICK);
                hket.setText("Keterangan");
                ketketerangan.setTextFill(Color.FIREBRICK);
                ketketerangan.setText("Masukkan Data Dengan Benar!");
            }
        });

        //Function untuk mereset semua data yang ada
        reset.setOnAction(e -> {
            clearResult();
        });

        //Kembali Ke Dashboard
        logout.setOnAction(e -> {
            try {
                //Menutup scene yang sedang terbuka saat ini
                logout.getScene().getWindow().hide();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
    
}

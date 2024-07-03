package app.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class JadwalDosen {
    private int id;
    private int no;
    private String matkul;
    private String sks;
    private String kelas;
    private String status;
    private String ruang;
    private String jam;

    @FXML
    public static ObservableList<JadwalDosen> JadwalDosen = FXCollections.observableArrayList();

    public JadwalDosen(int id, int no, String matkul, String sks, String kelas, String status ,String ruang, String jam) {
        this.id = id;
        this.no = no;
        this.matkul = matkul;
        this.sks = sks;
        this.kelas = kelas;
        this.status = status;
        this.ruang = ruang;
        this.jam = jam;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }
    
    public int getId() {
        return id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getJam() {
        return jam;
    }
    
}
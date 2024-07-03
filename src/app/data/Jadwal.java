package app.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Jadwal {
    private int id;
    private int no;
    private String matkul;
    private String sks;
    private String kelas;
    private String dosen;
    private String ruang;
    private String jam;

    @FXML
    public static ObservableList<Jadwal> Jadwal = FXCollections.observableArrayList();

    public Jadwal(int id, int no, String matkul, String sks, String kelas, String dosen ,String ruang, String jam) {
        this.id = id;
        this.no = no;
        this.matkul = matkul;
        this.sks = sks;
        this.kelas = kelas;
        this.dosen = dosen;
        this.ruang = ruang;
        this.jam = jam;
    }

    public String getMatkul() {
        return matkul;
    }

    public int getId() {
        return id;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
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

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
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

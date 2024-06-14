package app.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Student {
    private int no;
    private String namaMahasiswa;
    private String nim;
    private String jurusan;
    private String prodi;
    private int totalBukuDipinjam;
    static int i;

    @FXML
    public static ObservableList<Student> Student = FXCollections.observableArrayList();

    public Student(int no, String namaMahasiswa, String nim, String jurusan, String prodi, int totalBukuDipinjam) {
        this.no = no;
        this.namaMahasiswa = namaMahasiswa;
        this.nim = nim;
        this.jurusan = jurusan;
        this.prodi = prodi;
        this.totalBukuDipinjam = totalBukuDipinjam;
        i = i+1;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public int getTotalBukuDipinjam() {
        return totalBukuDipinjam;
    }

    public void setTotalBukuDipinjam(int totalBukuDipinjam) {
        this.totalBukuDipinjam = totalBukuDipinjam;
    }

    public static int get() {
        return i;
    }
}

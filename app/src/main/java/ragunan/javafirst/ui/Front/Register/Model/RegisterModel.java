package ragunan.javafirst.ui.Front.Register.Model;

public class RegisterModel {
    String nama,email,password,jenis_kelamin,foto,tanggal_lahir,alamat,phone;

    public RegisterModel(String nama, String email, String password, String jenis_kelamin, String foto, String tanggal_lahir, String alamat, String phone) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.jenis_kelamin = jenis_kelamin;
        this.foto = foto;
        this.tanggal_lahir = tanggal_lahir;
        this.alamat = alamat;
        this.phone = phone;
    }
}

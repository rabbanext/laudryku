package project.akbaralzaini.laudryku.model;

import com.google.gson.annotations.SerializedName;

public class Laundry {
    @SerializedName("id_laundry")
    private int id_laundry;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("nama_laundry")
    private  String nama_laundry;
    @SerializedName("nama_pemilik")
    private String nama_pemilik;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("tlp")
    private String tlp;
    @SerializedName("foto")
    private String foto;
    @SerializedName("status")
    private String status;

    public Laundry(int id_laundry, String id_user, String nama_laundry, String nama_pemilik, String alamat, String tlp, String foto) {
        this.id_laundry = id_laundry;
        this.id_user = id_user;
        this.nama_laundry = nama_laundry;
        this.nama_pemilik = nama_pemilik;
        this.alamat = alamat;
        this.tlp = tlp;
        this.foto = foto;
    }
    public Laundry() {
        this.id_laundry = 0;
        this.id_user = "";
        this.nama_laundry = "";
        this.nama_pemilik = "";
        this.alamat = "";
        this.tlp = "";
        this.foto = "";
    }

    public int getId_laundry() {
        return id_laundry;
    }

    public void setId_laundry(int id_laundry) {
        this.id_laundry = id_laundry;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_laundry() {
        return nama_laundry;
    }

    public void setNama_laundry(String nama_laundry) {
        this.nama_laundry = nama_laundry;
    }

    public String getNama_pemilik() {
        return nama_pemilik;
    }

    public void setNama_pemilik(String nama_pemilik) {
        this.nama_pemilik = nama_pemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

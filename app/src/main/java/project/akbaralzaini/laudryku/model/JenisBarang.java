package project.akbaralzaini.laudryku.model;

import com.google.gson.annotations.SerializedName;

public class JenisBarang {
    @SerializedName("id_jenis")
    private String id_jenis;
    @SerializedName("id_laundry")
    private String id_laundry;
    @SerializedName("nama_jenis")
    private String nama_jenis;
    @SerializedName("harga_jenis")
    private int harga_jenis;
    @SerializedName("lama_waktu")
    private int lama_waktu;

    public JenisBarang(String nama_jenis, int harga_jenis, int lama_waktu) {
        this.nama_jenis = nama_jenis;
        this.harga_jenis = harga_jenis;
        this.lama_waktu = lama_waktu;
    }

    public JenisBarang() {
        this.id_jenis = "";
        this.id_laundry = "";
        this.nama_jenis = "";
        this.harga_jenis = 0;
        this.lama_waktu = 0;
    }


    public String getNama_jenis() {
        return nama_jenis;
    }

    public void setNama_jenis(String nama_jenis) {
        this.nama_jenis = nama_jenis;
    }

    public String getId_jenis() {
        return id_jenis;
    }

    public void setId_jenis(String id_jenis) {
        this.id_jenis = id_jenis;
    }

    public String getId_laundry() {
        return id_laundry;
    }

    public void setId_laundry(String id_laundry) {
        this.id_laundry = id_laundry;
    }

    public int getHarga_jenis() {
        return harga_jenis;
    }

    public void setHarga_jenis(int harga_jenis) {
        this.harga_jenis = harga_jenis;
    }

    public int getLama_waktu() {
        return lama_waktu;
    }

    public void setLama_waktu(int lama_waktu) {
        this.lama_waktu = lama_waktu;
    }
}

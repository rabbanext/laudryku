package project.akbaralzaini.laudryku.userInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import project.akbaralzaini.laudryku.R;
import project.akbaralzaini.laudryku.model.JenisBarang;
import project.akbaralzaini.laudryku.model.Laundry;
import project.akbaralzaini.laudryku.rest.ApiClient;
import project.akbaralzaini.laudryku.rest.JenisBarangApiInterface;
import project.akbaralzaini.laudryku.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JenisBarangActivity extends AppCompatActivity {

    private TextView tvNamaJenis,tvHargaJenis,tvLamaWaktu;
    private Button btnAddJenis;
    private SharedPrefManager sharedPrefManager;
    private JenisBarangApiInterface jenisBarangApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_barang);

        tvHargaJenis = findViewById(R.id.harga_jenis);
        tvNamaJenis = findViewById(R.id.nama_jenis);
        tvLamaWaktu =  findViewById(R.id.lama_jenis);
        btnAddJenis = findViewById(R.id.button_tambah);
        jenisBarangApiInterface = ApiClient.getClient().create(JenisBarangApiInterface.class);
        sharedPrefManager = new SharedPrefManager(this);

        btnAddJenis.setOnClickListener(v -> {
            Laundry laundry = sharedPrefManager.getLaundry();
            JenisBarang jenisBarang = new JenisBarang(tvNamaJenis.getText().toString(),Integer.parseInt(tvHargaJenis.getText().toString()),Integer.parseInt(tvLamaWaktu.getText().toString()));
            Call<JenisBarang> jenisBarangCall = jenisBarangApiInterface.postJenisBarang(jenisBarang,laundry.getId_laundry());
            jenisBarangCall.enqueue(new Callback<JenisBarang>() {
                @Override
                public void onResponse(Call<JenisBarang> call, Response<JenisBarang> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JenisBarangActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Data Berhasil dimasukan");
                    builder.setPositiveButton("Ok", (dialogInterface, i) -> {
                        Intent loginIntent = new Intent(JenisBarangActivity.this,DashboardActivity.class);
                        startActivity(loginIntent);
                        finish();
                    });
                    builder.show();
                }

                @Override
                public void onFailure(Call<JenisBarang> call, Throwable t) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JenisBarangActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage(t.getMessage());
                    builder.show();
                }
            });
        });
    }
}

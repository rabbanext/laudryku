package project.akbaralzaini.laudryku.userInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import project.akbaralzaini.laudryku.R;
import project.akbaralzaini.laudryku.model.JenisBarang;
import project.akbaralzaini.laudryku.rest.ApiClient;
import project.akbaralzaini.laudryku.rest.JenisBarangApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOrderActivity extends Activity {
    private Button btnAddDetail;
    private LinearLayout llDetail;
    private EditText edtNamaPemesan,edtTlp,edtBerat;
    private TextView tvTotal;
    private Spinner sDaftarJenis;
    JenisBarangApiInterface jenisBarangApiInterface;
    List<JenisBarang> list = new ArrayList<JenisBarang>();
    private int total_bayar=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        llDetail = findViewById(R.id.detail);
        sDaftarJenis = findViewById(R.id.jenis);
        edtBerat = findViewById(R.id.berat_order);
        tvTotal = findViewById(R.id.total_bayar);
        tvTotal.setText("Rp. 0");
        populateSpinner();

        btnAddDetail = findViewById(R.id.button_add_barang);
        btnAddDetail.setOnClickListener(v -> {
            LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            final View addView = layoutInflater.inflate(R.layout.row_detal,null);
            TextView textOut = (TextView) addView.findViewById(R.id.jenis_barang);
            TextView tvHarga = (TextView) addView.findViewById(R.id.harga_barang);

            //set text
            try {
                int pos = sDaftarJenis.getSelectedItemPosition();
                float number = Float.valueOf(edtBerat.getText().toString());
                float harga = list.get(pos).getHarga_jenis() * number;
                String barang = "" + list.get(pos).getNama_jenis() + " " + list.get(pos).getLama_waktu() + " hari x " + edtBerat.getText().toString() + " Kg";

                Locale locale = new Locale("id", "ID");
                NumberFormat sharga = NumberFormat.getCurrencyInstance(locale);
                textOut.setText(barang);
                tvHarga.setText(sharga.format(harga));
                updateTotalBayar(harga,true);
                ImageView btnRemove = (ImageView) addView.findViewById(R.id.delete);
                btnRemove.setOnClickListener(v1 -> {
                    ((LinearLayout) addView.getParent()).removeView(addView);

                });
                llDetail.addView(addView);
            }
            catch (Exception e){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddOrderActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Masukan Berat");
                builder.setNeutralButton("Ok", (dialogInterface, i) -> { });
                builder.show();
            }
        });

    }

    private void updateTotalBayar(float harga,boolean p) {
        if (p){
            total_bayar = (int) (total_bayar+harga);
        }
        else{
            total_bayar = (int) (total_bayar-harga);
        }

        tvTotal.setText("RP. "+total_bayar);
    }

    private void populateSpinner() {
        jenisBarangApiInterface = ApiClient.getClient().create(JenisBarangApiInterface.class);
        Call<List<JenisBarang>> jenisBarangCall = jenisBarangApiInterface.getJenisBarang("2");
        jenisBarangCall.enqueue(new Callback<List<JenisBarang>>() {
            @Override
            public void onResponse(Call<List<JenisBarang>> call, Response<List<JenisBarang>> response) {
                try{
                    List<JenisBarang>  jenisBarangList = response.body();
                    List<String> listSpinner = new ArrayList<>();

                    for (int i=0;i<jenisBarangList.size();i++){
                        String text = ""+jenisBarangList.get(i).getNama_jenis()+jenisBarangList.get(i).getLama_waktu();
                        listSpinner.add(text);
                        list.add(jenisBarangList.get(i));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddOrderActivity.this, android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sDaftarJenis.setAdapter(adapter);

                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddOrderActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Data barang tidak ditemukan");
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<List<JenisBarang>> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddOrderActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Koneksi internet terputus");
                builder.show();
            }
        });
    }
}

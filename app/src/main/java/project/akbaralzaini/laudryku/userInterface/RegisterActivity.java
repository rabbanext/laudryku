package project.akbaralzaini.laudryku.userInterface;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import project.akbaralzaini.laudryku.R;
import project.akbaralzaini.laudryku.model.Laundry;
import project.akbaralzaini.laudryku.model.User;
import project.akbaralzaini.laudryku.rest.ApiClient;
import project.akbaralzaini.laudryku.rest.LaundryApiInterface;
import project.akbaralzaini.laudryku.rest.LoginApiInterface;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO : upload file
public class RegisterActivity extends Activity implements EasyPermissions.PermissionCallbacks{
    EditText edtNamaPemilik,edtNomortelpon,edtEmail,edtPassword,edtNamaLaundry,edtAlamat;
    Button btnUpload,btnRegister;
    TextView tvNamaFile;
    String id_userRegistered = "";

    //retroofit
    LaundryApiInterface laundryApiInterface;
    LoginApiInterface loginApiInterface;

    //file upload
    MultipartBody.Part fileToUpload;
    String file_path="";
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "Path_to_your_server";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //deklarasi
        edtNamaPemilik = findViewById(R.id.nama_pemilik);
        edtNomortelpon = findViewById(R.id.nomor_telpon);
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        edtNamaLaundry = findViewById(R.id.nama_laundry);
        edtAlamat = findViewById(R.id.alamat_lengkap);
        tvNamaFile = findViewById(R.id.nama_file_upload);

        //retrofit
        laundryApiInterface = ApiClient.getClient().create(LaundryApiInterface.class);
        loginApiInterface = ApiClient.getClient().create(LoginApiInterface.class);

        //Upload Foto
        btnUpload = findViewById(R.id.btn_upload);
        btnUpload.setOnClickListener(view -> uploadFoto());

        //Register
        btnRegister = findViewById(R.id.btn_daftar);
        btnRegister.setOnClickListener(view -> register());
    }

    private void register() {
        User user = new User(edtEmail.getText().toString(),edtPassword.getText().toString(),"laundry");
        Call<User> userCall = loginApiInterface.registerUser(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User data = response.body();
                if (data != null){
                    id_userRegistered = data.getId_user();
                    registerLaundry();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Registrasi anda gagal");
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Periksa kembali koneksi internet anda");
                builder.show();
            }
        });
    }

    private void registerLaundry() {
        RequestBody nama_laundry = RequestBody.create(MediaType.parse("text/plain"), edtNamaLaundry.getText().toString());
        RequestBody nama_pemilik = RequestBody.create(MediaType.parse("text/plain"),edtNamaPemilik.getText().toString());
        RequestBody id_user = RequestBody.create(MediaType.parse("text/plain"),id_userRegistered);
        RequestBody alamat = RequestBody.create(MediaType.parse("text/plain"),edtAlamat.getText().toString());
        RequestBody tlp = RequestBody.create(MediaType.parse("text/plain"),edtNomortelpon.getText().toString());
        File file = new File(file_path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part parts = MultipartBody.Part.createFormData("foto", file.getName(), requestBody);

        Call<Laundry> laundryCall = laundryApiInterface.createLaundry(parts,id_user,nama_laundry,nama_pemilik,alamat,tlp);
        laundryCall.enqueue(new Callback<Laundry>() {
            @Override
            public void onResponse(Call<Laundry> call, Response<Laundry> response) {
                Laundry dataResult = response.body();
                //assert dataResult != null;
                if (dataResult != null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Pendaftaran anda berhasil, silakan login untuk melihat status verifikasi anda");
                    builder.setNeutralButton("Ok", (dialogInterface, i) -> {
                        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    });
                    builder.show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Pendaftaran anda gagal");
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<Laundry> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage("Periksa kembali koneksi internet anda");
                builder.show();
            }
        });
    }

    private void uploadFoto() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, RegisterActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, RegisterActivity.this);
                File file = new File(filePath);
                Log.d(TAG, "Filename " + file.getName());
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("foto", file.getName(), RequestBody.create(MediaType.parse("image/*"),file));

                file_path = file.getPath();
                tvNamaFile.setText(file.getName());
            }else{
                EasyPermissions.requestPermissions(this, "Memerlukan akases File", READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, RegisterActivity.this);
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("foto", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

            tvNamaFile.setText(file.getName());

        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

}

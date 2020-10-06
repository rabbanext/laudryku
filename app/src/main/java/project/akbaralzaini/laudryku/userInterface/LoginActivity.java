package project.akbaralzaini.laudryku.userInterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import project.akbaralzaini.laudryku.R;
import project.akbaralzaini.laudryku.model.Laundry;
import project.akbaralzaini.laudryku.model.LoginUser;
import project.akbaralzaini.laudryku.model.User;
import project.akbaralzaini.laudryku.rest.ApiClient;
import project.akbaralzaini.laudryku.rest.LaundryApiInterface;
import project.akbaralzaini.laudryku.rest.LoginApiInterface;
import project.akbaralzaini.laudryku.util.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    EditText edtEmail;
    EditText edtPassword;
    LoginApiInterface loginApiInterface;
    LaundryApiInterface laundryApiInterface;
    SharedPrefManager sharedPrefManager;
    ProgressBar progressBar;

    ImageView btn_back;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Deklarasi
        loginApiInterface = ApiClient.getClient().create(LoginApiInterface.class);
        laundryApiInterface = ApiClient.getClient().create(LaundryApiInterface.class);
        sharedPrefManager = new SharedPrefManager(this);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        edtEmail = findViewById(R.id.email_login);
        edtPassword = findViewById(R.id.password_login);

        //button login
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(view -> login());

        //Button back
        btn_back = findViewById(R.id.button_back);
        btn_back.setOnClickListener(view -> finish()); //button back
    }//OnCreate

    private void login() {
        progressBar.setVisibility(View.VISIBLE);

        LoginUser loginUser = new LoginUser(edtEmail.getText().toString(),edtPassword.getText().toString());
        Call<User> userCall = loginApiInterface.LoginUser(loginUser);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User data = response.body();
                if (data != null){
                    progressBar.setVisibility(View.INVISIBLE);
                    //sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, data.());
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, data.getId_user());
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_ROLE, data.getTipe());
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME,data.getEmail_user());
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_PASSWORD,data.getPassword_user());
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                    //Log.d("apappa",data.getId_user());
                    Call<Laundry> laundryCall = laundryApiInterface.getOne(data.getId_user());
                    laundryCall.enqueue(new Callback<Laundry>() {
                        @Override
                        public void onResponse(Call<Laundry> call, Response<Laundry> response) {
                            Laundry laundry = response.body();
                            sharedPrefManager.storeFavorites(laundry);
                        }

                        @Override
                        public void onFailure(Call<Laundry> call, Throwable t) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("Informasi");
                            builder.setMessage(t.getMessage());
                            builder.show();
                        }
                    });

                    Intent dasboard = new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(dasboard);
                    finish();
                }else{
                    progressBar.setVisibility(View.INVISIBLE);

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Informasi");
                    builder.setMessage("Password atau email anda salah");
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Informasi");
                builder.setMessage(t.getMessage());
                builder.show();
            }
        });
    }
}

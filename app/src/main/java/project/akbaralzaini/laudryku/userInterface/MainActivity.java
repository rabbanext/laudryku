package project.akbaralzaini.laudryku.userInterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import project.akbaralzaini.laudryku.R;
import project.akbaralzaini.laudryku.util.SharedPrefManager;

public class MainActivity extends Activity {
    Button btnLogin;
    Button btnRegister;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSPSudahLogin()){
            Intent home = new Intent(MainActivity.this,DashboardActivity.class);
            startActivity(home);
            finish();
        }

        btnLogin = findViewById(R.id.login_button);
        btnRegister = findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }
}

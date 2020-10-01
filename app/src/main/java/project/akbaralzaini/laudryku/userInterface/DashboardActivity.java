package project.akbaralzaini.laudryku.userInterface;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import project.akbaralzaini.laudryku.CurvedBottomNavigationView;
import project.akbaralzaini.laudryku.R;

public class DashboardActivity extends FragmentActivity {
    private FrameLayout fMainFarme;
    private HomeFragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fMainFarme = findViewById(R.id.main_frame);
        homeFragment = new HomeFragment();
        setFragment(homeFragment);

        CurvedBottomNavigationView mView = findViewById(R.id.bottom);
        mView.inflateMenu(R.menu.bottom_app_bar);
        mView.setSelectedItemId(R.id.navigation_home);
        //getting bottom navigation view and attaching the listener
        mView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.navigation_order:
                        //dos
                        return true;
                    case R.id.navigation_notifications:
                        //
                        return true;
                    case R.id.navigation_profil:
                        //
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }


}

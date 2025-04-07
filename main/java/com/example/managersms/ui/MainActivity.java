package com.example.managersms.ui;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.managersms.ui.fragments.BlacklistFragment;
import com.example.managersms.ui.fragments.ContactFragment;
import com.example.managersms.ui.fragments.MessageFragment;
import com.example.managersms.services.BlockService;
import com.example.managersms.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!PermissionUtils.hasPermissions(this)) {
            PermissionUtils.requestPermissions(this);
        }

        startService(new Intent(this, BlockService.class));

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            if (item.getItemId() == R.id.nav_contacts) {
                selected = new ContactFragment();
            } else if (item.getItemId() == R.id.nav_blacklist) {
                selected = new BlacklistFragment();
            } else if (item.getItemId() == R.id.nav_message) {
                selected = new MessageFragment();
            }

            if (selected != null)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
            return true;
        });

        // Load mặc định
        bottomNav.setSelectedItemId(R.id.nav_contacts);
    }
}

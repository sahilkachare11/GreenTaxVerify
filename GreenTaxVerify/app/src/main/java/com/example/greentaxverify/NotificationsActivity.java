package com.example.greentaxverify;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationsActivity extends AppCompatActivity {

    private MaterialToolbar toolbarNotifications;
    private TextView tvActivityTitle;
    private TextView tvNewCount;
    private TextView tvActivitySubtitle;
    private RecyclerView rvNotifications;
    private BottomNavigationView bottomNavNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        toolbarNotifications  = findViewById(R.id.toolbar_notifications);
        tvActivityTitle       = findViewById(R.id.tv_activity_title);
        tvNewCount            = findViewById(R.id.tv_new_count);
        tvActivitySubtitle    = findViewById(R.id.tv_activity_subtitle);
        rvNotifications       = findViewById(R.id.rv_notifications);
        bottomNavNotifications = findViewById(R.id.bottom_nav_notifications);

        setSupportActionBar(toolbarNotifications);

        rvNotifications.setLayoutManager(new LinearLayoutManager(this));
        // TODO: set adapter on rvNotifications

        bottomNavNotifications.setOnItemSelectedListener(item -> {
            // TODO: handle bottom nav tab changes
            return true;
        });
    }
}

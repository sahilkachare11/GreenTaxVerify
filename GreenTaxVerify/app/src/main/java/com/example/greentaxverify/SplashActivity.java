package com.example.greentaxverify;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION_MS = 1500L;

    private TextView tvAppName;
    private TextView tvTagline;
    private ProgressBar progress;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable navigateToRoleSelection = () -> {
        Intent intent = new Intent(SplashActivity.this, RoleSelectionActivity.class);
        startActivity(intent);
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvAppName = findViewById(R.id.tv_app_name);
        tvTagline  = findViewById(R.id.tv_tagline);
        progress   = findViewById(R.id.progress);

        handler.postDelayed(navigateToRoleSelection, SPLASH_DURATION_MS);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(navigateToRoleSelection);
        super.onDestroy();
    }
}

package com.example.greentaxverify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResidentActivity extends AppCompatActivity {

    private MaterialToolbar toolbarResident;
    private TextView tvLocationLabel;
    private TextView tvSocietyName;
    private TextView tvSocietyAddress;
    private TextView tvComplianceLabel;
    private TextView tvComplianceScore;
    private ProgressBar progressCompliance;
    private TextView tvSavingsLabel;
    private TextView tvSavingsAmount;
    private Button btnViewBreakdown;
    private Button btnDownloadHistory;
    private BottomNavigationView bottomNavResident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        toolbarResident    = findViewById(R.id.toolbar_resident);
        tvLocationLabel    = findViewById(R.id.tv_location_label);
        tvSocietyName      = findViewById(R.id.tv_society_name);
        tvSocietyAddress   = findViewById(R.id.tv_society_address);
        tvComplianceLabel  = findViewById(R.id.tv_compliance_label);
        tvComplianceScore  = findViewById(R.id.tv_compliance_score);
        progressCompliance = findViewById(R.id.progress_compliance);
        tvSavingsLabel     = findViewById(R.id.tv_savings_label);
        tvSavingsAmount    = findViewById(R.id.tv_savings_amount);
        btnViewBreakdown   = findViewById(R.id.btn_view_breakdown);
        btnDownloadHistory = findViewById(R.id.btn_download_history);
        bottomNavResident  = findViewById(R.id.bottom_nav_resident);

        setSupportActionBar(toolbarResident);

        btnViewBreakdown.setOnClickListener(v -> {
            // TODO: show tax breakdown
        });

        btnDownloadHistory.setOnClickListener(v -> {
            // TODO: download full compliance history
        });

        bottomNavResident.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_dashboard) {
                // TODO: navigate to dashboard
            } else if (id == R.id.nav_activity) {
                startActivity(new Intent(this, ProofHistoryActivity.class));
            } else if (id == R.id.nav_review) {
                startActivity(new Intent(this, ProofReviewActivity.class));
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            }
            return true;
        });
    }
}

package com.example.greentaxverify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecretaryDashboardActivity extends AppCompatActivity {

    private MaterialToolbar toolbarSecretary;
    private TextView tvWelcomeLabel;
    private TextView tvSocietyName;
    private TextView tvTrustScore;
    private Button btnUploadProof;
    private Button btnViewHistory;
    private Button btnMyProfile;
    private Button btnRebateStatus;
    private TextView tvImpactTitle;
    private TextView tvWasteLabel;
    private TextView tvWasteAmount;
    private BottomNavigationView bottomNavSecretary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_dashboard);

        toolbarSecretary  = findViewById(R.id.toolbar_secretary);
        tvWelcomeLabel    = findViewById(R.id.tv_welcome_label);
        tvSocietyName     = findViewById(R.id.tv_society_name);
        tvTrustScore      = findViewById(R.id.tv_trust_score);
        btnUploadProof    = findViewById(R.id.btn_upload_proof);
        btnViewHistory    = findViewById(R.id.btn_view_history);
        btnMyProfile      = findViewById(R.id.btn_my_profile);
        btnRebateStatus   = findViewById(R.id.btn_rebate_status);
        tvImpactTitle     = findViewById(R.id.tv_impact_title);
        tvWasteLabel      = findViewById(R.id.tv_waste_label);
        tvWasteAmount     = findViewById(R.id.tv_waste_amount);
        bottomNavSecretary = findViewById(R.id.bottom_nav_secretary);

        setSupportActionBar(toolbarSecretary);

        btnUploadProof.setOnClickListener(v ->
                startActivity(new Intent(this, UploadProofActivity.class)));

        btnViewHistory.setOnClickListener(v ->
                startActivity(new Intent(this, ProofHistoryActivity.class)));

        btnMyProfile.setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));

        btnRebateStatus.setOnClickListener(v -> {
            // TODO: show rebate status screen
        });

        bottomNavSecretary.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_dashboard) {
                // already here
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

package com.example.greentaxverify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminDashboardActivity extends AppCompatActivity {

    private MaterialToolbar toolbarAdmin;
    private TextView tvAdminTitle;
    private TextView tvAdminSubtitle;
    private TextView tvLabelTotal;
    private TextView tvTotalSocieties;
    private TextView tvLabelCompliant;
    private TextView tvCompliantCount;
    private TextView tvLabelFlagged;
    private TextView tvFlaggedCount;
    private TextView tvLabelPending;
    private TextView tvPendingCount;
    private TextView tvQueueTitle;
    private Button btnViewAllRecords;
    private RecyclerView rvVerificationQueue;
    private BottomNavigationView bottomNavAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        toolbarAdmin        = findViewById(R.id.toolbar_admin);
        tvAdminTitle        = findViewById(R.id.tv_admin_title);
        tvAdminSubtitle     = findViewById(R.id.tv_admin_subtitle);
        tvLabelTotal        = findViewById(R.id.tv_label_total);
        tvTotalSocieties    = findViewById(R.id.tv_total_societies);
        tvLabelCompliant    = findViewById(R.id.tv_label_compliant);
        tvCompliantCount    = findViewById(R.id.tv_compliant_count);
        tvLabelFlagged      = findViewById(R.id.tv_label_flagged);
        tvFlaggedCount      = findViewById(R.id.tv_flagged_count);
        tvLabelPending      = findViewById(R.id.tv_label_pending);
        tvPendingCount      = findViewById(R.id.tv_pending_count);
        tvQueueTitle        = findViewById(R.id.tv_queue_title);
        btnViewAllRecords   = findViewById(R.id.btn_view_all_records);
        rvVerificationQueue = findViewById(R.id.rv_verification_queue);
        bottomNavAdmin      = findViewById(R.id.bottom_nav_admin);

        setSupportActionBar(toolbarAdmin);

        rvVerificationQueue.setLayoutManager(new LinearLayoutManager(this));
        // TODO: set adapter on rvVerificationQueue

        btnViewAllRecords.setOnClickListener(v -> {
            // TODO: navigate to full records list
        });

        bottomNavAdmin.setOnItemSelectedListener(item -> {
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

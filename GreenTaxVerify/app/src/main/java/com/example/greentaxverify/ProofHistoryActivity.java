package com.example.greentaxverify;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProofHistoryActivity extends AppCompatActivity {

    private MaterialToolbar toolbarHistory;
    private Button btnFilterAll;
    private Button btnFilterVerified;
    private Button btnFilterFlagged;
    private Button btnFilterRejected;
    private RecyclerView rvProofHistory;
    private Button btnAddPhoto;
    private BottomNavigationView bottomNavHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proof_history);

        toolbarHistory     = findViewById(R.id.toolbar_history);
        btnFilterAll       = findViewById(R.id.btn_filter_all);
        btnFilterVerified  = findViewById(R.id.btn_filter_verified);
        btnFilterFlagged   = findViewById(R.id.btn_filter_flagged);
        btnFilterRejected  = findViewById(R.id.btn_filter_rejected);
        rvProofHistory     = findViewById(R.id.rv_proof_history);
        btnAddPhoto        = findViewById(R.id.btn_add_photo);
        bottomNavHistory   = findViewById(R.id.bottom_nav_history);

        setSupportActionBar(toolbarHistory);

        rvProofHistory.setLayoutManager(new LinearLayoutManager(this));
        // TODO: set adapter on rvProofHistory

        btnFilterAll.setOnClickListener(v -> {
            // TODO: show all proof entries
        });

        btnFilterVerified.setOnClickListener(v -> {
            // TODO: filter verified entries
        });

        btnFilterFlagged.setOnClickListener(v -> {
            // TODO: filter flagged entries
        });

        btnFilterRejected.setOnClickListener(v -> {
            // TODO: filter rejected entries
        });

        btnAddPhoto.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, UploadProofActivity.class));
        });

        bottomNavHistory.setOnItemSelectedListener(item -> {
            // TODO: handle bottom nav tab changes
            return true;
        });
    }
}

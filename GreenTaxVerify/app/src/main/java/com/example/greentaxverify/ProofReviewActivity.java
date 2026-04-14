package com.example.greentaxverify;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class ProofReviewActivity extends AppCompatActivity {

    private MaterialToolbar toolbarReview;
    private ImageView ivEvidencePhoto;
    private TextView tvSocietyName;
    private TextView tvUploadedTime;
    private TextView tvGpsCoordinates;
    private TextView tvGreenCover;
    private TextView tvSolarYield;
    private TextView tvWasteMgmt;
    private TextView tvWaterRecycling;
    private TextView tvTrustScore;
    private EditText etReviewerNotes;
    private Button btnReject;
    private Button btnFlag;
    private Button btnApprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proof_review);

        toolbarReview     = findViewById(R.id.toolbar_review);
        ivEvidencePhoto   = findViewById(R.id.iv_evidence_photo);
        tvSocietyName     = findViewById(R.id.tv_society_name);
        tvUploadedTime    = findViewById(R.id.tv_uploaded_time);
        tvGpsCoordinates  = findViewById(R.id.tv_gps_coordinates);
        tvGreenCover      = findViewById(R.id.tv_green_cover);
        tvSolarYield      = findViewById(R.id.tv_solar_yield);
        tvWasteMgmt       = findViewById(R.id.tv_waste_mgmt);
        tvWaterRecycling  = findViewById(R.id.tv_water_recycling);
        tvTrustScore      = findViewById(R.id.tv_trust_score);
        etReviewerNotes   = findViewById(R.id.et_reviewer_notes);
        btnReject         = findViewById(R.id.btn_reject);
        btnFlag           = findViewById(R.id.btn_flag);
        btnApprove        = findViewById(R.id.btn_approve);

        setSupportActionBar(toolbarReview);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnReject.setOnClickListener(v -> {
            // TODO: reject proof and notify submitter
        });

        btnFlag.setOnClickListener(v -> {
            // TODO: flag proof for further inspection
        });

        btnApprove.setOnClickListener(v -> {
            // TODO: approve proof and update compliance score
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

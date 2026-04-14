package com.example.greentaxverify;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    // ── Constants ─────────────────────────────────────────────────────────────
    private static final int REQUEST_CODE_GALLERY_PERMISSION = 101;
    private static final int REQUEST_CODE_PICK_IMAGE         = 102;

    // READ_MEDIA_IMAGES on API 33+, READ_EXTERNAL_STORAGE on older devices
    private static final String GALLERY_PERMISSION =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                    ? Manifest.permission.READ_MEDIA_IMAGES
                    : Manifest.permission.READ_EXTERNAL_STORAGE;

    // ── Views ─────────────────────────────────────────────────────────────────
    private MaterialToolbar      toolbarProfile;
    private CircleImageView      ivProfileAvatar;
    private TextView             tvUserName;
    private TextView             tvUserRole;
    private TextView             tvSocietyName;
    private Button               btnEditProfile;
    private Button               btnSettings;
    private Button               btnTaxCertificates;
    private Button               btnHelpSupport;
    private Button               btnLogout;
    private BottomNavigationView bottomNavProfile;

    // ─────────────────────────────────────────────────────────────────────────

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bindViews();
        setSupportActionBar(toolbarProfile);
        wireListeners();
    }

    // ── View binding ──────────────────────────────────────────────────────────

    private void bindViews() {
        toolbarProfile     = findViewById(R.id.toolbar_profile);
        ivProfileAvatar    = findViewById(R.id.iv_profile_avatar);  // CircleImageView
        tvUserName         = findViewById(R.id.tv_user_name);
        tvUserRole         = findViewById(R.id.tv_user_role);
        tvSocietyName      = findViewById(R.id.tv_society_name);
        btnEditProfile     = findViewById(R.id.btn_edit_profile);
        btnSettings        = findViewById(R.id.btn_settings);
        btnTaxCertificates = findViewById(R.id.btn_tax_certificates);
        btnHelpSupport     = findViewById(R.id.btn_help_support);
        btnLogout          = findViewById(R.id.btn_logout);
        bottomNavProfile   = findViewById(R.id.bottom_nav_profile);
    }

    // ── Click listeners ───────────────────────────────────────────────────────

    private void wireListeners() {

        // Tapping the avatar OR Edit Profile → gallery flow
        ivProfileAvatar.setOnClickListener(v -> checkPermissionAndOpenGallery());
        btnEditProfile.setOnClickListener(v  -> checkPermissionAndOpenGallery());

        btnSettings.setOnClickListener(v -> {
            // TODO: open settings screen
        });

        btnTaxCertificates.setOnClickListener(v -> {
            // TODO: open tax certificates screen
        });

        btnHelpSupport.setOnClickListener(v -> {
            // TODO: open help & support screen
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoleSelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        bottomNavProfile.setOnItemSelectedListener(item -> {
            // TODO: handle bottom nav tab changes
            return true;
        });
    }

    // ── Permission logic (ActivityCompat + ContextCompat) ─────────────────────

    /**
     * Step 1 — entry point.
     * Checks with ContextCompat whether the permission is already granted.
     */
    private void checkPermissionAndOpenGallery() {
        if (ContextCompat.checkSelfPermission(this, GALLERY_PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted → go straight to gallery
            openGallery();
        } else {
            // Permission not yet granted → request it via ActivityCompat
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{GALLERY_PERMISSION},
                    REQUEST_CODE_GALLERY_PERMISSION
            );
        }
    }

    /**
     * Step 2 — system callback after user responds to the permission dialog.
     * Uses ActivityCompat.shouldShowRequestPermissionRationale to handle
     * "Don't ask again" gracefully.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_GALLERY_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // User granted → open gallery
                openGallery();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, GALLERY_PERMISSION)) {
                // User denied but did NOT tap "Don't ask again"
                Toast.makeText(
                        this,
                        "Gallery permission is needed to change your profile photo.",
                        Toast.LENGTH_LONG
                ).show();
            } else {
                // User tapped "Don't ask again" → guide to Settings
                Toast.makeText(
                        this,
                        "Permission permanently denied. Enable it in App Settings.",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    // ── Gallery ───────────────────────────────────────────────────────────────

    /**
     * Step 3 — fires Intent.ACTION_PICK.
     * Result is delivered to onActivityResult.
     */
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    /**
     * Step 4 — receives the picked image URI and displays it in CircleImageView.
     */
    @Override
    @SuppressWarnings("deprecation")   // startActivityForResult still works on all API levels
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE
                && resultCode == RESULT_OK
                && data != null) {

            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                ivProfileAvatar.setImageURI(selectedImageUri);
            }
        }
    }
}

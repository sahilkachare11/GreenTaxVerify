package com.example.greentaxverify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SELECTED_TAB = "selected_tab";
    public static final String EXTRA_VEHICLE_NUMBER = "extra_vehicle_number";

    private BottomNavigationView bottomNavigationView;
    private EditText vehicleInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicleInput = findViewById(R.id.vehicleInput);
        Button verifyButton = findViewById(R.id.verifyBtn);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        verifyButton.setOnClickListener(v -> openResultScreen());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_verify) {
                fragment = new VerifyFragment();
            } else if (itemId == R.id.nav_tax_status) {
                fragment = new TaxStatusFragment();
            } else if (itemId == R.id.nav_payment) {
                fragment = new PaymentFragment();
            } else if (itemId == R.id.nav_profile) {
                fragment = new ProfileFragment();
            } else {
                return false;
            }

            loadFragment(fragment);
            return true;
        });

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_verify);
        } else {
            int selectedTab = savedInstanceState.getInt(KEY_SELECTED_TAB, R.id.nav_verify);
            bottomNavigationView.setSelectedItemId(selectedTab);
        }
    }

    private void openResultScreen() {
        String vehicleNumber = vehicleInput.getText() != null
                ? vehicleInput.getText().toString().trim()
                : "";

        if (TextUtils.isEmpty(vehicleNumber)) {
            vehicleInput.setError(getString(R.string.error_vehicle_required));
            vehicleInput.requestFocus();
            return;
        }

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra(EXTRA_VEHICLE_NUMBER, vehicleNumber);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SELECTED_TAB, bottomNavigationView.getSelectedItemId());
    }
}
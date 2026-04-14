package com.example.greentaxverify;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.resultText);
        String vehicleNumber = getIntent() != null
                ? getIntent().getStringExtra(MainActivity.EXTRA_VEHICLE_NUMBER)
                : "";

        if (vehicleNumber == null) {
            vehicleNumber = "";
        }

        String taxStatus = vehicleNumber.endsWith("5")
                ? getString(R.string.tax_paid)
                : getString(R.string.tax_pending);

        String displayVehicle = vehicleNumber.isEmpty() ? getString(R.string.vehicle_na) : vehicleNumber;
        resultText.setText(getString(R.string.result_text_format, displayVehicle, taxStatus));
    }
}
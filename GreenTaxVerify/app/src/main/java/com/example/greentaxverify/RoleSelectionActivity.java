package com.example.greentaxverify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoleSelectionActivity extends AppCompatActivity {

    private TextView tvRoleTitle;
    private TextView tvRoleSubtitle;
    private Button btnSecretary;
    private Button btnAdmin;
    private Button btnResident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        tvRoleTitle    = findViewById(R.id.tv_role_title);
        tvRoleSubtitle = findViewById(R.id.tv_role_subtitle);
        btnSecretary   = findViewById(R.id.btn_secretary);
        btnAdmin       = findViewById(R.id.btn_admin);
        btnResident    = findViewById(R.id.btn_resident);

        btnSecretary.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("role", "secretary");
            startActivity(intent);
        });

        btnAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("role", "admin");
            startActivity(intent);
        });

        btnResident.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("role", "resident");
            startActivity(intent);
        });
    }
}

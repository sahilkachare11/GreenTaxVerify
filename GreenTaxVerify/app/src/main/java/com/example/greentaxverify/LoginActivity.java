package com.example.greentaxverify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail    = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin   = findViewById(R.id.btn_login);

        String role = getIntent().getStringExtra("role");

        btnLogin.setOnClickListener(v -> {
            if (isInputValid()) {
                navigateByRole(role);
            }
        });
    }

    private boolean isInputValid() {
        String email    = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
        String password = etPassword.getText() != null ? etPassword.getText().toString() : "";

        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_email_required));
            etEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError(getString(R.string.error_password_required));
            etPassword.requestFocus();
            return false;
        }
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        return true;
    }

    private void navigateByRole(String role) {
        Intent intent;
        if ("secretary".equals(role)) {
            intent = new Intent(this, SecretaryDashboardActivity.class);
        } else if ("admin".equals(role)) {
            intent = new Intent(this, AdminDashboardActivity.class);
        } else {
            intent = new Intent(this, ResidentActivity.class);
        }
        startActivity(intent);
        finish();
    }
}

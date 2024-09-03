package com.example.sqlitetutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlitetutorial.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {

    ActivityLoginBinding binding;
    Database database;
    String password;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = new Database(this);

        binding.btnSignLogin.setOnClickListener(v ->
                startActivity(new Intent(Login_Activity.this, Sign_up_Activity.class)));

        login();
    }

    private void login() {
        binding.btnLogin.setOnClickListener(v -> {
            username = binding.edEmailLogin.getText().toString();
            password = binding.edPasswordLogin.getText().toString();

            // Input validation
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login_Activity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isCheck = database.checkUser(password, username);
            if (isCheck) {
                Toast.makeText(Login_Activity.this, "Valid", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login_Activity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(Login_Activity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

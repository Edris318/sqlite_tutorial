package com.example.sqlitetutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlitetutorial.databinding.ActivitySignUpBinding;

public class Sign_up_Activity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        database = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        insertData();

    }

    private void insertData() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = database.insertData(binding.edName.getText().toString(), binding.edEmail.getText().toString()
                        , binding.edPassword.getText().toString());
                if (isInserted) {
                    Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data didn't inserted", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }






}
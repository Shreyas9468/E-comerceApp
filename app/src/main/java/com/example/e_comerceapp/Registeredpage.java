package com.example.e_comerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registeredpage extends AppCompatActivity {
    EditText Email, Password;
    Button login, backtomain;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeredpage);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        backtomain = findViewById(R.id.backtomain);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = Email.getText().toString().trim();
                String userPassword = Password.getText().toString().trim();

                // Check if the entered credentials are valid by calling the isValidCredentials method
                if (MyDbHelper.isValidCredentials(Registeredpage.this, userEmail, userPassword)) {
                    // If the credentials are valid, move to the next activity (NextPageActivity)
                    Intent intent1 = new Intent(Registeredpage.this, NextPageActivity.class);
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(), "Your login Successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Optional: You can finish the Registeredpage activity if you don't want to come back to it upon successful login.
                } else {
                    // If the credentials are not valid, display an error message
                    Toast.makeText(Registeredpage.this, "Invalid email or password. Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}

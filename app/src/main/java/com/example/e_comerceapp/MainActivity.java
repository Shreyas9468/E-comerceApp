package com.example.e_comerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, email, password, passwordConfirm;
    Button register,alreadyHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views by their respective IDs
        name = findViewById(R.id.Name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.confirmpassword);
       alreadyHaveAccount = findViewById(R.id.alreadyhaveanaccount);
        register = findViewById(R.id.register);
        register.setOnClickListener(view -> {
            // Perform validation when the register button is clicked
            if (isValidEmail(email.getText().toString())) {
                if (isValidPassword(password.getText().toString())) {
                    // If both email and password are valid, continue with registration
                    if (password.getText().toString().equals(passwordConfirm.getText().toString())) {
                        performRegistration();
                        Intent intent = new Intent(getApplicationContext(),NextPageActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Display an error message if passwords don't match
                        Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Display an error message if the password is not valid
                    Toast.makeText(this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Display an error message if the email is not valid
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            }
        });

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registeredpage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private void performRegistration() {
        String nameValue = name.getText().toString();
        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();

        // Save user information to the database using MyDbHelper
        MyDbHelper dbHelper = new MyDbHelper(this);
        dbHelper.add_info_table_method(nameValue, emailValue, passwordValue);

        // Display a success message after successful registration
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
    }
}

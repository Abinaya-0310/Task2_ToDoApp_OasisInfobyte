package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signin extends AppCompatActivity {
    EditText emailEditText, usernameEditText, passwordEditText;
    Button signUpButton, loginButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        db = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.emailid);
        usernameEditText = findViewById(R.id.username2);
        passwordEditText = findViewById(R.id.password2);
        signUpButton = findViewById(R.id.button);
        loginButton = findViewById(R.id.button2);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(signin.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = db.addUser(username, password);
                    if (isInserted) {
                        Toast.makeText(signin.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signin.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(signin.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signin.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.visa_hackathon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView registerTextView;
    EditText editTextEmail;
    EditText editTextPassword;
    Button loginButton;

    boolean isCredentialsValid(String email,String password){
        // Check from database whether email is valid or not , as of now returning true.
        return  true;
    }

    public void initRegister(){
        registerTextView = findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(newIntent);
            }
        });
    }
    public void initLogin(){
        loginButton = findViewById(R.id.loginButton);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if( isCredentialsValid(email,password) ) {
                    Intent newIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(newIntent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Email or Password :(", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRegister();
        initLogin();
    }
}
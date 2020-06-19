package com.example.visa_hackathon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    Button registerButton;
    TextView backTextView;
    EditText passwordEditText;
    EditText mobileNumberEditText;
    EditText nameEditText;
    EditText emailEditText;
    private View registrationRoot;
    private TextView passwordStrengthTextView;

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void calculatePasswordStrength(String str) {
        // Now, we need to define a PasswordStrength enum
        // with a calculate static method returning the password strength
        PasswordStrength passwordStrength = PasswordStrength.calculate(str);
        passwordStrengthTextView.setText(passwordStrength.msg);

        //registrationRoot.setBackgroundColor(passwordStrength.color);
    }

    public void init(){
        registerButton = findViewById(R.id.registerButton);
        //Animating Register button
        registerButton.setY(50);
        registerButton.animate().translationYBy(-50).setDuration(1000);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(newIntent);
            }
        });
    }
    public void initBack(){
        backTextView = findViewById(R.id.backTextView);
        //Animating Back Text View
        backTextView.setY(50);
        backTextView.animate().translationYBy(-50).setDuration(1000);

        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(newIntent);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationRoot = findViewById(R.id.registrationRoot);
        passwordStrengthTextView = findViewById(R.id.passwordStrengthTextView);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText = findViewById(R.id.nameEditText);
        mobileNumberEditText = findViewById(R.id.moblieNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);


        //Animation on clicking Registration
        nameEditText.setX(-1000);
        nameEditText.animate().translationXBy(1000).setDuration(1500);

        emailEditText.setX(1000);
        emailEditText.animate().translationXBy(-1000).setDuration(1500);

        mobileNumberEditText.setX(-1000);
        mobileNumberEditText.animate().translationXBy(1000).setDuration(1500);

        passwordEditText.setX(1000);
        passwordEditText.animate().translationXBy(-1000).setDuration(1500);

        passwordStrengthTextView.setX(1000);
        passwordStrengthTextView.animate().translationXBy(-1000).setDuration(1500);


        // now we set Text Watcher on the edit text
        // to update the password strength in real time
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculatePasswordStrength(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        init();
        initBack();
    }
}
package com.example.visa_hackathon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        //Animation
        registerTextView.setY(100);
        registerTextView.animate().translationYBy(-100).setDuration(1000);


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
        //Animation
        editTextEmail.setX(-1000);
        editTextEmail.animate().translationXBy(1000).setDuration(1500);

        editTextPassword.setX(1000);
        editTextPassword.animate().translationXBy(-1000).setDuration(1500);

        loginButton.setY(50);
        loginButton.animate().translationYBy(-50).setDuration(1000);


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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrcoords = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRegister();
        initLogin();
    }
}
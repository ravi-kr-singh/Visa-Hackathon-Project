package com.example.visa_hackathon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    public static boolean isMobileNumberValid(String mobile) {
        int n = mobile.length();
        if(n != 10)
            return false;
        for(int i=0;i<10;i++){
            if(!(mobile.charAt(i) >= '0' && mobile.charAt(i) <= '9'))
                return false;
        }
        return true;
    }
    


    private void calculatePasswordStrength(String str) {
        // Now, we need to define a PasswordStrength enum
        // with a calculate static method returning the password strength
        PasswordStrength passwordStrength = PasswordStrength.calculate(str);
        passwordStrengthTextView.setText(passwordStrength.msg);

        passwordStrengthTextView.setTextColor(passwordStrength.color);
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
package com.example.bible_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatAutoCompleteTextView autoTextView;
    private AppCompatAutoCompleteTextView autoTextViewCustom;
    private android.widget.EditText edtPassword;
    private Button btnSignup;
    private Button btnLogin;
    private Button btnBackSignUp;
    private Button btnBackLogin;
    private Button btnGuide;
    private Button btnQuestion;
    private Button btnTable;
    private AppCompatCheckBox checkbox;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.user);
        btnLogin = (Button) findViewById(R.id.button3) ;
        btnSignup = (Button) findViewById(R.id.button2);
        btnGuide = (Button) findViewById(R.id.button) ;
        btnQuestion = (Button) findViewById(R.id.button5) ;
        btnTable = (Button) findViewById(R.id.button4);
        btnTable.setOnClickListener(this);
        btnGuide.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnQuestion.setOnClickListener(this);
        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("tv"));
        if(TextUtils.isEmpty(tv1.getText().toString())){
            btnQuestion.setEnabled(false);
        }
        if (!TextUtils.isEmpty(tv1.getText().toString())){
            btnLogin.setText("התנתק");
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv1.setText(null);
                    btnQuestion.setEnabled(false);
                    btnLogin.setText("התחברות");
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
            });
        }

    }
    @Override
    public void onClick(View view) {
        if (view == btnSignup){
            Intent intent=new Intent(MainActivity.this,SignUp.class);
            startActivity(intent);
        }
        if (view == btnLogin){
            Intent intent=new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }
        if(view == btnGuide){
            Intent intent=new Intent(MainActivity.this,Guide.class);
            startActivity(intent);
        }
        if(view == btnQuestion){
            Intent intent=new Intent(MainActivity.this,ColorsButtons.class);
            intent.putExtra("user_name", tv1.getText().toString());
            startActivity(intent);
        }
        if(view == btnTable){
            Intent intent=new Intent(MainActivity.this,TableScore.class);
            startActivity(intent);
        }
    }
}

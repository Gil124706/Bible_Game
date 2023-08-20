package com.example.bible_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ColorsButtons extends AppCompatActivity implements View.OnClickListener{
    Button btn1, btn2, btn3, btn4, btn5;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors_buttons);
        btn1 = (Button) findViewById(R.id.buttongreen);
        btn2 = (Button) findViewById(R.id.buttonyellow);
        btn3 = (Button) findViewById(R.id.buttonblue);
        btn4 = (Button) findViewById(R.id.buttonred);
        btn5 = (Button) findViewById(R.id.BackC);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v == btn1){
            Intent intent=new Intent(ColorsButtons.this,Questions.class);
            Intent intent1 = getIntent();
            intent.putExtra("button","green");
            intent.putExtra("user_name", intent1.getStringExtra("user_name").toString());
            startActivity(intent);
        }
        if(v == btn2){
            Intent intent=new Intent(ColorsButtons.this,Questions.class);
            Intent intent1 = getIntent();
            intent.putExtra("button","yellow");
            intent.putExtra("user_name", intent1.getStringExtra("user_name").toString());
            startActivity(intent);
        }
        if(v == btn3){
            Intent intent=new Intent(ColorsButtons.this,Questions.class);
            Intent intent1 = getIntent();
            intent.putExtra("button","blue");
            intent.putExtra("user_name", intent1.getStringExtra("user_name").toString());
            startActivity(intent);
        }
        if(v == btn4){
            Intent intent=new Intent(ColorsButtons.this,Questions.class);
            Intent intent1 = getIntent();
            intent.putExtra("button","red");
            intent.putExtra("user_name", intent1.getStringExtra("user_name").toString());
            startActivity(intent);
        }
        if(v == btn5){
            Intent intent=new Intent(ColorsButtons.this,MainActivity.class);
            startActivity(intent);
        }
    }
}

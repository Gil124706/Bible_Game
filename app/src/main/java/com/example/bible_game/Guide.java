package com.example.bible_game;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Guide extends AppCompatActivity implements View.OnClickListener {
    Button back;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.guide);
        TextView textView = (TextView) findViewById(R.id.textview1);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        back = (Button) findViewById(R.id.BackG);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == back){
            Intent intent=new Intent(Guide.this,MainActivity.class);
            startActivity(intent);
        }
    }
}

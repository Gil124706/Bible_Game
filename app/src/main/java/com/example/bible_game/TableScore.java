package com.example.bible_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TableScore extends AppCompatActivity implements View.OnClickListener {
    Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_table);
        back = (Button) findViewById(R.id.BackT);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == back ){
            Intent intent=new Intent(TableScore.this,MainActivity.class);
            startActivity(intent);
        }
    }
}

package com.example.bible_game;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Question extends AppCompatActivity {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }
    public String getOptionA(){
        return options.get(0);
    }
    public String getOptionB(){
        return options.get(1);
    }
    public String getOptionC(){
        return options.get(2);
    }
    public String getOptionD(){
        return options.get(3);
    }
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}


package com.example.bible_game;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private android.widget.EditText edtPassword;
    private android.widget.EditText edtUserName;
    private Button btnSignup;
    private AppCompatCheckBox checkbox;
    JSONObject fromServer;
    JSONObject jsonRecieved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);
        checkbox = (AppCompatCheckBox)findViewById(R.id.checkbox);
        edtPassword = (EditText)findViewById(R.id.editTextTextPassword);
        edtUserName = (EditText)findViewById(R.id.editTextTextUserName);
        btnSignup = (Button)findViewById(R.id.btnSignUp);
        btnSignup.setOnClickListener(this);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
            }
        });
    }
    private boolean sendData()
    {
        JSONObject jsonToSend = new JSONObject();
        jsonRecieved = new JSONObject();
        try
        {
            jsonToSend.put("user_name", edtUserName.getText().toString());
            jsonToSend.put("password", edtPassword.getText().toString());
            jsonToSend.put("ls", "sign up");
            SocketTask send1 = new SocketTask(jsonToSend);
            fromServer = send1.execute().get();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSignup && !TextUtils.isEmpty(edtUserName.getText().toString()) && !TextUtils.isEmpty(edtPassword.getText().toString())){
            sendData();
            if(fromServer.has("error")){
                edtUserName.setError("There is already user name like this, choose another one");
            }
            else{
                Intent intent=new Intent(SignUp.this,MainActivity.class);
                try {
                    intent.putExtra("tv",fromServer.get("user_name").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }

        }
    }
}

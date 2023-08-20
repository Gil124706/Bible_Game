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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private android.widget.EditText edtPassword;
    private android.widget.EditText edtUserName;
    private Button btnLogin;
    private AppCompatCheckBox checkbox;
    JSONObject fromServer;
    JSONObject jsonRecieved;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        checkbox = (AppCompatCheckBox)findViewById(R.id.checkbox1);
        edtPassword = (EditText)findViewById(R.id.editTextTextPassword);
        edtUserName = (EditText)findViewById(R.id.editTextTextUserName);
        btnLogin = (Button) findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (v == btnLogin && !TextUtils.isEmpty(edtUserName.getText().toString()) && !TextUtils.isEmpty(edtPassword.getText().toString())){
            sendData();
            if(fromServer.has("error")){
                edtUserName.setError("There is not a user name like that");
            }
            else{
                Intent intent=new Intent(Login.this,MainActivity.class);
                try {
                    intent.putExtra("tv",fromServer.get("user_name").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }

        }
        else {
            edtUserName.setError("fill the lines");
        }
    }
    private boolean sendData()
    {
        JSONObject jsonToSend = new JSONObject();
        jsonRecieved = new JSONObject();
        try
        {
            jsonToSend.put("user_name", edtUserName.getText().toString());
            jsonToSend.put("password", edtPassword.getText().toString());
            jsonToSend.put("ls", "login");
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
}

package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mEmailLogin;
    EditText mPasswordLogin;
    Button mLoginButton;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailLogin = (EditText) findViewById(R.id.emaillogin);
        mPasswordLogin= (EditText) findViewById(R.id.passwordlogin);
        mLoginButton  = (Button) findViewById(R.id.Buttonlogin);
        DB = new DBHelper(this);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailLogin.getText().toString();
                String password = mPasswordLogin.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter all feilds", Toast.LENGTH_SHORT).show();
                }else {
                     Boolean checkuserPass = DB.checkEmailPassword(email,password);
                     if(checkuserPass == true){
                         Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(getApplicationContext(),Home.class);
                         startActivity(intent);
                     }else {
                         Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                     }
                }



            }
        });




    }
}
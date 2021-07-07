package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mRegister;
    Button mLoginin;
    Button mmd5check;

    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mRegister = (Button) findViewById(R.id.registerButton);
        mLoginin = (Button) findViewById(R.id.loginButton);
        mmd5check = (Button) findViewById(R.id.viewmd5Pass);
        DB = new DBHelper(this);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if(email.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkemail = DB.checkEmail(email);
                    if(checkemail == false){
                        Boolean insert = DB.insertData(email,password);
                        if(insert  == true){
                            Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }

                    }else {
                        Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mLoginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        mmd5check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),dbView.class);
                startActivity(intent);
            }
        });


    }
}
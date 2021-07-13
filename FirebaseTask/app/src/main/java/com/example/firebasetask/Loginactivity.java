package com.example.firebasetask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginactivity extends AppCompatActivity {


    EditText mLoginEmail,mLoginPassword;
    TextView mRegister;
    Button mLoginbtn;
    ProgressBar mProgressBarLogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        mLoginEmail= (EditText) findViewById(R.id.emaillogin);
        mLoginPassword= (EditText) findViewById(R.id.passwordlogin);
        mLoginbtn = (Button)  findViewById(R.id.Buttonlogin);
        mProgressBarLogin = (ProgressBar)  findViewById(R.id.progressBar2);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mRegister = findViewById(R.id.registerlogin);

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mLoginEmail.getText().toString().trim();
                String password = mLoginPassword.getText().toString().trim();

                if(email.equals("")||password.equals("")){
                    Toast.makeText(Loginactivity.this, "Enter All Fields First ", Toast.LENGTH_SHORT).show();
                }else{
                    mProgressBarLogin.setVisibility(View.VISIBLE);
                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Loginactivity.this, "Succesfully Loggedin", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Home.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(Loginactivity.this, "Not Logged in :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                mProgressBarLogin.setVisibility(View.GONE);


                            }
                        }
                    });
            }}
        });



    }


    public void gotoRegister(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);


    }
}
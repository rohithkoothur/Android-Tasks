package com.example.firebasetask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText mEmail,mPassword,mNumber,mName;
    Button mRegister,mLogin;
    FirebaseAuth mFirebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        mEmail = (EditText) findViewById(R.id.email);
        mName = (EditText) findViewById(R.id.name);
        mNumber = (EditText) findViewById(R.id.number);
        mPassword = (EditText) findViewById(R.id.password);
        mRegister =(Button) findViewById(R.id.registerButton);
        mLogin =(Button) findViewById(R.id.loginButton);


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String number = mNumber.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(email.equals("")||name.equals("")||number.equals("")||password.equals("")){
                    Toast.makeText(MainActivity.this, "Enter All Fields First ", Toast.LENGTH_SHORT).show();
                }else{
                     progressBar.setVisibility(View.VISIBLE);
                     mFirebaseAuth.createUserWithEmailAndPassword(email,password)
                             .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete
                                 (@NonNull  Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(MainActivity.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(getApplicationContext(),Loginactivity.class);
                                 startActivity(intent);
                             }else{
                                 Toast.makeText(MainActivity.this, "Not Registered"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                 progressBar.setVisibility(View.VISIBLE);

                             }

                         }
                     });
                }


            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Loginactivity.class);
                startActivity(intent);
            }
        });



    }
}
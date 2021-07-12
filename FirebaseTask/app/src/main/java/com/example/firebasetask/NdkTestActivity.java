package com.example.firebasetask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

public class NdkTestActivity extends AppCompatActivity {
    public native String getNativeKey1();
    public native String getNativeKey2();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk_test);
        String key1 = new String(Base64.decode(getNativeKey1(),Base64.DEFAULT));

        ((TextView)findViewById(R.id.key)).setText("API Key-->"+key1);
    }
    static {
        System.loadLibrary("keys");
    }
}
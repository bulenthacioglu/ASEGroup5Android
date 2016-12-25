package com.example.bulent.asegroup5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void generateQR(View view) {
        // Do something in response to button
        Intent myIntent = new Intent(MainActivity.this, QR_code.class);
        MainActivity.this.startActivity(myIntent);


    }
}

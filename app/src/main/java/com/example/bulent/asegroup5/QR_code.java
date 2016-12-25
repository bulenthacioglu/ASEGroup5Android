package com.example.bulent.asegroup5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QR_code extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
    }
    public void generateQR(View view) {
        // Do something in response to button
        Intent myIntent = new Intent(QR_code.this, MainActivity.class);
        QR_code.this.startActivity(myIntent);


    }
}

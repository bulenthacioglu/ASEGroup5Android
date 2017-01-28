package com.example.bulent.asegroup5;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import com.appspot.guestbook_148810.studentendpoints.Studentendpoints;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.security.Provider;

public class MainActivity extends AppCompatActivity {
    private EditText imkn;
    private EditText pwd;
    private static Studentendpoints studentEndpointsApiService = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inside your Activity class onCreate method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imkn = (EditText)findViewById(R.id.imkn);
        pwd = (EditText)findViewById(R.id.pwd);
        initService();
    }
    public void generateQR(View view) {
        // Do something in response to button
        Intent myIntent = new Intent(MainActivity.this, QR_code.class);
        MainActivity.this.startActivity(myIntent);
        String imkn_service = imkn.getText().toString();
        new StudentEndpointsAsyncTask(this).execute(imkn_service);

    }

    public void initService() {

        Studentendpoints.Builder builder = new Studentendpoints.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("https://guestbook-148810.appspot.com/_ah/api/studentendpoints/v1/") // 10.0.2.2 is localhost's IP address in Android emulator
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        studentEndpointsApiService = builder.build();
    }

class StudentEndpointsAsyncTask extends AsyncTask<String,Void,String>{
    private Context context;
    public StudentEndpointsAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String imkn = params[0];
        try{
            return studentEndpointsApiService.getStudent(imkn).execute().getName();
        }catch(IOException e){
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(context , result , Toast.LENGTH_LONG).show();
    }


}

}



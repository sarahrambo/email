package com.example.email;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
//

public class MainActivity extends AppCompatActivity {

    EditText subject, body;
    Button submit;
    String user= "capstonemailtest@gmail.com";
    String password = "DbsrXMctjr21";
    String sb, bd, rp, recipient;


    GMailSender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        submit = findViewById(R.id.submit);

        sender = new GMailSender(user, password);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb = subject.getText().toString();
                bd = body.getText().toString();
                recipient = "capstonemailtest@gmail.com";
                rp = recipient;

                new MyAsyncClass().execute();
            }
        });
    }

    class MyAsyncClass extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.show();

        }

        @Override

        protected Void doInBackground(Void... mApi) {
            try {

                // Add subject, Body, your mail Id, and receiver mail Id.
                sender.sendMail(sb, bd, user, rp);
                Log.d("send", "done");
            }
            catch (Exception ex) {
                Log.d("exceptionsending", ex.toString());
            }
            return null;
        }

        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            pDialog.cancel();

            Toast.makeText(MainActivity.this, "Sheet Submitted", Toast.LENGTH_SHORT).show();

        }
    }
}

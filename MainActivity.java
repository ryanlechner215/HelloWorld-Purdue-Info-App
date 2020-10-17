package com.example.purdueioandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getButton = (Button) findViewById(R.id.getButton);
        TextView resultsTextView = (TextView) findViewById(R.id.resultsTextView);

        getButton.setOnClickListener(view -> {
            try {
                final String URL = "http://api.purdue.io/odata/";
                final String ENDPOINT = "Courses";
                final String PARAMS = "?$filter=contains(Title,%20%27Algebra%27)";
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    // Calling the API
                    resultsTextView.setText(PurdueIOTest.callAPI(URL, ENDPOINT, PARAMS));
                }

            } catch (IOException e) {
                final String ERROR = "Invalid API address.";
                e.printStackTrace();
                resultsTextView.setText(ERROR);
            }
        });
    }
}
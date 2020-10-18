package com.example.purdueioandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.os.Bundle;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public void doLogin(View v) {
        String username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();




        Toast.makeText(getApplicationContext(), username + " " + password, Toast.LENGTH_SHORT).show();




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        view.findViewById(R.id.getButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });

        TextView textView = (TextView) findViewById(R.id.textView2);
        Button getButton = (Button) findViewById(R.id.getButton);

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
                    textView.setText(PurdueIOTest.callAPI(URL, ENDPOINT, PARAMS));
                }

            } catch (IOException e) {
                final String ERROR = "Invalid API address.";
                e.printStackTrace();
                textView.setText(ERROR);
            }
        });
    }
}
package com.example.purdueioandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doLogin(View v) {
        String username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a username and password", Toast.LENGTH_SHORT).show();
        } else {
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
            startActivity(new Intent(MainActivity.this, CoursesActivity.class));
        }
    }
}
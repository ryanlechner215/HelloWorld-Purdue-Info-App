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
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CoursesActivity extends AppCompatActivity {

    private static ArrayList<Map<String, Object>> APIArr;
    private static String title = "";
    private static String courseNumber = "";
    private static String credits = "";
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_activity);

        EditText searchText = (EditText) findViewById(R.id.searchText);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        Button prevButton = (Button) findViewById(R.id.prevButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView courseNumberTextView = (TextView) findViewById(R.id.courseNumberTextView);
        TextView creditsTextView = (TextView) findViewById(R.id.creditsTextView);
        TextView indexTextView = (TextView) findViewById(R.id.indexTextView);

        titleTextView.setVisibility(View.INVISIBLE);
        courseNumberTextView.setVisibility(View.INVISIBLE);
        creditsTextView.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        prevButton.setVisibility(View.INVISIBLE);
        indexTextView.setVisibility(View.INVISIBLE);

        searchButton.setOnClickListener(view -> {

            String textToSearch = searchText.getText().toString();

            textToSearch.replaceAll("%", "%25")
                    .replaceAll("\\(", "%28")
                    .replaceAll("\\)", "%29")
                    .replaceAll("/", "%2F")
                    .replaceAll("\"", "%22")
                    .replaceAll("\\\\", "%5C")
                    .replaceAll(",", "%2C")
                    .replaceAll(".","%2E")
                    .replaceAll(" ", "%20")
                    .replaceAll("'", "%27")
                    .replaceAll("=", "%3D")
                    .replaceAll("\\$", "%24")
                    .replaceAll("\\[", "%5B")
                    .replaceAll("]", "%5D")
                    .replaceAll("#", "%23")
                    .replaceAll("<", "%3C")
                    .replaceAll(">", "%3E")
                    .replaceAll("\\+", "%2B");

            String returnString = "";
            try {
                final String URL = "http://api.purdue.io/odata/";
                final String ENDPOINT = "Courses";
                String PARAMS = "?$filter=contains(Title,%20%27" + textToSearch + "%27)";
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    // Calling the API
                    returnString = PurdueIOTest.callAPI(URL, ENDPOINT, PARAMS);
                }

            } catch (IOException e) {
                final String ERROR = "Invalid API address.";
                System.out.println(ERROR);
                e.printStackTrace();
            }


            Map<String, Object> tempMap = new Gson().fromJson(
                    returnString, new TypeToken<HashMap<String, Object>>() {}.getType()
            );

            APIArr = (ArrayList<Map<String, Object>>) tempMap.get("value");

            index = 0;
            updateTexts();
        });

        prevButton.setOnClickListener(view -> {
            if (index > 0) {
                index--;
                updateTexts();
            }
        });

        nextButton.setOnClickListener(view -> {
            if (index < APIArr.size() - 1) {
                index++;
                updateTexts();
            }
        });


    }

    private void updateTexts() {
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView courseNumberTextView = (TextView) findViewById(R.id.courseNumberTextView);
        TextView creditsTextView = (TextView) findViewById(R.id.creditsTextView);
        TextView indexTextView = (TextView) findViewById(R.id.indexTextView);
        Button prevButton = (Button) findViewById(R.id.prevButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        if (APIArr.size() < 1) {
            titleTextView.setVisibility(View.INVISIBLE);
            courseNumberTextView.setVisibility(View.INVISIBLE);
            creditsTextView.setVisibility(View.INVISIBLE);
            prevButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.INVISIBLE);
            indexTextView.setVisibility(View.INVISIBLE);
        } else {
            titleTextView.setVisibility(View.VISIBLE);
            courseNumberTextView.setVisibility(View.VISIBLE);
            creditsTextView.setVisibility(View.VISIBLE);
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
            indexTextView.setVisibility(View.VISIBLE);

            titleTextView.setText("Title: " + (String) APIArr.get(index).get("Title"));
            courseNumberTextView.setText("Course #: " + String.valueOf(APIArr.get(index).get("Number")));
            creditsTextView.setText("Credit Hours: " + String.valueOf(APIArr.get(index).get("CreditHours")));
            indexTextView.setText("Index in results: " + String.valueOf(index + 1)
                    + " out of " + String.valueOf(APIArr.size()));
        }
    }
}
package com.example.finalprojectcst2335;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Get a reference to the TextView for displaying app version
        TextView appVersionTextView = findViewById(R.id.appVersionTextView);

        // Set the app version text using a string resource
        String appVersion = getString(R.string.app_version);
        appVersionTextView.setText(appVersion);
    }
}

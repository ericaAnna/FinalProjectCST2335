package com.example.finalprojectcst2335;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class NewsDetailActivity extends AppCompatActivity {

    private FavoritesDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Get the selected news headline and article URL from the Intent
        Intent intent = getIntent();
        String headline = intent.getStringExtra("headline");
        String url = intent.getStringExtra("url");

        // Set the title TextView to display the news headline
        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(headline);

        // Initialize the database helper
        dbHelper = new FavoritesDbHelper(this);

        // Set the Button click listener to open the article link in a web browser
        Button linkButton = findViewById(R.id.linkButton);
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(url);
            }
        });

        // Set the Button click listener to add the article to favorites
        Button addToFavoritesButton = findViewById(R.id.addToFavoritesButton);
        addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavorites(headline, url);
            }
        });
    }

    // Method to open the article link in a web browser
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void addToFavorites(String headline, String url) {
        // Get a writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object to store the values to be inserted
        ContentValues values = new ContentValues();
        values.put(FavoritesContract.FavoritesEntry.COLUMN_HEADLINE, headline);
        values.put(FavoritesContract.FavoritesEntry.COLUMN_URL, url);

        // Insert the values into the database
        long newRowId = db.insert(FavoritesContract.FavoritesEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            showSnackbar("Article added to favorites");
        } else {
            showSnackbar("Failed to add article to favorites");
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
}

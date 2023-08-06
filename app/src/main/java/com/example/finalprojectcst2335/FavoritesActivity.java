package com.example.finalprojectcst2335;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private FavoritesDbHelper dbHelper;
    private List<Article> favoritesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites1);

        // Initialize the database helper
        dbHelper = new FavoritesDbHelper(this);

        // Get references to the views
        ListView favoritesListView = findViewById(R.id.favoritesListView);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button deleteAllButton = findViewById(R.id.deleteAllButton);

        // Hide the delete all button initially
        deleteAllButton.setVisibility(View.GONE);

        // Show the progress bar while loading the favorites list
        progressBar.setVisibility(View.VISIBLE);

        // Load the list of favorite articles from the database using a background task
        new LoadFavoritesTask(this).execute();

        // Set up the ListView and the delete all button
        favoritesListView.setOnItemClickListener((parent, view, position, id) -> {
            // Handle click on individual favorite article
            // Navigate to NewsDetailActivity to display the full article using WebView
        });

        deleteAllButton.setOnClickListener(v -> {
            // Delete all favorite articles
            dbHelper.deleteAllFavoriteArticles();
            // Update the list and hide the delete all button
            favoritesList.clear();
            adapter.notifyDataSetChanged();
            deleteAllButton.setVisibility(View.GONE);
        });
    }

    private static class LoadFavoritesTask extends AsyncTask<Void, Void, List<String>> {
        private final WeakReference<FavoritesActivity> activityReference;

        LoadFavoritesTask(FavoritesActivity activity) {
            activityReference = new WeakReference<>(activity);
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            FavoritesActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }

            // Load the list of favorite articles from the database
            activity.favoritesList = activity.dbHelper.getAllFavoriteArticles();
            List<String> favoriteHeadlines = new ArrayList<>();
            for (Article article : activity.favoritesList) {
                favoriteHeadlines.add(article.getHeadline());
            }
            return favoriteHeadlines;
        }

        @Override
        protected void onPostExecute(List<String> favoriteHeadlines) {
            FavoritesActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            // Update the UI with the list of favorite article headlines
            ProgressBar progressBar = activity.findViewById(R.id.progressBar);
            ListView favoritesListView = activity.findViewById(R.id.favoritesListView);
            Button deleteAllButton = activity.findViewById(R.id.deleteAllButton);

            progressBar.setVisibility(View.GONE);
            activity.adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, favoriteHeadlines);
            favoritesListView.setAdapter(activity.adapter);

            // Show the delete all button if there are favorite articles
            if (!activity.favoritesList.isEmpty()) {
                deleteAllButton.setVisibility(View.VISIBLE);
            }
        }
    }
}

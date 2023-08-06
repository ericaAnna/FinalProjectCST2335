package com.example.finalprojectcst2335;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String newsURL = "http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml";
    private ArrayAdapter<String> adapter;
    private ProgressBar progressBar;
    private EditText editTextTest;
    private Button buttonTest;

    // SharedPreferences key for saving and retrieving the EditText input text
    private static final String PREF_EDIT_TEXT_INPUT = "pref_edit_text_input";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views by their ids
        ListView listViewNews = findViewById(R.id.listViewNews);
        progressBar = findViewById(R.id.progressBar);
        editTextTest = findViewById(R.id.editTextTest);
        buttonTest = findViewById(R.id.buttonTest);

        // Initialize the newsHeadlines list and adapter
        List<String> newsHeadlines = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsHeadlines);
        listViewNews.setAdapter(adapter);

        // Set the item click listener for the ListView
        listViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected news headline
                String selectedHeadline = newsHeadlines.get(position);

                // Pass the selected news headline and URL to the NewsDetailActivity
                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
                intent.putExtra("headline", selectedHeadline);
                intent.putExtra("url", getArticleUrl(position)); // Implement getArticleUrl() method
                startActivity(intent);
            }
        });

        // Set the click listener for the buttonTest
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextTest.getText().toString();
                if (!inputText.isEmpty()) {
                    // Show the input text in a Toast
                    Toast.makeText(MainActivity.this, "Input Text: " + inputText, Toast.LENGTH_SHORT).show();
                } else {
                    // Show a message if the input text is empty
                    Toast.makeText(MainActivity.this, "Please enter some text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Restore the input text from SharedPreferences
        editTextTest.setText(getSavedTextInput());

        // Fetch the news headlines from the given URL
        new FetchNewsHeadlinesTask().execute();
    }

    // Save the EditText input text to SharedPreferences
    private void saveTextInput(String text) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_EDIT_TEXT_INPUT, text);
        editor.apply();
    }

    // Retrieve the saved EditText input text from SharedPreferences
    private String getSavedTextInput() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString(PREF_EDIT_TEXT_INPUT, "");
    }

    // Implement the getArticleUrl() method to retrieve the URL of the selected article
    private String getArticleUrl(int position) {
        // Implement your logic here to retrieve the URL of the selected article based on the position in the list.
        // You can use the newsHeadlines list to get the necessary data.
        // Replace this with your actual implementation.
        return "https://www.example.com/article" + position;
    }

    private class FetchNewsHeadlinesTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> headlines = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(newsURL).get();
                Elements items = doc.select("item");
                for (Element item : items) {
                    String title = item.select("title").first().text();
                    headlines.add(title);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return headlines;
        }

        @Override
        protected void onPostExecute(List<String> headlines) {
            adapter.clear();
            adapter.addAll(headlines);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_help) {
            showHelpDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showHelpDialog() {
        // Create and show an AlertDialog with instructions here
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help");
        builder.setMessage("This is the help message for the MainActivity. Add your instructions here.");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}

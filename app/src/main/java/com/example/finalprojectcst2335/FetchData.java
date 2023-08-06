package com.example.finalprojectcst2335;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchData extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder result = new StringBuilder();

        for (String rssFeedUrl : urls) {
            try {
                URL url = new URL(rssFeedUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                reader.close();
                inputStream.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String xmlData) {
        // This method is called when the AsyncTask completes.
        // You can pass the xmlData to the listener for further processing.
        if (listener != null) {
            listener.onDataFetched(xmlData);
        }
    }

    public interface OnDataFetchedListener {
        void onDataFetched(String xmlData);
    }

    private OnDataFetchedListener listener;

    public void setOnDataFetchedListener(OnDataFetchedListener listener) {
        this.listener = listener;
    }
}

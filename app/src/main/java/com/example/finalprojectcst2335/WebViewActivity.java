package com.example.finalprojectcst2335;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        // Get the WebView from the layout
        webView = findViewById(R.id.webView);

        // Enable JavaScript (Optional, if needed for the web page)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load the web page
        String url = getIntent().getStringExtra("url");
        if (url != null) {
            webView.loadUrl(url);
        }

        // Set a WebViewClient to handle page navigation inside the WebView
        webView.setWebViewClient(new WebViewClient());
    }
}

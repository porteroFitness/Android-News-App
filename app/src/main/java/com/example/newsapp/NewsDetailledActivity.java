package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetailledActivity extends AppCompatActivity {

    WebView webArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detailled);

        webArticle = findViewById(R.id.wvArticle);

        Intent i = getIntent();
        String url = i.getStringExtra(NewsActivity.CLAVE_URL);

        webArticle.getSettings().setDomStorageEnabled(true);
        webArticle.getSettings().setJavaScriptEnabled(true);
        webArticle.getSettings().setLoadsImagesAutomatically(true);
        webArticle.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webArticle.setWebViewClient(new WebViewClient());
        webArticle.loadUrl(url);

    }
}

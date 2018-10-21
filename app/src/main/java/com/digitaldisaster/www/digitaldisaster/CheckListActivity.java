package com.digitaldisaster.www.digitaldisaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CheckListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        WebView webview=(WebView)findViewById(R.id.websitecheck);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_IF_CONTENT_SCROLLS);
        webview.loadUrl(message);
    }
}

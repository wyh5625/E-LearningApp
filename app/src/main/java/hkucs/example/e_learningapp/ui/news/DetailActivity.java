package hkucs.example.e_learningapp.ui.news;

import androidx.appcompat.app.AppCompatActivity;
import hkucs.example.e_learningapp.R;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {

    WebView mWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String addr = intent.getStringExtra(NewsFragment.NEW_ADDRESS_INFO);
        Log.d("WEB_DEBUG", addr);
        mWeb = findViewById(R.id.web_view);
        mWeb.loadUrl(addr);
    }
}

package com.aib.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.aib.demo.R;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;

import java.io.File;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final WebView webView = findViewById(R.id.webview);
        webView.loadUrl("https://jftest.wom186.com/gducdc_h5/#/battlefieldReport");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ImageUtils.view2Bitmap(webView);
                ImageUtils.save(bitmap, PathUtils.getExternalAppFilesPath() + File.separator + "test.png", Bitmap.CompressFormat.PNG);
            }
        });
    }
}
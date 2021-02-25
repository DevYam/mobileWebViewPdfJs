package com.ncertguruji.pdfjswebview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {

    private WebView mWebview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebview  = new WebView(this);

        //-----Mobile site loading start ----
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setUseWideViewPort(true);

        mWebview.getSettings().setSupportZoom(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setDisplayZoomControls(false);

        mWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebview.setScrollbarFadingEnabled(false);
        //-----Mobile site loading end -----

        //--------enabling cache for offline viewing ----------------//
//        mWebview.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 ); // 5MB
        mWebview.getSettings().setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        mWebview.getSettings().setAllowFileAccess( true );
        mWebview.getSettings().setAppCacheEnabled( true );
//        mWebview.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default
        mWebview.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );

        final Activity activity = this;


        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                mWebview.loadUrl("file:///android_asset/error.html");
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview .loadUrl("http://www.ncertguruji.in");
        setContentView(mWebview );
    }
}
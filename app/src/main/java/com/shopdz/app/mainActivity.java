package com.shopdz.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    private static final String STORE_URL =
            "https://shop-dz.gt.tc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);

        setContentView(
                webView,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        setupWebView();

        webView.loadUrl(STORE_URL);
    }

    private void setupWebView() {

        WebSettings settings = webView.getSettings();

        // JavaScript
        settings.setJavaScriptEnabled(true);

        // Local Storage
        settings.setDomStorageEnabled(true);

        // Database
        settings.setDatabaseEnabled(true);

        // تحسين عرض الموقع على الهاتف
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        // السماح بالملفات والمحتوى
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);

        // Cookies
        CookieManager cookieManager =
                CookieManager.getInstance();

        cookieManager.setAcceptCookie(true);

        cookieManager.setAcceptThirdPartyCookies(
                webView,
                true
        );

        // إبقاء صفحات الموقع داخل التطبيق
        webView.setWebViewClient(
                new WebViewClient()
        );

        // دعم JavaScript dialogs وبعض وظائف الويب
        webView.setWebChromeClient(
                new WebChromeClient()
        );
    }

    @Override
    public void onBackPressed() {

        if (webView != null &&
                webView.canGoBack()) {

            webView.goBack();

        } else {

            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {

        if (webView != null) {

            webView.stopLoading();

            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);

            webView.destroy();

            webView = null;
        }

        super.onDestroy();
    }
}

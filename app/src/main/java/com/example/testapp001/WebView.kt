package com.example.testapp001

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebView : AppCompatActivity() {

//    private val url = "https://google.com"  //http://localhost:8080/index
    private val url = "http://10.202.36.80:8080/index"
    private val TAG = "MainWebView"

    val Base_URL = "https://wuqhn9t75a.execute-api.ap-northeast-2.amazonaws.com/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val pref : SharedPreferences = getSharedPreferences("default", 0)
        val editor = pref.edit()

        val webView : WebView = findViewById(R.id.webView)



        // Get the web view settings instance
        val settings = webView.settings

        // javascript 허용
        settings.javaScriptEnabled = true

        // 웹뷰 캐시 허용
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)

        // 줌 컨트롤 허용
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true

        // 웹뷰 배율
        settings.textZoom = 100

        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true

        // 웹뷰 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
//        settings.mediaPlaybackRequiresUserGesture = false
        //선택적인 설정들
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true
//        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        settings.javaScriptCanOpenWindowsAutomatically = true


        // WebView settings
        webView.fitsSystemWindows = true

        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)

                return false // then it is not handled by default action
            }

        })

        // Load url in a web view
        webView.loadUrl(url)


        /*
            if SDK version is greater of 19 then activate hardware acceleration
            otherwise activate software acceleration
        */
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
    }
}
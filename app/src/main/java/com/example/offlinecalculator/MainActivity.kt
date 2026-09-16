package com.example.offlinecalculator

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var assetLoader: WebViewAssetLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        // 禁止从 file:// 网页访问网络资源
        settings.allowFileAccessFromFileURLs = false
        settings.allowUniversalAccessFromFileURLs = false

        assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        webView.webViewClient = object : WebViewClient() {

            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest): WebResourceResponse? {
                val intercepted = assetLoader.shouldInterceptRequest(request.url)
                if (intercepted != null) {
                    return intercepted
                }
                val scheme = request.url.scheme
                if (scheme == "http" || scheme == "https") {
                    return WebResourceResponse("text/plain", "utf-8", 403, "Forbidden", mapOf(), null)
                }
                return super.shouldInterceptRequest(view, request)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url ?: return true
                val host = url.host ?: ""
                if (host == "appassets.androidplatform.net") return false
                if (url.scheme == "file" && url.path?.startsWith("/android_asset/") == true) return false
                return true
            }
        }

        webView.loadUrl("https://appassets.androidplatform.net/assets/index.html")
    }
}

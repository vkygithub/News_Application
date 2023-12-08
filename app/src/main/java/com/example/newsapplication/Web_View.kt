package com.example.newsapplication

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.task_kotlin.ViewModel.ArticlesListViewModel

class Web_View : AppCompatActivity() {
    var Web_View: WebView? = null
    lateinit var articlesListViewModel: ArticlesListViewModel
    var website: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        Web_View = findViewById(R.id.Web_View)
        articlesListViewModel = ArticlesListViewModel()
        website = intent.getStringExtra("website").toString()
        Web_View!!.webViewClient = WebViewClient()
        Web_View!!.apply { loadUrl(website) }
        Web_View!!.settings.javaScriptEnabled = true
        Web_View!!.settings.setSupportZoom(true)
        Web_View!!.settings.domStorageEnabled = true
        Web_View!!.settings.safeBrowsingEnabled = true


    }
}
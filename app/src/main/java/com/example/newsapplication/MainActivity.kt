package com.example.newsapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsapplication.Adapter.ArticlesListAdapter
import com.example.task_kotlin.Interface.ResponseInterface.ArticlesListResponseInterface
import com.example.task_kotlin.PojoFile.ArticlesListResponse
import com.example.task_kotlin.PojoFile.ErrorBody
import com.example.task_kotlin.ViewModel.ArticlesListViewModel


class MainActivity : AppCompatActivity(), ArticlesListResponseInterface {
    var articlesListViewModel: ArticlesListViewModel? = null
    var articlesListAdapter: ArticlesListAdapter? = null
    var view_news: RecyclerView? = null
    var SearchListText: SearchView? = null
    var Progress_Bar: ProgressBar? = null
    var SwipeRefresh: SwipeRefreshLayout? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_news = findViewById(R.id.ViewArticles)
        SearchListText = findViewById(R.id.SearchListText)
        Progress_Bar = findViewById(R.id.Progress_Bar)
        SwipeRefresh = findViewById(R.id.SwipeRefresh)
        articlesListViewModel = ArticlesListViewModel()
        articlesListViewModel!!.ArticlesListCallEnqueue(this)
        Progress_Bar!!.visibility = ProgressBar.GONE

        SearchListText!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Progress_Bar!!.visibility = ProgressBar.GONE
                articlesListAdapter!!.filter(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        SwipeRefresh!!.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            articlesListViewModel!!.ArticlesListCallEnqueue(this)
            SwipeRefresh!!.isRefreshing = false
        })
        SwipeRefresh!!.isRefreshing = true

    }


    override fun ArticlesListResponseProcess(articlesListResponse: ArticlesListResponse?) {
        if (articlesListResponse != null) {
            if (articlesListResponse.previous == null) {
                Progress_Bar!!.visibility = ProgressBar.GONE
                articlesListAdapter =
                    ArticlesListAdapter(applicationContext, articlesListResponse.results!!);
                view_news!!.setLayoutManager(LinearLayoutManager(this))
                view_news!!.setAdapter(articlesListAdapter!!)

                SwipeRefresh!!.isRefreshing = false
            } else {
                Progress_Bar!!.visibility = ProgressBar.GONE

                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
                SwipeRefresh!!.isRefreshing = false

            }
        }


    }

    override fun onFailure(errorBody: ErrorBody?, statusCode: Int) {
    }

}




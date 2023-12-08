package com.example.newsapplication.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.Web_View
import com.example.task_kotlin.PojoFile.ArticlesListResponse
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.text.DateFormat
import java.text.SimpleDateFormat


class ArticlesListAdapter(
     var context: Context,
    var ArticleList: List<ArticlesListResponse.ArticlesList>
) : RecyclerView.Adapter<ArticlesListAdapter.mine>() {

            var check = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesListAdapter.mine {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.view_news, parent, false)
        return mine(view)
    }


    override fun onBindViewHolder(holder: ArticlesListAdapter.mine, position: Int) {
        val articleimage = ArticleList[position].image_url
        val currentItem = ArticleList[position].summary

        holder.article_title.text = ArticleList.get(position).title.toString()
        holder.article_published.setText(
            changeDateFormatFromAnother(
                "" + ArticleList.get(position).published_at!!.substring(0, 10)
            ) + changeTimeFormatFromAnother(
                "" + ArticleList.get(position).published_at!!.substring(
                    11, ArticleList.get(position).published_at!!.length
                )
            )
        )
        holder.article_source.text = ArticleList.get(position).summary.toString()
        holder.article_source.text = currentItem
        holder.article_url.text = ArticleList.get(position).news_site.toString()
        holder.article_website.text = ArticleList.get(position).url.toString()
holder.itemView.setOnClickListener{
    check =true
    val intent = Intent(context,Web_View::class.java).putExtra("website",ArticleList[position].url.toString())
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}
        check=false

        if (holder.article_source.lineCount > 3) {
            holder.Read_more.visibility = View.VISIBLE
            holder.Read_more.setOnClickListener {
                holder.article_source.maxLines = Int.MAX_VALUE


            }

        } else {
            holder.Read_more.visibility = View.GONE
        }


        Glide.with(context)
            .load(articleimage)
            .into(holder.article_image_view)

    }

    override fun getItemCount(): Int {
        return ArticleList.size
    }

    inner class mine(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var article_title: TextView
        var article_image_view: ImageView
        var article_source: TextView
        var article_url: TextView
        var Read_more: TextView
        var article_published: TextView
        var article_website: TextView


        init {
            article_title = itemView.findViewById(R.id.article_title)
            article_image_view = itemView.findViewById(R.id.article_image_view)
            article_source = itemView.findViewById(R.id.article_source)
            article_url = itemView.findViewById(R.id.article_url)
            Read_more = itemView.findViewById(R.id.Read_more)
            article_published = itemView.findViewById(R.id.article_published)
            article_website = itemView.findViewById(R.id.article_website)
            Read_more.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                }
            }
        }
    }

    fun changeDateFormatFromAnother(date: String?): String? {
        @SuppressLint("SimpleDateFormat") val inputFormat: DateFormat =
            SimpleDateFormat("yyy-MMM-dd")
        @SuppressLint("SimpleDateFormat") val outputFormat: DateFormat =
            SimpleDateFormat("dd-MM-yyyy")
        var resultDate: String? = ""
        try {
            resultDate = outputFormat.format(inputFormat.parse(date))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return resultDate
    }

    fun changeTimeFormatFromAnother(date: String?): String? {
        @SuppressLint("SimpleDateFormat") val inputFormat: DateFormat =
            SimpleDateFormat("HH:mm")
        @SuppressLint("SimpleDateFormat") val outputFormat: DateFormat =
            SimpleDateFormat("h:mm a")
        var resultDate: String? = ""
        try {
            resultDate = outputFormat.format(inputFormat.parse(date))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return resultDate
    }

    fun filter(query: String){
        val filteredList = ArticleList.filter { it.news_site!!.contains(query,ignoreCase = true) }
    this.ArticleList = filteredList;
    notifyDataSetChanged()

    }
}






package com.example.task_kotlin.PojoFile

import com.example.newsapplication.PojoFile.EventsListResponse
import com.example.newsapplication.PojoFile.LaunchesListResponse
import com.fasterxml.jackson.annotation.JsonProperty

class ArticlesListResponse {
    @JsonProperty("count")
    public val count: String? = null

    @JsonProperty("next")
    public val next: String? = null


    @JsonProperty("previous")
    public val previous: String? = null

    @JsonProperty("results")
    public var results: List<ArticlesList>? = null

    data class ArticlesList(

        @JsonProperty("id")
        public var id: String? = null,
        @JsonProperty("title")
        public var title: String? = null,
        @JsonProperty("url")
        public var url: String? = null,
        @JsonProperty("image_url")
        public var image_url: String? = null,
        @JsonProperty("news_site")
        public var news_site: String? = null,
        @JsonProperty("summary")
        public var summary: String? = null,
        @JsonProperty("published_at")
        public var published_at: String? = null,
        @JsonProperty("updated_at")
        public var updated_at: String? = null,
        @JsonProperty("featured")
        public var featured: String? = null,

        @JsonProperty("launches")
        public var launches: List<LaunchesListResponse>? = null,

        @JsonProperty("events")
        public var events: List<EventsListResponse>? = null

    )
}
package com.example.newsapplication.PojoFile

import com.fasterxml.jackson.annotation.JsonProperty

class EventsListResponse {
    @JsonProperty("event_id")
    public var event_id: String? = null

    @JsonProperty("provider")
    public var provider: String? = null
}
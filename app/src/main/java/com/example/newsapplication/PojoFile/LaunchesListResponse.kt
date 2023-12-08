package com.example.newsapplication.PojoFile

import com.fasterxml.jackson.annotation.JsonProperty

class LaunchesListResponse {
    @JsonProperty("launch_id")
    public var launch_id: String? = null

    @JsonProperty("provider")
    public var provider: String? = null

}
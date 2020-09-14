package com.kotlin.gojek.assignment.data.model.response


data class GitHubRepoResponse(
    var id: Long,
    val author: String,
    val name: String,
    val description: String,
    val avatar: String,
    val stars: String,
    val forks: String,
    val language: String

)


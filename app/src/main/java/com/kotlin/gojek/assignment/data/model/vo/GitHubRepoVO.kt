package com.kotlin.gojek.assignment.data.model.vo

data class GitHubRepoVO(
    var id: Long,
    val author: String,
    val name: String,
    val description: String,
    val avatar: String,
    val stars: String,
    val forks: String,
    val language: String?
)
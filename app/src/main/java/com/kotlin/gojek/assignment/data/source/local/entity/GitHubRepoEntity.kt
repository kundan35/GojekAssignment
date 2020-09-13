package com.kotlin.gojek.assignment.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GitHubRepos")
data class GitHubRepoEntity(

    @PrimaryKey
    val id: Long,
    val author: String,
    val name: String,
    val description: String,
    val avatar: String,
    val stars: String,
    val forks: String,
    val language: String
    )
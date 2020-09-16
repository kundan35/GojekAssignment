package com.kotlin.gojek.assignment.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GitHubRepos")
data class GitHubRepoEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var author: String,
    val name: String,
    val description: String,
    val avatar: String,
    val language: String?,
    val forks: String,
    val stars: String
    )
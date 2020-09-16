package com.kotlin.gojek.assignment.dao

import com.kotlin.gojek.assignment.data.source.local.entity.GitHubRepoEntity

object TestUtil {

    fun createGitHubRepo(id: Long) = GitHubRepoEntity(
        id = id,
        author = "",
        name = "",
        description ="",
        avatar = "",
        language =  "",
        forks =  "",
        stars = ""
    )

    fun makeGitHubRepoList(size: Int): MutableList<GitHubRepoEntity> {
        val list = ArrayList<GitHubRepoEntity>(size)
        list.forEach {
            it.author = "GitHubRepoEntity ${list.indexOf(it)}"
            it.id = (list.indexOf(it) + 1).toLong()
            list.add(it)
        }
        return list
    }
}
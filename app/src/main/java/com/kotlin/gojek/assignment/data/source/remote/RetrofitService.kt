package com.kotlin.gojek.assignment.data.source.remote

import com.kotlin.gojek.assignment.data.source.local.entity.GitHubRepoEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    fun getGithubProjectIssues(@Query("since") since: String): Single<List<GitHubRepoEntity>>
}
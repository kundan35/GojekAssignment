package com.kotlin.gojek.assignment.data.source.remote

import com.kotlin.gojek.assignment.data.model.response.GitHubRepoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    fun getGithubProjectIssues(@Query("since") since: String): Single<List<GitHubRepoResponse>>
}
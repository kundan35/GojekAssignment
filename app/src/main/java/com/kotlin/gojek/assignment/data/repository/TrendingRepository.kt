package com.kotlin.gojek.assignment.data.repository

import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import io.reactivex.Single

/**
 * To make an interaction between [TrendingRepositoryImpl] & [TrendingUseCase]
 * */
interface TrendingRepository {
    fun getGitHubRepos(
        isRefresh: Boolean,
        since: String
    ): Single<List<GitHubRepoVO>>
}
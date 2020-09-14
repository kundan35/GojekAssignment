package com.kotlin.gojek.assignment.data

import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import io.reactivex.Single

/**
 * To make an interaction between [TrendingRepositoryImpl] & [TrendingUseCase]
 * */
interface TrendingRepository {
    fun getGitHubRepos(
        since: String
    ): Single<List<GitHubRepoVO>>
}
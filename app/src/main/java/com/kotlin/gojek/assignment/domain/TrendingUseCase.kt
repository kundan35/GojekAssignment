package com.kotlin.gojek.assignment.domain

import com.kotlin.gojek.assignment.data.repository.TrendingRepository
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import com.kotlin.gojek.assignment.domain.usecase.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class TrendingUseCase @Inject constructor(private val trendingRepository: TrendingRepository) :
    SingleUseCase<List<GitHubRepoVO>>() {

    private var isRefresh: Boolean = false
    private var since: String = "daily"
    private var state: String = ""

    fun setIsRefresh(isRefresh: Boolean) {
       this.isRefresh = isRefresh
    }

    override fun buildUseCaseSingle(): Single<List<GitHubRepoVO>> {
        return trendingRepository.getGitHubRepos(isRefresh,since)
    }
}
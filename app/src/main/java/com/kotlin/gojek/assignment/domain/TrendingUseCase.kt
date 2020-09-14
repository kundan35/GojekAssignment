package com.kotlin.gojek.assignment.domain

import com.kotlin.gojek.assignment.data.TrendingRepository
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import com.kotlin.gojek.assignment.domain.usecase.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class TrendingUseCase @Inject constructor(private val trendingRepository: TrendingRepository) :
    SingleUseCase<List<GitHubRepoVO>>() {

    override fun buildUseCaseSingle(): Single<List<GitHubRepoVO>> {
        return trendingRepository.getGitHubRepos("daily")
    }
}
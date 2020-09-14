package com.kotlin.gojek.assignment.data.mapper

import com.kotlin.gojek.assignment.data.model.response.GitHubRepoResponse
import com.kotlin.gojek.assignment.data.source.local.entity.GitHubRepoEntity

object DBGitHubRepoMapper {
    fun from(gitHubRepoResponse: GitHubRepoResponse): GitHubRepoEntity {
        return GitHubRepoEntity(
            gitHubRepoResponse.id,
            gitHubRepoResponse.author,
            gitHubRepoResponse.name,
            gitHubRepoResponse.description,
            gitHubRepoResponse.avatar,
            gitHubRepoResponse.language,
            gitHubRepoResponse.forks,
            gitHubRepoResponse.stars
        )
    }
}
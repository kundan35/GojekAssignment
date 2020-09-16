package com.kotlin.gojek.assignment.data

import android.content.SharedPreferences
import com.kotlin.gojek.assignment.data.model.response.GitHubRepoResponse
import com.kotlin.gojek.assignment.data.repository.TrendingRepositoryImpl
import com.kotlin.gojek.assignment.data.source.AppDatabase
import com.kotlin.gojek.assignment.data.source.remote.RetrofitService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class TrendingRepositoryImplTest {
    @MockK
    internal lateinit var mockService: RetrofitService

    @MockK
    internal lateinit var sharedPreferences: SharedPreferences

    @MockK
    internal lateinit var database: AppDatabase

    private lateinit var cut: TrendingRepositoryImpl
    private val since = "daily"
    private val isRefresh = false
    @MockK
    internal lateinit var githubRepoResponse: Single<List<GitHubRepoResponse>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = TrendingRepositoryImpl(
            sharedPreferences,
            database,
            mockService
        )
    }

    @Test
    fun `getTrendingGitHubRepos fetches GithubRepos and maps to Model`() {
        // given
        coEvery {
            mockService.getTrendingGithubRepos(since)
        } returns githubRepoResponse

        // when
        val result = cut.getGitHubRepos(false, since)

        // then
        assert(result == githubRepoResponse)
    }
}
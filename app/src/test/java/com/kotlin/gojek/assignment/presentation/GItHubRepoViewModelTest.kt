package com.kotlin.gojek.assignment.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.gojek.assignment.RxImmediateSchedulerRule
import com.kotlin.gojek.assignment.domain.TrendingUseCase
import com.kotlin.gojek.assignment.presentation.trending.GitHubReposViewModel
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class GItHubRepoViewModelTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @MockK
    internal lateinit var mockTrendingUseCase: TrendingUseCase
    private lateinit var cut: GitHubReposViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = GitHubReposViewModel(mockTrendingUseCase)
    }

    @Test
    fun `init`() {
        assert(cut.isLoad.value == false)
    }

    @Test
    fun `execute getGitHubRepo`() {

        cut.getGitHubRepo(false)

        // then
        coVerify {
            mockTrendingUseCase.execute(
                onSuccess = {
                    cut.isLoad.value = true
                    cut.githubRepoLiveData.value = it
                },
                onError = {
                    cut.isLoad.value = true
                    cut.errMsgLiveData.value = it.localizedMessage.toString()
                }
            )

        }

    }
}
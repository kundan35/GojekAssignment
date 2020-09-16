package com.kotlin.gojek.assignment.domain

import com.kotlin.gojek.assignment.data.repository.TrendingRepositoryImpl
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class TrendingUseCaseTest {
    @MockK
    internal lateinit var mockTrendingRepository: TrendingRepositoryImpl

    private lateinit var cut: TrendingUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = TrendingUseCase(mockTrendingRepository)
    }

    @Test
    fun `buildUseCaseSingle`() {
        cut.setIsRefresh(false)

        val GitHubRepoVOList = listOf(
            GitHubRepoVO(1, "title1", "/backdrop1", "Overview1", "release date1", "2", "3", "5")
        )
        val mock =
            Mockito.`when`(cut.buildUseCaseSingle()).thenReturn(Single.just(GitHubRepoVOList))
        assert(mock.getMock())


    }
}
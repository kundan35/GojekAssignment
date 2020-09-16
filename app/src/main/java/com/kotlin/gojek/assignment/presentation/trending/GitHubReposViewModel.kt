package com.kotlin.gojek.assignment.presentation.trending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import com.kotlin.gojek.assignment.domain.TrendingUseCase
import javax.inject.Inject

class GitHubReposViewModel @Inject constructor(private val trendingUseCase: TrendingUseCase) :
    ViewModel() {
    private val TAG = GitHubReposViewModel::class.java.simpleName
    val isLoad = MutableLiveData<Boolean>()
    val githubRepoLiveData = MutableLiveData<List<GitHubRepoVO>>()
    val errMsgLiveData = MutableLiveData<String>()

    init {
        isLoad.value = false
    }

    fun getGitHubRepo(isRefresh: Boolean) {
        trendingUseCase.setIsRefresh(isRefresh)
        trendingUseCase.execute(
            onSuccess = {
                isLoad.value = true
                githubRepoLiveData.value = it
            },
            onError = {
                isLoad.value = true
                errMsgLiveData.value = it.localizedMessage.toString()
                it.printStackTrace()

            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        trendingUseCase.dispose();
    }
}
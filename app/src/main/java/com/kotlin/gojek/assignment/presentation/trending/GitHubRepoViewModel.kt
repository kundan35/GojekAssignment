package com.kotlin.gojek.assignment.presentation.trending

import androidx.lifecycle.MutableLiveData
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO

/**A helper class for the UI controller that is responsible for
 * preparing data for [GitHubRepoViewModel] as the UI
 *
 * */
class GitHubRepoViewModel(val gitHubRepoVO: GitHubRepoVO) {

    private val TAG = GitHubRepoViewModel::class.java.simpleName
    private val githubRepoData = MutableLiveData<GitHubRepoVO>()

    init {
        githubRepoData.value = gitHubRepoVO
    }

}
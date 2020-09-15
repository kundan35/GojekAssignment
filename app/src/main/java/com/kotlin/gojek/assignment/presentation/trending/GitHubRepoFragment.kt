package com.kotlin.gojek.assignment.presentation.trending

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.gojek.assignment.R
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import com.kotlin.gojek.assignment.databinding.FragmentGithubRepoBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GitHubRepoFragment : DaggerFragment(), OnGitHubRepoAdapterListener {
    private lateinit var fragmentGithubRepoBinding: FragmentGithubRepoBinding
    private var adapter: GitHubRepoAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: GitHubReposViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(GitHubReposViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = GitHubRepoAdapter(this)
        viewModel.getGitHubRepo(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentGithubRepoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_github_repo, container, false)
        fragmentGithubRepoBinding.githubReposViewModel = viewModel
        fragmentGithubRepoBinding.githubRepoRecyclerView.adapter = adapter
        //** Set the colors of the Pull To Refresh View
        activity?.baseContext?.let {
            ContextCompat.getColor(
                it, R.color.colorPrimary)
        }?.let {
            fragmentGithubRepoBinding.itemsSwipeToRefreshLayout.setProgressBackgroundColorSchemeColor(
                it
            )
        }
        activity?.baseContext?.let {
            ContextCompat.getColor(
                it, R.color.colorBlack)
        }?.let {

            fragmentGithubRepoBinding.itemsSwipeToRefreshLayout.setColorSchemeColors(
                it
            )
        }


        fragmentGithubRepoBinding.itemsSwipeToRefreshLayout.setOnRefreshListener {

            fragmentGithubRepoBinding.itemsSwipeToRefreshLayout.isRefreshing = true
            viewModel.getGitHubRepo(true)
        }
        initObserver()
        return fragmentGithubRepoBinding.root
    }

    private fun initObserver() {

        viewModel.isLoad.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let { visibility ->
                if (visibility) {

                    fragmentGithubRepoBinding.noInternetLayout.noInternetConstraintLayout.visibility = View.GONE
                    fragmentGithubRepoBinding.shimmerViewContainer.stopShimmerAnimation()
                    fragmentGithubRepoBinding.shimmerViewContainer.visibility=View.GONE
                    fragmentGithubRepoBinding.itemsSwipeToRefreshLayout.isRefreshing = false
                } else {

                    fragmentGithubRepoBinding.shimmerViewContainer.duration = 800
                    fragmentGithubRepoBinding.shimmerViewContainer.startShimmerAnimation()
                    fragmentGithubRepoBinding.shimmerViewContainer.visibility=View.VISIBLE

                }
            }
        })

        viewModel.githubRepoLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })

        viewModel.errMsgLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            fragmentGithubRepoBinding.noInternetLayout.noInternetConstraintLayout.visibility = View.VISIBLE
        })

    }

    private fun initRecyclerView(gitHubRepoVOList: List<GitHubRepoVO>) {
        adapter?.addData(gitHubRepoVOList)
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
    }

}
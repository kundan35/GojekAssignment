package com.kotlin.gojek.assignment.presentation.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.gojek.assignment.R
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import com.kotlin.gojek.assignment.databinding.HolderGithubRepoBinding
import com.kotlin.gojek.assignment.loadImage
import com.kotlin.gojek.assignment.presentation.trending.GitHubRepoAdapter.GitHubRepoViewHolder
import java.util.*

/**
 * This class is responsible for converting each data entry [GitHubRepoVO]
 * into [GitHubRepoViewHolder] that can then be added to the AdapterView.
 *
 */
internal class GitHubRepoAdapter(val mListener: OnGitHubRepoAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = GitHubRepoAdapter::class.java.name
    private val githubReposList: MutableList<GitHubRepoVO> = ArrayList()


    /**
     * This method is called right when adapter is created &
     * is used to initialize ViewHolders
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderOpenIssueBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_github_repo, parent, false
        )
        return GitHubRepoViewHolder(holderOpenIssueBinding)
    }

    /** It is called for each ViewHolder to bind it to the adapter &
     * This is where we pass data to ViewHolder
     * */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GitHubRepoViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): GitHubRepoVO {
        return githubReposList[position]
    }

    /**
     * This method returns the size of collection that contains the items we want to display
     * */
    override fun getItemCount(): Int {
        return githubReposList.size
    }

    fun addData(list: List<GitHubRepoVO>) {
        this.githubReposList.clear()
        this.githubReposList.addAll(list)
        notifyDataSetChanged()
    }


    inner class GitHubRepoViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {


        fun onBind(gitHubRepoVO: GitHubRepoVO) {
            val holderGithubRepoBinding = dataBinding as HolderGithubRepoBinding
            val githubRepoViewModel = GitHubRepoViewModel(gitHubRepoVO)
            holderGithubRepoBinding.gitHubRepoViewModel = githubRepoViewModel
            gitHubRepoVO.avatar?.let {
                holderGithubRepoBinding.profileImageView.loadImage(
                     gitHubRepoVO.avatar
                )
            }
            itemView.setOnClickListener {

            }

        }
    }

}

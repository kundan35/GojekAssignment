package com.kotlin.gojek.assignment.presentation.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.gojek.assignment.R
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import com.kotlin.gojek.assignment.databinding.HolderGithubRepoBinding
import com.kotlin.gojek.assignment.loadImage
import com.kotlin.gojek.assignment.presentation.custom.ExpandableLayout
import com.kotlin.gojek.assignment.presentation.trending.GitHubRepoAdapter.GitHubRepoViewHolder
import kotlinx.android.synthetic.main.holder_github_repo.view.*
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

    // Save the expanded row position
    private val expandedPositionSet: HashSet<Int> = HashSet()
    private var selectedPost: Int = -1

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
            // Expand when you click on cell
            itemView.expand_layout.setOnExpandListener(object :
                ExpandableLayout.OnExpandListener {
                override fun onExpand(expanded: Boolean) {
                    if (expandedPositionSet.contains(position)) {
                        expandedPositionSet.remove(position)
                    } else {
                        if (selectedPost != -1) {
                            expandedPositionSet.remove(selectedPost)
                        }
                        selectedPost = position
                        expandedPositionSet.add(position)
                    }
                    itemView.expand_layout.setExpand(expandedPositionSet.contains(position))
                    if (expanded) {
                        itemView.language_text_view.visibility = View.VISIBLE
                        itemView.desc_text_view.visibility = View.VISIBLE
                        itemView.language_image_view.visibility = View.VISIBLE
                        itemView.stars_image_view.visibility = View.VISIBLE
                        itemView.stars_text_view.visibility=View.VISIBLE
                        itemView.fork_image_view.visibility=View.VISIBLE
                        itemView.forks_text_view.visibility=View.VISIBLE
                    } else {
                        itemView.language_text_view.visibility = View.GONE
                        itemView.desc_text_view.visibility = View.GONE
                        itemView.language_image_view.visibility = View.GONE
                        itemView.stars_image_view.visibility = View.GONE
                        itemView.stars_text_view.visibility=View.GONE
                        itemView.fork_image_view.visibility=View.GONE
                        itemView.forks_text_view.visibility=View.GONE
                    }
                }
            })


        }
    }

}

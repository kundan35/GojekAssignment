package com.kotlin.gojek.assignment.data

import android.content.SharedPreferences
import com.kotlin.gojek.assignment.data.mapper.DBGitHubRepoMapper
import com.kotlin.gojek.assignment.data.model.response.GitHubRepoResponse
import com.kotlin.gojek.assignment.data.model.vo.GitHubRepoVO
import com.kotlin.gojek.assignment.data.source.AppDatabase
import com.kotlin.gojek.assignment.data.source.local.dao.GitHubRepoDao
import com.kotlin.gojek.assignment.data.source.local.entity.GitHubRepoEntity
import com.kotlin.gojek.assignment.data.source.remote.RetrofitService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class TrendingRepositoryImpl(
    private val sharedPreference: SharedPreferences,
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
) : TrendingRepository {
    override fun getGitHubRepos(
        since: String
    ): Single<List<GitHubRepoVO>> {
        val gitHubReposEntityList: MutableList<GitHubRepoEntity> =
            database.githubRepoDao.loadAll()
        return if (!gitHubReposEntityList.isNullOrEmpty() && Util.dateGreaterThanExpireTime(
                sharedPreference
            )
        ) {
            getGitHubReposFromLocalSource()
        } else {
            val singleGitHubRepoResponse: Single<List<GitHubRepoResponse>> =
                retrofitService.getGithubProjectIssues(since);
            return execute(singleGitHubRepoResponse)
        }

    }


    private fun execute(
        singleListGitHubRepoResponse: Single<List<GitHubRepoResponse>>
    ): Single<List<GitHubRepoVO>> {
        return singleListGitHubRepoResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess() { githubRepoResponseList ->
                run {
                    insertGitHubProjectIssues(githubRepoResponseList)
                }

            }.flatMap { getGitHubReposFromLocalSource() }
    }

    private fun insertGitHubProjectIssues(
        githubRepoResponseList: List<GitHubRepoResponse>
    ) {
        val iterator = githubRepoResponseList.listIterator()
        val githubRepoDao: GitHubRepoDao = database.githubRepoDao
        for (item in iterator) {
            val githubRepoEntity: GitHubRepoEntity = DBGitHubRepoMapper.from(item)
            githubRepoDao.insert(githubRepoEntity)
        }
        sharedPreference.edit().putLong("lastDw", Calendar.getInstance().time.time).apply()

    }

    private fun getGitHubReposFromLocalSource(

    ): Single<List<GitHubRepoVO>> {

        val githubReposEntityList: MutableList<GitHubRepoEntity> =
            database.githubRepoDao.loadAll()
        val iterator = githubReposEntityList.listIterator()
        val githubReposVOList: MutableList<GitHubRepoVO> = mutableListOf()
        for (item in iterator) {
            val githubRepoVO =
                GitHubRepoVO(
                    item.id,
                    item.author,
                    item.name,
                    item.avatar,
                    item.forks,
                    item.language,
                    item.description,
                    item.stars
                )
            githubReposVOList.add(githubRepoVO)
        }

        return Single.just(githubReposVOList)
    }
}
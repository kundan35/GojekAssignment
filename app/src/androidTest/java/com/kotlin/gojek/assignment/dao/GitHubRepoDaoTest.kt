package com.kotlin.gojek.assignment.dao

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kotlin.gojek.assignment.data.source.AppDatabase
import com.kotlin.gojek.assignment.data.source.local.entity.GitHubRepoEntity
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GitHubRepoDaoTest {
    private lateinit var mDatabase: AppDatabase

    @Before
    fun createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getTargetContext(),
            AppDatabase::class.java
        )
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun isGitHubRepoEmpty() {
        Assert.assertEquals(0, mDatabase.githubRepoDao.loadAll().size)
    }


    @Test
    @Throws(Exception::class)
    fun insertGitHubRepo() {
        val gitHubRepoEntity: GitHubRepoEntity = TestUtil.createGitHubRepo(1)
        val insertedGitHubRepoEntity = mDatabase.githubRepoDao.insert(gitHubRepoEntity)
        Assert.assertNotNull(insertedGitHubRepoEntity)
    }


    @Test
    @Throws(Exception::class)
    fun retrievesGitHubRepos() {
        val gitHubRepoList = TestUtil.makeGitHubRepoList(5)
        gitHubRepoList.forEach {
            mDatabase.githubRepoDao.insert(it)
        }

        val loadedGitHubRepos = mDatabase.githubRepoDao.loadAll()
        assertEquals(gitHubRepoList, loadedGitHubRepos)
    }


    @Test
    @Throws(Exception::class)
    fun deleteAll() {
        mDatabase.githubRepoDao.deleteAll()
        val loadedAllGitHubRepos = mDatabase.githubRepoDao.loadAll()
        assert(loadedAllGitHubRepos.isEmpty())
    }

}
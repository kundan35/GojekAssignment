package com.kotlin.gojek.assignment.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.kotlin.gojek.assignment.data.source.local.entity.GitHubRepoEntity

@Dao
interface GitHubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gitHubRepoEntity: GitHubRepoEntity): Long
}
package com.kotlin.gojek.assignment.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.gojek.assignment.data.source.local.dao.GitHubRepoDao
import com.kotlin.gojek.assignment.data.source.local.entity.GitHubRepoEntity

@Database(entities = [GitHubRepoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val githubRepoDao: GitHubRepoDao

    companion object {
        const val DB_NAME = "githubRepoDatabase.db"
    }
}
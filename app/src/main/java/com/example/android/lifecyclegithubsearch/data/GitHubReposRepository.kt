package com.example.android.lifecyclegithubsearch.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GitHubReposRepository(
    private val service: GitHubService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadRepositoriesSearch(query: String): Result<List<GitHubRepo>> =
        withContext(ioDispatcher) {
            try {
                val results = service.searchRepositories(query)
                Result.success(results.items)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}
package com.example.android.lifecyclegithubsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.lifecyclegithubsearch.data.GitHubRepo
import com.example.android.lifecyclegithubsearch.data.GitHubReposRepository
import com.example.android.lifecyclegithubsearch.data.GitHubService
import kotlinx.coroutines.launch

class GitHubSearchViewModel : ViewModel() {
    private val repository = GitHubReposRepository(GitHubService.create())
    private val _searchResults = MutableLiveData<List<GitHubRepo>?>(null)
    val searchResults: LiveData<List<GitHubRepo>?> = _searchResults

    fun loadSearchResults(query: String) {
        viewModelScope.launch {
            val result = repository.loadRepositoriesSearch(query)
            _searchResults.value = result.getOrNull()
        }
    }
}
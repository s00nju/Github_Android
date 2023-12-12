package com.example.github.repoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepoViewModel : ViewModel() {
    var currentPage = MutableLiveData<Int>().apply { value = 1 }
}
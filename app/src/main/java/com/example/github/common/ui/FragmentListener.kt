package com.example.github.common.ui

import com.example.github.repoList.ui.RepoListFragment

interface FragmentListener {
    fun changeFragment(fragment:BaseFragment<*>)
}
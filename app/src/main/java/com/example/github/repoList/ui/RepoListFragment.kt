package com.example.github.repoList.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.example.github.common.ui.BaseFragment
import com.example.github.databinding.FragmentRepoListBinding
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.repoList.adapter.RepoListAdapter
import com.example.github.repoList.api.Repo
import com.example.github.repoList.repository.repoApi
import com.example.github.search.ui.SearchFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoListFragment() : BaseFragment<FragmentRepoListBinding>() {

    private val repoListAdapter: RepoListAdapter by lazy { RepoListAdapter() }
    private var currentPage = 1

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRepoListBinding {
        return FragmentRepoListBinding.inflate(inflater, container, false)
    }

    override fun initFragment() {
        initRepoListRecyclerView()
        callRepoList()
        initBtn()
        onBackPressed()
    }

    private fun initBtn(){
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            callRepoList()
        }

        binding.nextPageBtn.setOnClickListener {
            currentPage++
            callRepoList { repos ->
                if (repos.isEmpty()) {
                    // 반환된 목록이 비어있다면 페이지 넘어가지 않도록 처리
                    currentPage--
                    callRepoList()
                    Toast.makeText(context, "더 이상 Repository 목록이 없습니다.", Toast.LENGTH_SHORT).show()
                }
                binding.pageNum.text = currentPage.toString()
            }
        }


        binding.prevPageBtn.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                callRepoList()
                binding.pageNum.text = currentPage.toString()
            }
        }
    }

    private fun initRepoListRecyclerView(){
        binding.repoList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.repoList.adapter = repoListAdapter

        // DividerItemDecoration 생성 및 추가
        val dividerItemDecoration = DividerItemDecoration(binding.repoList.context,
            DividerItemDecoration.VERTICAL)
        binding.repoList.addItemDecoration(dividerItemDecoration)
    }

    private fun callRepoList(onComplete: (List<Repo>) -> Unit = {}) {
        val reposUrl = arguments?.getString("reposUrl")
        if (reposUrl != null) {
            repoApi.getRepoListResult(reposUrl, currentPage)
                .enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        val repos = response.body().orEmpty()
                        val filteredRepos = when (binding.radioGroup.checkedRadioButtonId) {
                            R.id.allBtn -> repos // 모두 표시
                            R.id.publicBtn -> repos.filter { !it.isPrivate } // private이 false인 경우만 표시
                            R.id.privateBtn -> repos.filter { it.isPrivate } // private이 true인 경우만 표시
                            else -> repos
                        }
                        repoListAdapter.setList(filteredRepos)
                        onComplete(filteredRepos)
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                        Log.d(TAG, "callPersonList onFailure", t)
                    }
                }
                )
        }
    }

//    private fun callRepoList() {
//        val reposUrl = arguments?.getString("reposUrl")
//        if (reposUrl != null) {
//            repoApi.getRepoListResult(reposUrl)
//                .enqueue(object : Callback<List<Repo>> {
//                    override fun onResponse(
//                        call: Call<List<Repo>>,
//                        response: Response<List<Repo>>
//                    ) {
//                        val repos = response.body().orEmpty()
//                        repoListAdapter.setList(repos)
//                    }
//
//                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//                        Log.d(TAG, "callPersonList onFailure", t)
//                    }
//
//                })
//        }
//    }
    override fun onBackPressed() {
        binding.backSearchBtn.setOnClickListener{
            listener?.changeFragment(SearchFragment())
        }
    }

    companion object {
        fun newInstance(reposUrl: String): RepoListFragment {
            val fragment = RepoListFragment()
            val args = Bundle()
            args.putString("reposUrl", reposUrl)
            fragment.arguments = args
            return fragment
        }
    }
}
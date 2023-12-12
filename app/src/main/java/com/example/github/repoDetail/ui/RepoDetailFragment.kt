package com.example.github.repoDetail.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.github.common.ui.BaseFragment
import com.example.github.databinding.FragmentRepoDetailBinding
import com.example.github.databinding.FragmentRepoListBinding
import com.example.github.repoDetail.repository.repoDetailApi
import com.example.github.repoList.api.Repo
import com.example.github.repoList.ui.RepoListFragment
import com.example.github.search.ui.SearchFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoDetailFragment : BaseFragment<FragmentRepoDetailBinding>() {

    private var repo: Repo? = null

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRepoDetailBinding {
        return FragmentRepoDetailBinding.inflate(inflater, container, false)
    }

    override fun initFragment() {
        callRepoDetail()
        onBackPressed()
        binding.repoUrl.setOnClickListener { openWebView() }
    }

    private fun callRepoDetail() {
        val url = arguments?.getString("url") ?: return
        repoDetailApi.getRepoDetailResult(url).enqueue(object : Callback<Repo> {
            override fun onResponse(call: Call<Repo>, response: Response<Repo>) {
                response.body()?.let { repo ->
                    this@RepoDetailFragment.repo = repo
                    binding.repoId.text = repo.id.toString()
                    binding.repoFullName.text = repo.fullName

                    if(repo.description == null){
                        binding.repoDescription.text = "No description provided"
                    } else {
                        binding.repoDescription.text = repo.description
                    }

                    binding.repoUrl.text = repo.url
                }
            }

            override fun onFailure(call: Call<Repo>, t: Throwable) {
                Log.d(TAG, "callRepoDetail onFailure", t)
            }
        })
    }

    override fun onBackPressed() {
        binding.backListBtn.setOnClickListener{
            listener?.popFragment()
        }
    }

    private fun openWebView() {
        val url = repo?.html_url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}
package com.example.github.search.ui

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.github.common.ui.BaseFragment
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.repoList.ui.RepoListFragment
import com.example.github.search.api.SearchResponse
import com.example.github.search.repository.searchApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initFragment() {
        binding.searchBtn.setOnClickListener{
            val searchId = binding.idInput.text.toString()

            val call = searchApi.getSearchResult(searchId)
            call.enqueue(object: Callback<SearchResponse> {
//                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
//                    if (response.isSuccessful) {
//                        val user = response.body()?.items?.firstOrNull()
//                        if (user != null) {
//                            // 검색한 회원이 존재하면 처리
//                            Toast.makeText(context, "${user.login}이(가) 검색되었습니다.", Toast.LENGTH_SHORT).show()
//                            val repoListFragment = RepoListFragment.newInstance(user.reposUrl)
//                            Log.d("checkInstance", "${repoListFragment}")
//                            listener?.changeFragment(repoListFragment)
//                        } else {
//                            showPopup("검색한 회원이 존재하지 않습니다.")
//                        }
//                    } else {
//                        // API 호출 자체가 실패한 경우
//                        showPopup("회원 검색에 실패했습니다.")
//                    }
//                }

                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (response.isSuccessful) {
                        val searchResponse = response.body()
                        if (searchResponse != null) {
                            // 검색한 회원이 존재하면 처리
                            Toast.makeText(context, "${searchResponse.login}이(가) 검색되었습니다.", Toast.LENGTH_SHORT).show()
                            val repoListFragment = RepoListFragment.newInstance(searchResponse.reposUrl)
                            listener?.changeFragment(repoListFragment)
                        } else {
                            showPopup("회원 정보를 가져올 수 없습니다.")
                        }
                    } else if (response.code() == 404) {
                        // 사용자를 찾지 못한 경우
                        showPopup("검색한 회원이 존재하지 않습니다.")
                    } else {
                        // API 호출 자체가 실패한 경우
                        showPopup("회원 검색에 실패했습니다.")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    // 통신 실패한 경우
                    showPopup("통신에 실패했습니다. 네트워크 상태를 확인해주세요.")
                }
            })
        }
    }

    private fun showPopup(message: String) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton("확인", null)
            .setCancelable(false)
            .show()
    }

    override fun onBackPressed() {

    }

}
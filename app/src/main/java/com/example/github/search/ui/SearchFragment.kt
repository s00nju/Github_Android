package com.example.github.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.github.R
import com.example.github.common.ui.BaseFragment
import com.example.github.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }





    override fun onBackPressed() {

    }





}
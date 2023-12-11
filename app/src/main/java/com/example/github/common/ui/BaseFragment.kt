package com.example.github.common.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.github.repoList.adapter.RepoListAdapter

abstract class BaseFragment<T: ViewBinding>: Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    protected var listener: FragmentListener? = null

    private var callback: OnBackPressedCallback? = null

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Activity 리스너 초기화
        if (context is FragmentListener) { listener = context }

        // Activity onBackPressedCallback 초기화
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        callback?.let { activity?.onBackPressedDispatcher?.addCallback(this, it) }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    abstract fun initFragment()
    abstract fun onBackPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
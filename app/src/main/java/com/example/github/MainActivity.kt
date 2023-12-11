package com.example.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.github.common.ui.BaseFragment
import com.example.github.common.ui.FragmentListener
import com.example.github.databinding.ActivityMainBinding
import com.example.github.search.ui.SearchFragment

class MainActivity : AppCompatActivity(), FragmentListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initFragmentContainer()

    }

    private fun initFragmentContainer(){
        supportFragmentManager.beginTransaction().replace(binding.frameContainer.id, SearchFragment()).commit()
    }

    override fun changeFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().replace(binding.frameContainer.id, fragment).commit()
    }
}
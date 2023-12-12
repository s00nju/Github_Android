package com.example.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
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
        // 일반적인 경우
        //supportFragmentManager.beginTransaction().replace(binding.frameContainer.id, fragment).commit()

        // 스택 필요한 경우
        supportFragmentManager.beginTransaction()
            .replace(binding.frameContainer.id, fragment)
            .addToBackStack(null)  // 프래그먼트를 스택에 추가
            .commit()
    }

    override fun popFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {  // 스택에 프래그먼트가 있는 경우
            supportFragmentManager.popBackStack()  // 가장 최근에 추가된 프래그먼트를 스택에서 제거
        } else {
            super.onBackPressed()  // 스택에 프래그먼트가 없는 경우 액티비티를 종료
        }
    }
}
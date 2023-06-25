package com.example.habday_android.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityMainBinding
import com.example.habday_android.src.main.list.FinishFundingFragment
import com.example.habday_android.src.main.list.MyParticipationFundingFragment
import com.example.habday_android.src.main.list.ProgressingFundingFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    lateinit var finishFundingFragment: FinishFundingFragment
    lateinit var progressingFundingFragment: ProgressingFundingFragment
    lateinit var myParticipationFundingFragment: MyParticipationFundingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTabLayout()
        tabLayout()
    }

    private fun initTabLayout(){
        finishFundingFragment = FinishFundingFragment()
        progressingFundingFragment = ProgressingFundingFragment()
        myParticipationFundingFragment = MyParticipationFundingFragment()

        supportFragmentManager.beginTransaction().add(R.id.fl_main, finishFundingFragment).commit()
    }

    private fun tabLayout(){
        binding.tlMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> replaceView(finishFundingFragment)
                    1 -> replaceView(progressingFundingFragment)
                    2 -> replaceView(myParticipationFundingFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun replaceView(fragment: Fragment){
        var selectedFragment: Fragment ?= null
        selectedFragment = fragment
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
            .replace(R.id.fl_main, it).commit()}
    }
}
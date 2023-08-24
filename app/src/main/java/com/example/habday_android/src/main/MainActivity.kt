package com.example.habday_android.src.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityMainBinding
import com.example.habday_android.src.main.add.AddFundingActivity
import com.example.habday_android.src.main.list.finish.FinishFundingFragment
import com.example.habday_android.src.main.list.myparticipation.MyParticipationFundingFragment
import com.example.habday_android.src.main.list.progressingfunding.ProgressingFundingFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) , UserView{
    lateinit var finishFundingFragment: FinishFundingFragment
    lateinit var progressingFundingFragment: ProgressingFundingFragment
    lateinit var myParticipationFundingFragment: MyParticipationFundingFragment

    var leftDay : Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)
        UserService(this).tryGetUserInfo()
        initTabLayout()
        tabLayout()
        navigateToAddFunding()
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

    private fun navigateToAddFunding(){
        binding.btnAddFunding.setOnClickListener{
            startActivity(Intent(this, AddFundingActivity::class.java))
        }
    }

    override fun getUserInfoSuccess(response: UserInfoResponse) {
        dismissLoadingDialog()
        leftDay = response.data.leftday

        if(response.data.leftday == 365){
            binding.tvMainDDay.text = response.data.name + "님\n생일 축하합니다!"
        }else{
            binding.tvMainDDay.text = response.data.name + "님\n생일까지 " + (response.data.leftday+1) + "일 남았습니다"
        }
    }

    override fun getUserInfoFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("로딩에 실패했습니다")
    }
}
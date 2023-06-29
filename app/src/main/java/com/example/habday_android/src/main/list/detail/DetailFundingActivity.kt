package com.example.habday_android.src.main.list.detail

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habday_android.R
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityDetailFundingBinding
import com.example.habday_android.src.main.list.detail.image.DetailFundingImageAdapter
import com.example.habday_android.util.recycler.finish.FinishData
import com.example.habday_android.util.recycler.funder.FunderAdapter
import com.example.habday_android.util.recycler.funder.FunderData

class DetailFundingActivity : BaseActivity<ActivityDetailFundingBinding>(ActivityDetailFundingBinding::inflate) {

    lateinit var funderAdapter : FunderAdapter
    val funderdatas = mutableListOf<FunderData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToMain()
        setViewPager()
        tempSettingProgressBar()
        initRV()
    }

    private fun setViewPager(){
        var img = arrayOfNulls<Drawable>(3)
        img[0] = this?.getDrawable(R.drawable.img_max1)
        img[1] = this?.getDrawable(R.drawable.img_max2)
        img[2] = this?.getDrawable(R.drawable.img_max3)

        val pagerAdapter = DetailFundingImageAdapter(this, img)
        binding.vpDetailFundingImg.adapter = pagerAdapter
        binding.wormDotsIndicator.setViewPager2(binding.vpDetailFundingImg)
    }

    private fun tempSettingProgressBar(){
        binding.progressBarDetailFunding.progress = 30 // 30%
    }

    private fun initRV(){
        funderdatas.clear()

        funderAdapter = FunderAdapter(this)
        binding.recyclerDetailFunding.adapter = funderAdapter

        for(i in 1 until 10){
            funderdatas.apply { add(FunderData(name = "test 1")) }
        }

        funderAdapter.funderdatas = funderdatas
        funderAdapter.notifyDataSetChanged()
    }

    private fun navigateToMain(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }
}
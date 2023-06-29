package com.example.habday_android.src.main.list.detail.image

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.habday_android.R
import com.example.habday_android.config.BaseFragment
import com.example.habday_android.databinding.FragmentDetailFundingSlideImageBinding

class DetailFundingSlideImageFragment(val image: Drawable) : BaseFragment<FragmentDetailFundingSlideImageBinding>
    (FragmentDetailFundingSlideImageBinding::bind, R.layout.fragment_detail_funding_slide_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val width = getItemWidth()

        Glide.with(this)
            .load(image)
            .override(width, width)
            .centerCrop()
            .into(binding.ivDetailFundingImgViewer)
    }

    // display 별 화면에 맞는 그리드 크기 구하기
    private fun getItemWidth():Int{
        val display = this.context?.resources?.displayMetrics
        val displaywidth = display?.widthPixels

        return displaywidth!!
    }
}
package com.example.habday_android.src.main.list.detail.image

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailFundingImageAdapter(fa: FragmentActivity, val images: Array<Drawable?>): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = images.size

    override fun createFragment(position: Int): Fragment {
        return when(position){
            position -> DetailFundingSlideImageFragment(images[position]!!)
            else -> DetailFundingSlideImageFragment(images[0]!!)
        }
    }
}
package com.example.habday_android.src.main.list

import android.os.Bundle
import android.view.View
import com.example.habday_android.R
import com.example.habday_android.config.BaseFragment
import com.example.habday_android.databinding.FragmentProgressingFundingBinding
import com.example.habday_android.util.recycler.progressing.ProgressingAdapter
import com.example.habday_android.util.recycler.progressing.ProgressingData

class ProgressingFundingFragment : BaseFragment<FragmentProgressingFundingBinding>
    (FragmentProgressingFundingBinding::bind, R.layout.fragment_progressing_funding) {

    lateinit var progressingAdapter: ProgressingAdapter
    val progressingdatas = mutableListOf<ProgressingData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
    }

    private fun initRV(){
        progressingdatas.clear()

        progressingAdapter = ProgressingAdapter(requireActivity())
        binding.rvMainProgressing.adapter = progressingAdapter

        for(i in 1 until 10){
            progressingdatas.apply { add(ProgressingData(title = "test 1")) }
        }

        progressingAdapter.progressingdatas = progressingdatas
        progressingAdapter.notifyDataSetChanged()
    }

}
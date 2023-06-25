package com.example.habday_android.src.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habday_android.R
import com.example.habday_android.config.BaseFragment
import com.example.habday_android.databinding.FragmentFinishFundingBinding
import com.example.habday_android.util.recycler.finish.FinishAdapter
import com.example.habday_android.util.recycler.finish.FinishData

class FinishFundingFragment : BaseFragment<FragmentFinishFundingBinding>
    (FragmentFinishFundingBinding::bind, R.layout.fragment_finish_funding) {

    lateinit var finishAdapter: FinishAdapter
    val finishdatas = mutableListOf<FinishData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
    }

    private fun initRV(){
        finishAdapter = FinishAdapter(requireActivity())
        binding.rvMainFinish.adapter = finishAdapter

        for(i in 1 until 10){
            finishdatas.apply { add(FinishData(title = "test 1")) }
        }

        finishAdapter.finishdatas = finishdatas
        finishAdapter.notifyDataSetChanged()
    }
}
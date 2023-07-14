package com.example.habday_android.src.main.list.myparticipation

import android.os.Bundle
import android.view.View
import com.example.habday_android.R
import com.example.habday_android.config.BaseFragment
import com.example.habday_android.databinding.FragmentMyParticipationFundingBinding
import com.example.habday_android.util.recycler.myparticipation.MyParticipationAdapter
import com.example.habday_android.util.recycler.myparticipation.MyParticipationData

class MyParticipationFundingFragment : BaseFragment<FragmentMyParticipationFundingBinding>
    (FragmentMyParticipationFundingBinding::bind, R.layout.fragment_my_participation_funding) {

    lateinit var myParticipationAdapter: MyParticipationAdapter
    val myParticipationdatas = mutableListOf<MyParticipationData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
    }

    private fun initRV(){
        myParticipationdatas.clear()

        myParticipationAdapter = MyParticipationAdapter(requireActivity())
        binding.rvMainMyParticipation.adapter = myParticipationAdapter

        for(i in 1 until 10){
            myParticipationdatas.apply { add(MyParticipationData(title = "test 1")) }
        }

        myParticipationAdapter.myParticipationdatas = myParticipationdatas
        myParticipationAdapter.notifyDataSetChanged()
    }
}
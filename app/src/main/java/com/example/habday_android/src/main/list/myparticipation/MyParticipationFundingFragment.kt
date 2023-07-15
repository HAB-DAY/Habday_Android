package com.example.habday_android.src.main.list.myparticipation

import android.os.Bundle
import android.view.View
import com.example.habday_android.R
import com.example.habday_android.config.BaseFragment
import com.example.habday_android.databinding.FragmentMyParticipationFundingBinding
import com.example.habday_android.src.main.list.myparticipation.model.MyParticipationFundingResponse
import com.example.habday_android.util.recycler.myparticipation.MyParticipationAdapter
import com.example.habday_android.util.recycler.myparticipation.MyParticipationData

class MyParticipationFundingFragment : BaseFragment<FragmentMyParticipationFundingBinding>
    (FragmentMyParticipationFundingBinding::bind, R.layout.fragment_my_participation_funding), MyParticipationView {

    lateinit var myParticipationAdapter: MyParticipationAdapter
    val myParticipationdatas = mutableListOf<MyParticipationData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())
        MyParticipationService(this).tryGetMyParticipationFundingList(1, null)
    }

    private fun initRV(response: MyParticipationFundingResponse){
        myParticipationdatas.clear()

        myParticipationAdapter = MyParticipationAdapter(requireActivity())
        binding.rvMainMyParticipation.adapter = myParticipationAdapter

        for(i in response.data.lists.indices){
            myParticipationdatas.apply { add(MyParticipationData(
                fundingName = response.data.lists[i].fundingName,
                fundingMemberId = response.data.lists[i].fundingMemberId,
                merchantId = response.data.lists[i].merchantId,
                fundingItemId = response.data.lists[i].fundingItemId,
                fundingItemImg = response.data.lists[i].fundingItemImg,
                fundingDate = response.data.lists[i].fundingDate,
                payment_status = response.data.lists[i].payment_status,
                creatorName = response.data.lists[i].creatorName,
                fundingAmount = response.data.lists[i].fundingAmount,
                fundingStatus = response.data.lists[i].fundingStatus
            )) }
        }

        myParticipationAdapter.myParticipationdatas = myParticipationdatas
        myParticipationAdapter.notifyDataSetChanged()
    }

    override fun onGetMyParticipationListSuccess(response: MyParticipationFundingResponse) {
        dismissLoadingDialog()
        initRV(response)
    }

    override fun onGetMyParticipationListFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("로딩에 실패했습니다")
    }
}
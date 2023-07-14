package com.example.habday_android.src.main.list.progressingfunding

import android.os.Bundle
import android.view.View
import com.example.habday_android.R
import com.example.habday_android.config.BaseFragment
import com.example.habday_android.databinding.FragmentProgressingFundingBinding
import com.example.habday_android.src.main.list.progressingfunding.model.ProgressingFundingResponse
import com.example.habday_android.util.recycler.progressing.ProgressingAdapter
import com.example.habday_android.util.recycler.progressing.ProgressingData

class ProgressingFundingFragment : BaseFragment<FragmentProgressingFundingBinding>
    (FragmentProgressingFundingBinding::bind, R.layout.fragment_progressing_funding), ProgressingFundingView {

    lateinit var progressingAdapter: ProgressingAdapter
    val progressingdatas = mutableListOf<ProgressingData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(requireContext())
        ProgressingFundingService(this).tryGetProgressingFundingList(1, null)
    }

    private fun initRV(response: ProgressingFundingResponse){
        progressingdatas.clear()

        progressingAdapter = ProgressingAdapter(requireActivity())
        binding.rvMainProgressing.adapter = progressingAdapter


        for(i in response.data.lists.indices){
            progressingdatas.apply { add(ProgressingData(id = response.data.lists[i].id,
             fundingItemImg = response.data.lists[i].fundingItemImg,
             fundingName = response.data.lists[i].fundingName,
             totalPrice = response.data.lists[i].totalPrice,
             startDate = response.data.lists[i].startDate,
             finishDate = response.data.lists[i].finishDate,
             status = response.data.lists[i].status)) }
        }

        progressingAdapter.progressingdatas = progressingdatas
        progressingAdapter.notifyDataSetChanged()
    }

    override fun getProgressingFundingListSuccess(response: ProgressingFundingResponse) {
        dismissLoadingDialog()
        initRV(response)
    }

    override fun getProgressingFundingListFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("로딩에 실패했습니다")
    }

}
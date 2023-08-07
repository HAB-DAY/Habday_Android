package com.example.habday_android.src.main.list.finish

import android.os.Bundle
import android.view.View
import com.example.habday_android.R
import com.example.habday_android.config.BaseFragment
import com.example.habday_android.databinding.FragmentFinishFundingBinding
import com.example.habday_android.src.main.list.finish.model.FinishFundingResponse
import com.example.habday_android.util.recycler.finish.FinishAdapter
import com.example.habday_android.util.recycler.finish.FinishData

class FinishFundingFragment : BaseFragment<FragmentFinishFundingBinding>
    (FragmentFinishFundingBinding::bind, R.layout.fragment_finish_funding), FinishFundingView {

    lateinit var finishAdapter: FinishAdapter
    val finishdatas = mutableListOf<FinishData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        showLoadingDialog(requireContext())
        FinishFundingService(this).tryGetFinishFundingList(null) // 임시
    }

    private fun initRV(response: FinishFundingResponse){
        finishdatas.clear()

        finishAdapter = FinishAdapter(requireActivity())
        binding.rvMainFinish.adapter = finishAdapter


        for(i in response.data.lists.indices){
            finishdatas.apply { add(FinishData(id = response.data.lists[i].id, fundingItemImg = response.data.lists[i].fundingItemImg,
                fundingName = response.data.lists[i].fundingName, totalPrice = response.data.lists[i].totalPrice,
                startDate = response.data.lists[i].startDate, finishDate = response.data.lists[i].finishDate, status = response.data.lists[i].status)) }
        }

        finishAdapter.finishdatas = finishdatas
        finishAdapter.notifyDataSetChanged()
    }

    override fun onGetFinishFundingListSuccess(response: FinishFundingResponse) {
        dismissLoadingDialog()
        initRV(response)
    }

    override fun onGetFinishFundingListFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("로딩에 실패했습니다")
    }
}
package com.example.habday_android.src.main.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.core.util.Pair
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityAddFundingBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.text.SimpleDateFormat
import java.util.*


class AddFundingActivity : BaseActivity<ActivityAddFundingBinding>(ActivityAddFundingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backToMain()
        getDateRangePicker()
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun getDateRangePicker(){
        binding.ivCalendar.setOnClickListener {
            val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("펀딩 기간을 선택해주세요")
                .build()

            dateRangePicker.show(supportFragmentManager, "date_picker")
            dateRangePicker.addOnPositiveButtonClickListener { selection ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selection?.first ?: 0
                val startDate = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()
                Log.d("date_start", startDate)

                calendar.timeInMillis = selection?.second ?: 0
                val endDate = SimpleDateFormat("yyyyMMdd").format(calendar.time).toString()
                Log.d("date_end", endDate)

                binding.tvAddFundingSelectedTerm.text = startDate.substring(0, 4) + "년 " + startDate.substring(4, 6) + "월 " + startDate.substring(6, 8) + "일 ~ " +
                                                        endDate.substring(0, 4) + "년 " + endDate.substring(4, 6) + "월 " + endDate.substring(6, 8) + "일"
            }

        }
    }

    private fun backToMain(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }
}
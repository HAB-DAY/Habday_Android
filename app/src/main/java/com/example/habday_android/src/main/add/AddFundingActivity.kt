package com.example.habday_android.src.main.add

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityAddFundingBinding
import com.example.habday_android.src.main.add.finish.FinishAddingFundingActivity
import com.google.android.material.datepicker.MaterialDatePicker
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.text.SimpleDateFormat
import java.util.*


class AddFundingActivity : BaseActivity<ActivityAddFundingBinding>(ActivityAddFundingBinding::inflate) {
    private var OPEN_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backToMain()
        openGallery()
        getDateRangePicker()
        addFunding()
    }

    private fun openGallery(){
        binding.ivAddFundingImg.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, OPEN_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){

            if(requestCode == OPEN_GALLERY){
                var currentImageUrl: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)

                    binding.ivSelect1.isGone = false
                    binding.ivSelect1.setImageBitmap(bitmap)
                    binding.ivDelete1.isVisible = true

                    binding.ivDelete1.setOnClickListener {
                        binding.ivSelect1.isGone = true
                        binding.ivDelete1.isGone = true
                    }


                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    inner class BitmapRequestBody(private val bitmap: Bitmap): RequestBody(){
        override fun contentType(): MediaType? = "image/jpeg".toMediaType()

        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }

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

    private fun addFunding(){
        binding.tvAddFundingFinish.setOnClickListener {
            // checkbox 체크해야 펀딩 생성 가능!

            startActivity(Intent(this, FinishAddingFundingActivity::class.java))
            finish()
        }
    }

    private fun backToMain(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }

}
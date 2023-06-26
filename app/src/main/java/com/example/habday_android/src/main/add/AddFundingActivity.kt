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
import com.google.android.material.datepicker.MaterialDatePicker
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.text.SimpleDateFormat
import java.util.*


class AddFundingActivity : BaseActivity<ActivityAddFundingBinding>(ActivityAddFundingBinding::inflate) {
    private var OPEN_GALLERY = 1
    var bitmaps = ArrayList<Bitmap>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backToMain()
        openGallery()
        getDateRangePicker()
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
            var galleryImgs = arrayOf(binding.ivSelect1 ,binding.ivSelect2, binding.ivSelect3)
            var deleteImgs = arrayOf(binding.ivDelete1, binding.ivDelete2, binding.ivDelete3)

            if(requestCode == OPEN_GALLERY){
                var currentImageUrl: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
                    bitmaps.add(bitmap)

                    for(i in bitmaps.indices){
                        deleteImgs[i].setOnClickListener {
                            deleteImgs[bitmaps.size-1].isGone = true
                            bitmaps.removeAt(i)
                            galleryImgs[i].isInvisible = true
                        }
                    }

                    for(i in bitmaps.indices){
                        galleryImgs[i].setImageBitmap(bitmaps[i])
                        deleteImgs[i].isVisible = true

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

    private fun backToMain(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }
}
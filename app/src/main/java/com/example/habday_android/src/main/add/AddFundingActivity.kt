package com.example.habday_android.src.main.add

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.CompoundButton
import android.widget.DatePicker
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityAddFundingBinding
import com.example.habday_android.src.main.add.finish.FinishAddingFundingActivity
import com.example.habday_android.src.main.add.model.AddFundingResponse
import com.google.android.material.datepicker.MaterialDatePicker
import okhttp3.MediaType
import okhttp3.MediaType.Companion.parse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class AddFundingActivity : BaseActivity<ActivityAddFundingBinding>(ActivityAddFundingBinding::inflate), AddFundingView {
    private var OPEN_GALLERY = 1

    private var fundingItemImg: MultipartBody.Part ?= null
    var jsonBody : RequestBody?= null
    private var checkbox = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backToMain()
        openGallery()
        getDatePicker()
        checkCheckBox()
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

                    changeToMultipart(bitmap)

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getFundingText(): Boolean{
        if(binding.etAddFundingTitle.text.isNullOrBlank() || binding.etAddFundingInformation.text.isNullOrBlank() || binding.etAddFundingAmount.text.isNullOrBlank()
            || binding.etAddFundingGoal.text.isNullOrBlank() || binding.tvAddFundingSelectedTerm.text.isNullOrBlank()){
            showCustomToast("모두 입력해주세요")
            return false
        }else if(binding.etAddFundingAmount.text.toString().toLong() < binding.etAddFundingGoal.text.toString().toLong()){
            showCustomToast("상품 가격보다 목표 금액이 더 큽니다")
            return false
        }else if(fundingItemImg.toString() == "null"){ // 이미지 존재 여부 파악
            showCustomToast("이미지를 선택해주세요")
            return false
        } else{
            val fundingName = binding.etAddFundingTitle.text.toString()
            val fundDetail = binding.etAddFundingInformation.text.toString()
            val itemPrice = binding.etAddFundingAmount.text.toString().toInt()
            val goalPrice = binding.etAddFundingGoal.text.toString().toInt()
            val startDate = binding.tvAddFundingSelectedTerm.text.toString().substring(0, 4) + "-" +
                    binding.tvAddFundingSelectedTerm.text.toString().substring(6, 8) + "-" +
                    binding.tvAddFundingSelectedTerm.text.toString().substring(10, 12)
            val finishDate = binding.tvAddFundingSelectedTerm.text.toString().substring(16, 20) + "-" +
                    binding.tvAddFundingSelectedTerm.text.toString().substring(22, 24) + "-" +
                    binding.tvAddFundingSelectedTerm.text.toString().substring(26, 28)
            Log.d("finishDate-1", finishDate)


            val jsonObject = JSONObject("{\"fundingName\":\"${fundingName}\",\"fundDetail\":\"${fundDetail}\"," +
                    "\"itemPrice\":\"${itemPrice}\",\"goalPrice\":\"${goalPrice}\",\"startDate\":\"${startDate}\",\"finishDate\":\"${finishDate}\"}").toString() // JSON 객체 생성
            jsonBody = RequestBody.create("application/json".toMediaTypeOrNull(),jsonObject) // RequestBody 형태로 변환

            return true
        }
    }

    private fun changeToMultipart(bitmap: Bitmap){
        val bitmapRequestBody = BitmapRequestBody(bitmap)
        val bitmapMultipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("fundingItemImg", ".png", bitmapRequestBody)

        fundingItemImg = bitmapMultipartBody

    }

    inner class BitmapRequestBody(private val bitmap: Bitmap): RequestBody(){
        override fun contentType(): MediaType = "image/*".toMediaType()

        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }

    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun getDatePicker(){
        binding.ivCalendar.setOnClickListener {
            var dateString = ""

            val cal = Calendar.getInstance()

            /*
            val formatter = SimpleDateFormat("yyyyMMdd")
            val today = Date()
            cal.time = today
            cal.add(Calendar.DATE, -1)
            val finishDate = formatter.format(cal.time) // 생일 전날까지

             */

            val finishDate = SimpleDateFormat("yyyyMMdd").format(cal.time).toString()

            val dateSetListener = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                var months = ""
                months = if(month+1 < 10){
                    "0" + (month+1)
                }else{
                    (month+1).toString()
                }

                var days = ""
                days = if(dayOfMonth < 10){
                    "0$dayOfMonth"
                }else{
                    dayOfMonth.toString()
                }

                /*
                var selectedDate = (year.toString() + months + days).toInt()
                if(selectedDate < todayDate.toInt()){
                    showCustomToast("이전 날짜는 선택하실 수 없습니다")
                    binding.tvAddFundingSelectedTerm.text = null
                }else{
                    dateString = "${year}년 ${months}월 ${days}일"
                    binding.tvAddFundingSelectedTerm.text = dateString + " ~ " + finishDate.substring(0, 4) + "년 " + finishDate.substring(4, 6) + "월 " + finishDate.substring(6, 8) + "일"
                }*/
                dateString = "${year}년 ${months}월 ${days}일"
                binding.tvAddFundingSelectedTerm.text = dateString + " ~ " + finishDate.substring(0, 4) + "년 " + finishDate.substring(4, 6) + "월 " + finishDate.substring(6, 8) + "일"

            }

            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun checkCheckBox(){
        // checkbox 체크해야 펀딩 생성 가능!
        binding.cbAddFunding.setOnCheckedChangeListener { compoundButton, isChecked ->
            checkbox = isChecked
        }
    }

    private fun addFunding(){
        binding.tvAddFundingFinish.setOnClickListener {
            if(getFundingText()){ // 정보 가져오기
                if(checkbox){
                    showLoadingDialog(this)
                    AddFundingService(this).tryAddFunding(fundingItemImg!!, jsonBody!!)
                }else{
                    showCustomToast("유의사항을 확인해주세요")
                }
            }
        }
    }

    private fun backToMain(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }

    override fun onPostAddFundingSuccess(response: AddFundingResponse) {
        dismissLoadingDialog()
        intent = Intent(this, FinishAddingFundingActivity::class.java)
        intent.putExtra("shareLink", response.data)
        startActivity(intent)
        finish()

    }

    override fun onPostAddFundingFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("펀딩 등록에 실패했습니다")
    }
}
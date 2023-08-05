package com.example.habday_android.src.main.add

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.CompoundButton
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
    private var dto = HashMap<String, RequestBody>()
    var jsonBody : RequestBody?= null

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

                    changeToMultipart(bitmap)

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getFundingText(){
        /*
        //val fundingName = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etAddFundingTitle.text.toString())
        val fundingName : RequestBody = binding.etAddFundingTitle.text.toString().toPlainRequestBody()
        //val fundDetail = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etAddFundingInformation.text.toString())
        val fundDetail : RequestBody = binding.etAddFundingInformation.text.toString().toPlainRequestBody()
        //val itemPrice = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etAddFundingAmount.text.toString())
        val itemPrice : RequestBody = binding.etAddFundingAmount.text.toString().toPlainRequestBody()
        //val goalPrice = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etAddFundingGoal.text.toString().toInt())
        val goalPrice : RequestBody = binding.etAddFundingGoal.text.toString().toPlainRequestBody()
        //val startDate = RequestBody.create("text/plain".toMediaTypeOrNull(), "2023-07-21") // 임시
        val startDate: RequestBody = "2023-07-21".toPlainRequestBody()
        //val finishDate = RequestBody.create("text/plain".toMediaTypeOrNull(), "2023-08-12") // 임시
        val finishDate : RequestBody = "2023-08-12".toPlainRequestBody()

        dto["fundingName"] = fundingName
        dto["fundDetail"] = fundDetail
        dto["itemPrice"] = itemPrice
        dto["goalPrice"] = goalPrice
        dto["startDate"] = startDate
        dto["finishDate"] = finishDate

         */

        val fundingName = binding.etAddFundingTitle.text.toString()
        val fundDetail = binding.etAddFundingInformation.text.toString()
        val itemPrice = binding.etAddFundingAmount.text.toString().toInt()
        val goalPrice = binding.etAddFundingGoal.text.toString().toInt()
        val startDate = "2023-07-29"
        val finishDate = "2023-08-12"

        val jsonObject = JSONObject("{\"fundingName\":\"${fundingName}\",\"fundDetail\":\"${fundDetail}\",\"itemPrice\":\"${itemPrice}\",\"goalPrice\":\"${goalPrice}\",\"startDate\":\"${startDate}\",\"finishDate\":\"${finishDate}\"}")//.toString()
        //jsonBody = RequestBody.create("application/json".toMediaTypeOrNull(),jsonObject)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        jsonBody = jsonObject.toString().toRequestBody(mediaType)

    }

    private fun String?.toPlainRequestBody() = requireNotNull(this).toRequestBody("text/plain".toMediaType())

    private fun changeToMultipart(bitmap: Bitmap){
        val bitmapRequestBody = BitmapRequestBody(bitmap)
        val bitmapMultipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("image", ".png", bitmapRequestBody)

        fundingItemImg = bitmapMultipartBody

    }

    inner class BitmapRequestBody(private val bitmap: Bitmap): RequestBody(){
        override fun contentType(): MediaType = "image/jpeg".toMediaType()

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
            getFundingText() // 정보 가져오기


            // checkbox 체크해야 펀딩 생성 가능!


            //AddFundingService(this).tryAddFunding(fundingItemImg!!, dto)
            AddFundingService(this).tryAddFunding(fundingItemImg!!, jsonBody!!)

            //showLoadingDialog(this)
        }
    }

    private fun backToMain(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }

    override fun onPostAddFundingSuccess(response: AddFundingResponse) {
        //dismissLoadingDialog()
        Log.d("addFund", "성공")
        showCustomToast("success!!!")
        Log.d("fundingItemImgsuccess", fundingItemImg.toString())

        /*
        startActivity(Intent(this, FinishAddingFundingActivity::class.java))
            finish()
         */
    }

    override fun onPostAddFundingFailure(message: String) {
        //dismissLoadingDialog()
        showCustomToast("펀딩 등록에 실패했습니다")
        Log.d("fundingItemImgfail", fundingItemImg.toString())
    }
}
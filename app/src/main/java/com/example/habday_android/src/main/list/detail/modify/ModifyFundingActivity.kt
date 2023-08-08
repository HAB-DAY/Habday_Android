package com.example.habday_android.src.main.list.detail.modify

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityModifyFundingBinding
import com.example.habday_android.src.main.list.detail.DetailFundingService
import com.example.habday_android.src.main.list.detail.DetailFundingView
import com.example.habday_android.src.main.list.detail.model.DetailFundingResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import java.util.HashMap

class ModifyFundingActivity : BaseActivity<ActivityModifyFundingBinding>(ActivityModifyFundingBinding::inflate), ModifyFundingView,
    DetailFundingView {
    private var OPEN_GALLERY = 1

    private var fundingItemImg: MultipartBody.Part ?= null
    private var data = HashMap<String, RequestBody>()
    private var itemId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToFundingDetail()
        getItemId()

        //showLoadingDialog(this)
        //DetailFundingService(this).tryGetDetailFunding(itemId!!)

        openGallery()
        modifyFunding()
    }

    private fun getItemId(){
        itemId = intent.getIntExtra("itemId", 0)
        Log.d("itemId", itemId.toString())
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
        val fundingItemName = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etAddFundingTitle.text.toString())
        val fundingItemDetail = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.etAddFundingInformation.text.toString())

        data["fundingItemName"] = fundingItemName
        data["fundingItemDetail"] = fundingItemDetail
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

    private fun navigateToFundingDetail(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }

    private fun modifyFunding(){
        binding.tvAddFundingFinish.setOnClickListener {
            getFundingText()
            // 체크박스 확인하기

            showLoadingDialog(this)
            ModifyFundingService(this).tryModifyFunding(itemId!!, fundingItemImg!!, data)
            Log.d("fundingItemImg", fundingItemImg!!.toString())
        }
    }

    // 수정 시 기본 정보 띄우기
    override fun onGetDetailFundingSuccess(response: DetailFundingResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetDetailFundingFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPutModifyFundingSuccess(response: ModifyFundingResponse) {
        dismissLoadingDialog()
        showCustomToast("펀딩 수정에 성공했습니다")
        finish()
    }

    override fun onPutModifyFundingFailure(message: String) {
       dismissLoadingDialog()
        showCustomToast("펀딩 등록에 실패했습니다")
    }


}
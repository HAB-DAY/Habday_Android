package com.example.habday_android.src.main.list.detail.certify

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityCertifyFundingBinding
import com.example.habday_android.src.main.list.detail.certify.model.CertifyFundingFailureResponse
import com.example.habday_android.src.main.list.detail.certify.model.CertifyFundingSuccessResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import org.json.JSONObject

class CertifyFundingActivity : BaseActivity<ActivityCertifyFundingBinding>(ActivityCertifyFundingBinding::inflate), CertifyFundingView {
    private var OPEN_GALLERY = 1

    private var img: MultipartBody.Part ?= null
    var jsonBody : RequestBody ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToDetail()
        openGallery()
        finishCertify()
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

    private fun changeToMultipart(bitmap: Bitmap){
        val bitmapRequestBody = BitmapRequestBody(bitmap)
        val bitmapMultipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("img", ".png", bitmapRequestBody)

        img = bitmapMultipartBody

    }

    inner class BitmapRequestBody(private val bitmap: Bitmap): RequestBody(){
        override fun contentType(): MediaType = "image/*".toMediaType()

        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }

    }

    private fun getCertifyText(): Boolean{
        if(binding.etCertifyFundingTitle.text.isNullOrBlank() || binding.etAddFundingInformation.text.isNullOrBlank()){
            showCustomToast("모두 입력해주세요")
            return  false
        }else if(img.toString() == "null"){ // 이미지 존재 여부 파악
            showCustomToast("이미지를 선택해주세요")
            return false
        }else{
            val message = binding.etAddFundingInformation.text.toString()
            val title = binding.etCertifyFundingTitle.text.toString()

            val jsonObject = JSONObject("{\"message\":\"${message}\",\"title\":\"${title}\"}").toString() // JSON 객체 생성
            jsonBody = RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject) // RequestBody 형태로 변환

            return true
        }
    }

    private fun finishCertify(){
        binding.tvCertifyFinish.setOnClickListener {

        }
    }

    private fun navigateToDetail(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }

    override fun onPostCertifyFundingSuccess(response: CertifyFundingSuccessResponse) {
        dismissLoadingDialog()
        showCustomToast("인증에 성공했습니다")
        finish()
    }

    override fun onPostCertifyFundingFailure(response: CertifyFundingFailureResponse) {
        dismissLoadingDialog()
    }


}
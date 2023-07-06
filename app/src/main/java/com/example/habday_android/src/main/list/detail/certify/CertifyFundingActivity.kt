package com.example.habday_android.src.main.list.detail.certify

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityCertifyFundingBinding

class CertifyFundingActivity : BaseActivity<ActivityCertifyFundingBinding>(ActivityCertifyFundingBinding::inflate) {
    private var OPEN_GALLERY = 1

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


                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun finishCertify(){
        binding.tvCertifyFinish.setOnClickListener {
            finish()
        }
    }

    private fun navigateToDetail(){
        binding.ivLeftArrow.setOnClickListener {
            finish()
        }
    }
}
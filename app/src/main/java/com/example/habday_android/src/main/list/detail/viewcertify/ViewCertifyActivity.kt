package com.example.habday_android.src.main.list.detail.viewcertify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityViewCertifyBinding
import com.example.habday_android.src.main.list.detail.viewcertify.model.ViewCertifyResponse

class ViewCertifyActivity : BaseActivity<ActivityViewCertifyBinding>(ActivityViewCertifyBinding::inflate), ViewCertifyView {
    private var itemId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToDetail()
        getItemId()

        showLoadingDialog(this)
        ViewCertifyService(this).tryGetConfirmation(itemId!!)
    }

    private fun getItemId(){
        itemId = intent.getIntExtra("itemId", 0)
        Log.d("itemId", itemId.toString())
    }

    private fun navigateToDetail(){
        binding.ivLeftArrow.setOnClickListener { finish() }
    }

    override fun tryGetConfirmationSuccess(response: ViewCertifyResponse) {
        dismissLoadingDialog()

        Glide.with(this)
            .load(response.data.confirmationImg)
            .centerCrop()
            .into(binding.ivCertifyImg)

        binding.tvCertifyTitle.text = response.data.title

        val fireWorkUnicode = 0x1F389
        binding.tvTotalPrice.text = "${String(Character.toChars(fireWorkUnicode))} " +  response.data.totalPrice.toInt().toString() + "원" + " ${String(Character.toChars(fireWorkUnicode))}"

        binding.tvCertifyMessage.text = response.data.message
    }

    override fun tryGetConfirmationFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("로딩에 실패했습니다")
    }
}
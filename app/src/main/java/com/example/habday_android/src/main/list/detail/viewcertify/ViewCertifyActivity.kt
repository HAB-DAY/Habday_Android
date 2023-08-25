package com.example.habday_android.src.main.list.detail.viewcertify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.habday_android.config.BaseActivity
import com.example.habday_android.databinding.ActivityViewCertifyBinding

class ViewCertifyActivity : BaseActivity<ActivityViewCertifyBinding>(ActivityViewCertifyBinding::inflate) {
    private var itemId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToDetail()
        getItemId()
    }

    private fun getItemId(){
        itemId = intent.getIntExtra("itemId", 0)
        Log.d("itemId", itemId.toString())
    }

    private fun navigateToDetail(){
        binding.ivLeftArrow.setOnClickListener { finish() }
    }
}
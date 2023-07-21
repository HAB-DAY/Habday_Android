package com.example.habday_android.src.main.list.detail.delete

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.habday_android.databinding.DialogDeleteFundingBinding
import com.example.habday_android.src.main.MainActivity

class DeleteFundingDialog(context: Context): Dialog(context), DeleteFundingView {
    private lateinit var binding: DialogDeleteFundingBinding
    var itemId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogDeleteFundingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable())

        btnFalse()
        btnDelete()
    }

    private fun btnFalse(){
        binding.btnDeleteFundingNone.setOnClickListener {
            dismiss()
        }
    }

    private fun btnDelete(){
        binding.btnDeleteFundingOk.setOnClickListener {
            DeleteFundingService(this).tryDeleteFunding(itemId!!)
            dismiss()

            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            this.context.startActivity(intent)

        }
    }

    override fun deleteFundingSuccess(response: DeleteFundingResponse) {
        Toast.makeText(context, "삭제하기를 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun deleteFundingFailure(message: String) {
        Toast.makeText(context, "삭제하기를 실패했습니다", Toast.LENGTH_SHORT).show()
    }
}
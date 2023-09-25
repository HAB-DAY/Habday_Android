package com.example.habday_android.src.main.settings.logout

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.example.habday_android.config.ApplicationClass.Companion.editor
import com.example.habday_android.databinding.DialogLogoutBinding
import com.example.habday_android.src.login.LoginActivity
import com.example.habday_android.src.main.MainActivity

class LogoutDialog(context: Context): Dialog(context) {
    private lateinit var binding: DialogLogoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable())

        btnFalse()
        btnLogout()
    }

    private fun btnFalse(){
        binding.btnLogoutNone.setOnClickListener{dismiss()}
    }

    private fun btnLogout(){
        binding.btnLogoutOk.setOnClickListener {
            editor.clear()
            editor.commit()

            dismiss()

            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            this.context.startActivity(intent)
        }
    }
}
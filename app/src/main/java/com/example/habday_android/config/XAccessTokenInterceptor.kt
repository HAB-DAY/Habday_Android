package com.example.habday_android.config


import com.example.habday_android.config.ApplicationClass.Companion.accessToken
import com.example.habday_android.config.ApplicationClass.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sSharedPreferences.getString(accessToken, null)
        if (jwtToken != null) {
            builder.addHeader("accessToken", jwtToken)
        }
        return chain.proceed(builder.build())
    }
}
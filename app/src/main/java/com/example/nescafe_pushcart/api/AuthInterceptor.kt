package com.example.nescafe_pushcart.api

import android.content.Context
import android.util.Log
import com.example.nescafe_pushcart.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add auth token to requests
 */

class AuthInterceptor(context: Context):Interceptor {

    val TAG = "AUTH_INTERCEPTOR"

    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // if token has been saved aff it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
            Log.d(TAG,"what is inside this authInterceptor:$it")
        }
        return chain.proceed(requestBuilder.build())
    }



}
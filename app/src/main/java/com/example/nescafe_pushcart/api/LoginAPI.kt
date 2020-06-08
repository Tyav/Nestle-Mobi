package com.example.nescafe_pushcart.api

import com.example.nescafe_pushcart.constants.URLConstants
import com.example.nescafe_pushcart.constants.URLEndpoints
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST(URLEndpoints.AUTHENTICATE_LOGIN_ENDPOINT)
   suspend fun loginAsync(
        @Body body:LoginBody
    ): SignInResponse


    companion object {

        operator fun invoke():LoginAPI {

            val apiClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URLConstants.BASE_URL)
                .client(apiClient)
                .build()
                .create(LoginAPI::class.java)

        }

    }


}
package com.example.nescafe_pushcart.api

import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import retrofit2.Response
import retrofit2.http.Body

interface LoginAPI {

   suspend fun loginAsync(
        @Body body:LoginBody
    ): Response<SignInResponse>


    companion object {

        operator fun invoke():LoginAPI {

        }

    }


}
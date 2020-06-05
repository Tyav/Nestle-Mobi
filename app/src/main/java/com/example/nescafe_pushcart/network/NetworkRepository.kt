package com.example.nescafe_pushcart.network

import android.util.Log
import com.example.nescafe_pushcart.api.LoginAPI
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.utils.BaseRepository
import com.example.nescafe_pushcart.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class NetworkRepository: BaseRepository() {

    val loginAPI = LoginAPI()

    val TAG = "NETWORK REPOSITORY"

    suspend fun getSignInResponse(body: LoginBody): Result<Response<SignInResponse>>{

        return withContext(Dispatchers.IO){

            try {
                val output = loginAPI.loginAsync(body)
                Log.d(TAG, "show me the response: ${output}")
                Result.Success(output)

            } catch (t:Throwable){
                Log.d(TAG, t.message.toString())
                Result.Error(t as Exception)
            }
        }

    }


}
package com.example.nescafe_pushcart.network

import android.content.Context
import android.util.Log
import com.example.nescafe_pushcart.api.LoginAPI
import com.example.nescafe_pushcart.api.VendorListAPI
import com.example.nescafe_pushcart.model.listofvendors.Content
import com.example.nescafe_pushcart.model.listofvendors.VendorList
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.utils.BaseRepository
import com.example.nescafe_pushcart.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class NetworkRepository(context: Context): BaseRepository() {



    val loginAPI = LoginAPI()
    val vendorListAPI = VendorListAPI(context)

    val TAG = "NETWORK REPOSITORY"

    suspend fun getSignInResponse(body: LoginBody): Result<SignInResponse>{

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


    // vendor list network call

    suspend fun getVendorList():Result<VendorList>{

        return withContext(Dispatchers.IO){

            try {
                val output = vendorListAPI.showVendors()
                Log.d(TAG, "please shw me the list of vendors:${output}")
                Result.Success(output)

            } catch (t:Throwable){
                Log.d(TAG, t.message.toString())
                Result.Error(t as Exception)
            }

        }

    }


}
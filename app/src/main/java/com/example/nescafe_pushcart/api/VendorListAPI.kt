package com.example.nescafe_pushcart.api

import android.content.Context
import com.example.nescafe_pushcart.constants.URLConstants
import com.example.nescafe_pushcart.constants.URLEndpoints
import com.example.nescafe_pushcart.model.listofvendors.Content
import com.example.nescafe_pushcart.model.listofvendors.VendorList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface VendorListAPI {

   @POST(URLEndpoints.LIST_OF_VENDOR_ENDPOINT)
   suspend fun showVendors(): Call<VendorList>

   companion object {

      operator fun invoke(context: Context):VendorListAPI{

         val apiClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor(context)).build()

         return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URLConstants.BASE_URL)
            .client(apiClient)
            .build()
            .create(VendorListAPI::class.java)

      }



   }

}
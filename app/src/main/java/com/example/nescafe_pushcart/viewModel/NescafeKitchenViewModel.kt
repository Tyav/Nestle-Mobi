package com.example.nescafe_pushcart.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nescafe_pushcart.model.listofvendors.Content
import com.example.nescafe_pushcart.model.listofvendors.VendorList
import com.example.nescafe_pushcart.network.NetworkRepository
import com.example.nescafe_pushcart.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class NescafeKitchenViewModel(application: Application): AndroidViewModel(application) {

    private val repository = NetworkRepository(getApplication())
    private val job = Job()
    private val viewModelScope = CoroutineScope(job + Dispatchers.Main)

    private val _myVendorList = MutableLiveData<Result<VendorList>>()
    val myVendorList:LiveData<Result<VendorList>>
    get() = _myVendorList

    // it when I initialized this "getVendorListNow()" that was whwn the
    // items in the recyclerview displayed

    init {
        getVendorListNow()
    }



    fun getVendorListNow():LiveData<Result<VendorList>>{

        viewModelScope.launch {

            _myVendorList.value = repository.getVendorList()

        }

        return _myVendorList

    }


}
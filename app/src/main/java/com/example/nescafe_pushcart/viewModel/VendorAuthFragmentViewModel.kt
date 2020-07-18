package com.example.nescafe_pushcart.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.network.NetworkRepository
import com.example.nescafe_pushcart.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VendorAuthFragmentViewModel(application: Application):AndroidViewModel(application) {

    private val repository = NetworkRepository(getApplication())
    private val job = Job()
    private val viewModelScope = CoroutineScope(job + Dispatchers.Main)


    private val _vendorSignedInDetails = MutableLiveData<Result<SignInResponse>>()
    val vendorSignedInDetails:LiveData<Result<SignInResponse>>
    get() = _vendorSignedInDetails


    fun signedInVendor(body:LoginBody):LiveData<Result<SignInResponse>>{

        viewModelScope.launch {
            _vendorSignedInDetails.value = repository.getSignInResponse(body)
        }
        return _vendorSignedInDetails
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
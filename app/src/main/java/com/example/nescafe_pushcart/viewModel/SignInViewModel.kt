package com.example.nescafe_pushcart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.network.NetworkRepository
import com.example.nescafe_pushcart.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class SignInViewModel: ViewModel() {

    private val repository = NetworkRepository()
    private val job = Job()
    private val viewModelScope = CoroutineScope(job + Dispatchers.Main)


    private val _userSignedInDetails = MutableLiveData<Result<SignInResponse>>()
    val userSignedInDetails:LiveData<Result<SignInResponse>>
    get() = _userSignedInDetails


    fun signedIn(body: LoginBody):LiveData<Result<SignInResponse>>{

        viewModelScope.launch {
            _userSignedInDetails.value = repository.getSignInResponse(body)
        }
        return _userSignedInDetails
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}
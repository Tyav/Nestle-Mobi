package com.example.nescafe_pushcart.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nescafe_pushcart.helpers.Validator
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.network.NetworkRepository
import com.example.nescafe_pushcart.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class SignInViewModel(application: Application): AndroidViewModel(application) {

    val validator = Validator()

    var email:String? = null
    var password:String? = null

    private val repository = NetworkRepository(getApplication())
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


    fun validateEmail(): Int?{
        return email?.let { email_ ->
            when {
                email_.isEmpty() -> 1
                !validator.isValidEmail(email_) -> 2
                else -> 0
            }
        }
    }

    fun validatePassword():Int?{
        return password?.let { password_ ->
            when {
                password_.isEmpty() -> 1
                !validator.isValidPassword(password_) -> 2
                else -> 0
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}
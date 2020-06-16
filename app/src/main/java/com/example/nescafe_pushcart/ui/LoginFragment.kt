package com.example.nescafe_pushcart.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nescafe_pushcart.R
import com.example.nescafe_pushcart.databinding.FragmentLoginBinding
import com.example.nescafe_pushcart.helpers.IsEmptyCheck
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.utils.InputUtils
import com.example.nescafe_pushcart.utils.Result
import com.example.nescafe_pushcart.utils.SessionManager
import com.example.nescafe_pushcart.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var email:String
    private lateinit var password:String
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sessionManager:SessionManager

    val TAG = "LOGIN_FRAGMENT"
    lateinit var viewModel:SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sessionManager = SessionManager(requireContext())

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // initialize the viewModel
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        setupTextChangeListeners()
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        login_btn.setOnClickListener {
            if (isInputFilled()){
                var loginBody = LoginBody()
                loginBody = LoginBody(email, password)
                val response =  signInCall(loginBody)
//                Log.i(TAG, "what is inside loginBody:$loginBody")
//                Toast.makeText(context, "I am workng",Toast.LENGTH_LONG).show()
                binding.userLoginProgressBar.visibility = View.VISIBLE

                response.observe(viewLifecycleOwner, Observer {



                    when(it) {

                        is Result.Success -> {
                            binding.userLoginProgressBar.visibility = View.GONE

                            if (it.data.status == "OK"){
                                val authKey:String = "${it.data.data?.token}"
                                Log.i(TAG,"inside authKey:$authKey")
                                sessionManager.saveAuthToken(authKey)
                            }



                            if (findNavController().currentDestination?.id == R.id.loginFragment){
                                findNavController().navigate(R.id.action_loginFragment_to_nescafeKitchen)
                                Toast.makeText(context,"${it.data.message}", Toast.LENGTH_LONG).show()
                            }

                            }
                        is Result.Error -> {
                            binding.userLoginProgressBar.visibility = View.GONE
                            var status:String = ""
                            if (it.exception.message?.trim() == "HTTP 422"){
                                status = "No record found"
                            } else if(it.exception.message?.trim() == "timeout"){
                                status = "No network"
                            } else if (it.exception.message?.trim() == "HTTP 400"){
                                status = "Incomplete login details"
                            }
                            Toast.makeText(context, "${it.exception.message}",Toast.LENGTH_SHORT).show()
                        }
                        }


                })

                closeKeyboard()
            }





//            userLoginProgressBar.visibility = View.VISIBLE

//            val response = login()


//            response.observe(viewLifecycleOwner, Observer {
//
//                Log.d(TAG, "what is inside:$it")
//                when(it){
//
//                    is Result.Success -> {
//                        userLoginProgressBar.visibility = View.GONE
//                        Toast.makeText(context,"${it.data.message}", Toast.LENGTH_LONG).show()
//                        if(findNavController().currentDestination?.id == R.id.loginFragment){
//                            findNavController().navigate(R.id.action_loginFragment_to_nescafeKitchen)
//                        }
//
//
//                    }
//                    is Result.Error -> {
//                        userLoginProgressBar.visibility = View.GONE
//                        var status:String? = ""
////                        Log.d(TAG, "check inside exception: ${it.exception.message} == HTTP 422")
////                        Log.i(TAG,(it.exception.message?.trim() == "HTTP 422").toString())
////                        Log.i(TAG,(it.exception.message).toString())
//                        if (it.exception.message?.trim() == "HTTP 422"){
//                            status = "No record found"
//                        } else if(it.exception.message?.trim() == "timeout"){
//                            status = "No network"
//                        } else if (it.exception.message?.trim() == "HTTP 400"){
//                            status = "Incomplete login details"
//                        } else if (it.exception.message?.trim() == "Unable to resolve host\"dnestle.herokuapp.com\":No address associated with hostname"){
//                            status = "Please check network connection"
//                        }
////                        status = "No record found"
//                        Log.d(TAG, "check here in status:${status}")
//                        Toast.makeText(
//                            context,
//                            "${status}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//
//            })

            closeKeyboard()

        }




    }

//    private fun login():LiveData<Result<SignInResponse>>{

//        var loginBody = LoginBody()
//
//        val value = IsEmptyCheck(email_edit_text, password_edit_text)
//        val emailAddress = email_edit_text.text.toString().trim()
//        val passwordString = password_edit_text.text.toString().trim()
//
//        if (value){
//            val validation = IsEmptyCheck.fieldsValidation(emailAddress, passwordString)
//
//            if (validation != "true"){
//                Toast.makeText(context, validation, Toast.LENGTH_LONG).show()
//            } else {
//                loginBody = LoginBody(emailAddress,passwordString)
//                login_btn.isClickable = true
//            }
//        } else {
//            login_btn.isClickable = false
//        }

//        when {
//            email_edit_text.editableText.toString().isEmpty() -> {
//                email_edit_text.error = "Email cannot be empty"
//            }
////            InputUtils.validateEmail(email_edit_text.editableText.toString()) -> {
////                email_edit_text.error = "Email not valid"
////            }
//            password_edit_text.editableText.toString().isEmpty() -> {
//                password_edit_text.error = "Password cannot be empty"
//            }
//            else -> {
//                val email = email_edit_text.editableText.toString()
//                val password = password_edit_text.editableText.toString()
//                loginBody = LoginBody(email, password)
//            }
//        }
//        val email = email_edit_text.editableText.toString()
//                val password = password_edit_text.toString()
//                loginBody = LoginBody(email, password)



//        return viewModel.signedIn(loginBody)


//    }

    private fun signInCall(loginBody: LoginBody):LiveData<Result<SignInResponse>>{
       return viewModel.signedIn(loginBody)
    }

    private fun closeKeyboard(){

        val imm = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(view?.windowToken,0)

    }

    private fun isInputFilled():Boolean{
        getLoginInput()
        return when {
            email.isEmpty()
                    || password.isEmpty()
            -> false
            else -> true
        }
    }

    private fun getLoginInput(){
        email = email_textInputLayout.editText?.text.toString()
        password = password_textInputLayout.editText?.text.toString()
    }

    private fun setupTextChangeListeners(){
        binding.run {
            emailTextInputLayout.editText?.addTextChangedListener( object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.apply {
                        email = s.toString()
                    }
                    when (viewModel.validateEmail()){
                        2 -> {
                            emailTextInputLayout.error = getString(R.string.invalid_email_error)
                        }
                        1 -> {
                            emailTextInputLayout.error = getString(R.string.empty_email_error)
                        }
                        0 -> {
                            emailTextInputLayout.isErrorEnabled = false
                            enableSignupButton()
                        }
                    }
                }
            })

            passwordTextInputLayout.editText?.addTextChangedListener( object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.apply {
                        password = s.toString()
                    }
                    when (viewModel.validatePassword()){
                        2 -> {
                            passwordTextInputLayout.error = getString(R.string.invalid_password_error)

                        }
                        1 -> {
                            passwordTextInputLayout.error = getString(R.string.empty_password_error)
                        }
                        0 -> {
                            passwordTextInputLayout.isErrorEnabled = false
                            enableSignupButton()
                        }
                    }
                }
            })

        }

//        email_textInputLayout.editText?.addTextChangedListener( object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.apply {
//                    email = s.toString()
//                }
//                when (viewModel.validateEmail()){
//                    2 -> {
//                        email_textInputLayout.error = getString(R.string.invalid_email_error)
//                    }
//                    1 -> {
//                        email_textInputLayout.error = getString(R.string.empty_email_error)
//                    }
//                    0 -> {
//                        email_textInputLayout.isErrorEnabled = false
//                        enableSignupButton()
//                    }
//                }
//            }
//        })
//
//        password_edit_text.addTextChangedListener( object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.apply {
//                    password = s.toString()
//                }
//                when (viewModel.validatePassword()){
//                    2 -> {
//                        password_textInputLayout.error = getString(R.string.invalid_password_error)
//                    }
//                    1 -> {
//                        password_textInputLayout.error = getString(R.string.empty_password_error)
//                    }
//                    0 -> {
//                        password_textInputLayout.isErrorEnabled = false
//                        enableSignupButton()
//                    }
//                }
//            }
//        })

    }

    private fun enableSignupButton(){
        if (isInputFilled()){
            login_btn.isEnabled = true
        }
    }




}

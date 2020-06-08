package com.example.nescafe_pushcart.ui

import android.content.Context
import android.os.Bundle
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
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.utils.InputUtils
import com.example.nescafe_pushcart.utils.Result
import com.example.nescafe_pushcart.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    val TAG = "LOGIN_FRAGMENT"
    lateinit var viewModel:SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // initialize the viewModel
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        login_btn.setOnClickListener {

            userLoginProgressBar.visibility = View.VISIBLE

            val response = login()


            response.observe(viewLifecycleOwner, Observer {

                Log.d(TAG, "what is inside:$it")
                when(it){

                    is Result.Success -> {
                        userLoginProgressBar.visibility = View.GONE
                        Toast.makeText(context,"${it.data.message}", Toast.LENGTH_LONG).show()
                        if(findNavController().currentDestination?.id == R.id.loginFragment){
                            findNavController().navigate(R.id.action_loginFragment_to_nescafeKitchen)
                        }


                    }
                    is Result.Error -> {
                        userLoginProgressBar.visibility = View.GONE
                        var status:String? = ""
                        Log.d(TAG, "check inside exception: ${it.exception.message}")
//                        if (it.exception.message == "HTTP 422"){
//                            status = "No record found"
//                        }
//                        status = "No record found"
                        Log.d(TAG, "check here in status:${status}")
                        Toast.makeText(
                            context,
                            "${status}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })

            closeKeyboard()

        }




    }

    private fun login():LiveData<Result<SignInResponse>>{

        var loginBody = LoginBody()

        when {
            email_edit_text.editableText.toString().isEmpty() -> {
                email_edit_text.error = "Email cannot be empty"
            }
//            InputUtils.validateEmail(email_edit_text.editableText.toString()) -> {
//                email_edit_text.error = "Email not valid"
//            }
            password_edit_text.editableText.toString().isEmpty() -> {
                password_edit_text.error = "Password cannot be empty"
            }
            else -> {
                val email = email_edit_text.editableText.toString()
                val password = password_edit_text.editableText.toString()
                loginBody = LoginBody(email, password)
            }
        }
//        val email = email_edit_text.editableText.toString()
//                val password = password_edit_text.toString()
//                loginBody = LoginBody(email, password)

        return viewModel.signedIn(loginBody)


    }

    private fun closeKeyboard(){

        val imm = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(view?.windowToken,0)

    }


}

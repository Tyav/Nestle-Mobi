package com.example.nescafe_pushcart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nescafe_pushcart.R
import com.example.nescafe_pushcart.databinding.FragmentVendorAuthBinding
import com.example.nescafe_pushcart.model.login.LoginBody
import com.example.nescafe_pushcart.model.login.SignInResponse
import com.example.nescafe_pushcart.utils.Result
import com.example.nescafe_pushcart.utils.SessionManager
import com.example.nescafe_pushcart.viewModel.VendorAuthFragmentViewModel
import kotlinx.android.synthetic.main.fragment_vendor_auth.*

/**
 * A simple [Fragment] subclass.
 */
class VendorAuthFragment : Fragment() {

    private lateinit var email:String
    private lateinit var password:String

    private lateinit var viewModel:VendorAuthFragmentViewModel
    private lateinit var binding:FragmentVendorAuthBinding
    private lateinit var sessionManager:SessionManager

    val TAG = "VENDORAUTHFRAGMENT"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_vendor_auth, container, false)
        sessionManager = SessionManager(requireContext())

        binding = FragmentVendorAuthBinding.inflate(inflater, container, false)
        // initialize the viewmodel
        viewModel = ViewModelProvider(this).get(VendorAuthFragmentViewModel::class.java)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        login_button.setOnClickListener {

            if(isInputFilled()){
                var loginBody = LoginBody()
                loginBody = LoginBody(email, password)
                val response = vendorSignedInCall(loginBody)

                /*
                    observe the response
                 */
                response.observe(viewLifecycleOwner, Observer {

                    when(it){

                        is Result.Success -> {
                            /*
                            validate the user
                             */
                           val roleContent = it.data.data?.roles
                            for (role in roleContent!!){
                                val myRole = role
                                if (myRole?.name == "VENDOR"){
                                    if(findNavController().currentDestination?.id == R.id.vendorAuthFragment){
                                        findNavController().navigate(R.id.action_vendorAuthFragment_to_newSalesFragment)
                                        Toast.makeText(context,"${it.data.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }


                        }
                        is Result.Error -> {
                            Toast.makeText(context, "${it.exception.message}", Toast.LENGTH_LONG).show()
                        }

                    }

                })
            }




//            findNavController().navigate(R.id.action_vendorAuthFragment_to_newSalesFragment)
        }



    }

    /*
        Receive vendor_user login details
     */
    private fun receiveVendorInput(){
        email = email_textInputLayout.editText?.text.toString()
        password = password_textInputLayout.editText?.text.toString()
    }

    /*
        validate the fields
     */
    private fun isInputFilled():Boolean{
        receiveVendorInput()
        return when {
            email.isEmpty()
                    || password.isEmpty()
            -> false
            else -> true
        }
    }

    private fun vendorSignedInCall(loginBody: LoginBody):LiveData<Result<SignInResponse>>{
        return viewModel.signedInVendor(loginBody)
    }

}

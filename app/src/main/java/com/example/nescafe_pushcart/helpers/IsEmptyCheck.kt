package com.example.nescafe_pushcart.helpers

import com.google.android.material.textfield.TextInputEditText

class IsEmptyCheck {

    companion object {
        operator fun invoke(vararg edits:TextInputEditText):Boolean{
            var value:Boolean? = null
            for(edit in edits){
                if(edit.text.toString().trim().isEmpty()){
                    edit.error = "This field is required"
                    value = false
                }
                else {
                    value = true
                }
            }
            return value!!
        }


        fun fieldsValidation(email:String, password:String):String{

           var message:String? = null
            val emailPattern = Regex("""^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*${'$'}""")
            val passwordPattern = Regex("""^[a-zA-Z0-9@$!%*#?&]{6,}$""")
            val matchedEmail = emailPattern.matches(email)
            val matchedPassword = passwordPattern.matches(password)

            if (!matchedEmail){
                message = "Invalid Email address"
                return message
            } else if (!matchedPassword){
                message = "Invalid password pattern"
                return message
            }
            message = "true"
            return message

        }


    }

}
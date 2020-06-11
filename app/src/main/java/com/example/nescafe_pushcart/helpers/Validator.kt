package com.example.nescafe_pushcart.helpers

class Validator {

    companion object {
        private const val PHONE_PATTERN =
            ("(\\+[0-9]+[\\- \\.]*)?"
                    + "(\\([0-9]+\\)[\\- \\.]*)?"
                    + "([0-9][0-9\\- \\.]+[0-9])")
        private const val EMAIL_PATTERN = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*\$"
        private const val PASSWORD_PATTERN =
            "(?=(.[0-9]))((?=.[A-Za-z0-9])(?=.[A-Z])(?=.[a-z]))^.{8,}${'$'}"
        private const val NAME_PATTERN = "[a-zA-z .]{3,}"
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(EMAIL_PATTERN.toRegex())
    }

    fun isValidPhoneNumber(number: String): Boolean {
        return number.matches(PHONE_PATTERN.toRegex())
    }

    fun isValidName(name: String): Boolean {
        return name.matches(NAME_PATTERN.toRegex())
    }

    /**
     * the regex represents a password with at least 1 uppercase character,
     * at least 1 lowercase character, and at least 1 number. password should be
     * at least 8 characters long
     */

    fun isValidPassword(password: String): Boolean {
        return password.matches(PASSWORD_PATTERN.toRegex())
    }

    fun isPasswordConfirmed(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

}
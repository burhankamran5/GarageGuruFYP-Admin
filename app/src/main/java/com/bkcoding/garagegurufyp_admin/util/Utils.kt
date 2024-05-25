package com.bkcoding.garagegurufyp_admin.util

import android.content.Context
import android.widget.Toast
import com.bkcoding.garagegurufyp_admin.R

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

fun isValidPassword(context: Context, password: String): String {
    val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")
    val result = passwordPattern.matches(password)
    return when (!result) {
        true -> {
            when {
                !password.contains("[A-Z]".toRegex()) -> context.getString(R.string.password_uppercase_error)
                !password.contains("[a-z]".toRegex()) -> context.getString(R.string.password_lowercase_error)
                !password.contains("\\d".toRegex()) -> context.getString(R.string.password_digit_error)
                else -> context.getString(R.string.password_length_error)
            }
        }

        else -> ""
    }
}
fun showToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}
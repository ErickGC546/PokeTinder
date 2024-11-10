package com.galindo.erick.poketinder

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    val context: Context
) : ViewModel() {

    val emailError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()
    val registerSuccess = MutableLiveData<Boolean>()
    val registerError = MutableLiveData<Boolean>()

    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }

    fun registerUser(email: String, password: String, confirmPassword: String) {
        if (!validateInputs(email, password, confirmPassword)) return

        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)
        registerSuccess.postValue(true)
    }

    private fun validateInputs(email: String, password: String, confirmPassword: String): Boolean {
        if (email.isEmpty()) {
            emailError.postValue("Es necesario poner tu correo electrónico")
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError.postValue("Ingrese un correo electrónico válido")
            return false
        }
        if (password.isEmpty() || password.length < 8) {
            passwordError.postValue("La contraseña debe tener al menos 8 caracteres")
            return false
        }
        if (password != confirmPassword) {
            passwordError.postValue("Las contraseñas no coinciden")
            return false
        }

        emailError.postValue(null)
        passwordError.postValue(null)
        return true
    }
}

package com.galindo.erick.poketinder

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel

class RegisterViewModel(private val sharedPreferencesRepository: SharedPreferencesRepository) : ViewModel() {

    fun registerUser(context: Context, email: String, password: String, confirmPassword: String): String? {
        // Validación del correo electrónico
        if (email.isEmpty()) return "Es necesario poner tu correo electrónico"
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "Ingrese un correo electrónico válido"

        // Validación de la contraseña
        if (password.isEmpty() || password.length < 8) return "La contraseña debe tener al menos 8 caracteres"
        if (password != confirmPassword) return "Las contraseñas no coinciden"

        // Inicialización de SharedPreferences
        try {
            sharedPreferencesRepository.setSharedPreference(context)
            sharedPreferencesRepository.saveUserEmail(email)
            sharedPreferencesRepository.saveUserPassword(password)
            Log.i("RegisterViewModel", "Registro exitoso: datos guardados en SharedPreferences")
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "Error al guardar los datos de usuario: ${e.message}")
            return "Hubo un problema al guardar los datos de usuario"
        }

        return null // Registro exitoso, sin errores
    }
}

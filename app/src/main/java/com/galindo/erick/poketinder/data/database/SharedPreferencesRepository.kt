package com.galindo.erick.poketinder.data.database
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreferencesRepository {
    companion object {
        private lateinit var sharedPreferences: SharedPreferences
        private const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"
        private const val USER_EMAIL_KEY = "USER_EMAIL_KEY"
        private const val USER_PASSWORD_KEY = "USER_PASSWORD_KEY"
        private const val DEFAULT_VALUE_FOR_EMPTY = ""
    }

    fun setSharedPreference(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    fun getUserEmail(): String {
        return sharedPreferences.getString(USER_EMAIL_KEY, DEFAULT_VALUE_FOR_EMPTY) ?: DEFAULT_VALUE_FOR_EMPTY
    }

    fun getUserPassword(): String {
        return sharedPreferences.getString(USER_PASSWORD_KEY, DEFAULT_VALUE_FOR_EMPTY) ?: DEFAULT_VALUE_FOR_EMPTY
    }

    fun saveUserEmail(email: String) {
        try {
            sharedPreferences.edit()
                .putString(USER_EMAIL_KEY, email)
                .apply()
            Log.i("SharedPreferencesRepo", "Correo guardado exitosamente")
        } catch (e: Exception) {
            Log.e("SharedPreferencesRepo", "Error al guardar el correo: ${e.message}")
        }
    }

    fun saveUserPassword(password: String) {
        try {
            sharedPreferences.edit()
                .putString(USER_PASSWORD_KEY, password)
                .apply()
            Log.i("SharedPreferencesRepo", "Contraseña guardada exitosamente")
        } catch (e: Exception) {
            Log.e("SharedPreferencesRepo", "Error al guardar la contraseña: ${e.message}")
        }
    }
}

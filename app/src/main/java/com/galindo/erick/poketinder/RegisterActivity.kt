package com.galindo.erick.poketinder

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegisterActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtPassword2: EditText
    private lateinit var btnRegister: Button
    private val registerViewModel = RegisterViewModel(SharedPreferencesRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtPassword2 = findViewById(R.id.edtPassword2)
        btnRegister = findViewById(R.id.btnRegister)

        findViewById<FloatingActionButton>(R.id.btnBackClose).setOnClickListener {
            finish()
        }

        btnRegister.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString()
            val confirmPassword = edtPassword2.text.toString()

            val error = registerViewModel.registerUser(this, email, password, confirmPassword)
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                Log.e("RegisterActivity", "Error de registro: $error")
            } else {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                Log.i("RegisterActivity", "Usuario registrado con éxito")
                finish()
            }
        }
    }
}

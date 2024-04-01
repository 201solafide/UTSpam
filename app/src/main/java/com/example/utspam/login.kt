package com.example.utspam

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class login : AppCompatActivity() {
    private lateinit var authentic: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authentic = FirebaseAuth.getInstance()
        buttonLogin.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Isi semua data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            loginUser(email, password)

        }
    }

    private fun loginUser(email: String, password: String){
        authentic.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    val user = authentic.currentUser
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(baseContext, "Login Gagal", Toast.LENGTH_SHORT).show()
                }

            }

    }
    fun goToRegister(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}
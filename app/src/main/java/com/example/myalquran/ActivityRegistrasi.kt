package com.example.myalquran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.myalquran.databinding.ActivityRegistrasiBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityRegistrasi : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var username = ""
    private var email = ""
    private var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebase auth init
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            //validasi data
            validasiDataRegis()
        }
    }

    private fun validasiDataRegis() {
        username= binding.usernameContainerLogin.editText?.text.toString().trim()
        email=binding.emailContainerLogin.editText?.text.toString().trim()
        password=binding.confirmPasswordContainer.editText?.text.toString().trim()
        if (TextUtils.isEmpty(username)){
          //cek username tidak kosong

            binding.etUsername.error="Please enter your user again"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //cek email format
            binding.etEmail.error="Sorry your email format invalid"
        }
        //TextUtils untuk validasi string
        else if (TextUtils.isEmpty(password)) {
                //ketika pass kosong
                binding.etPassowrd.error = "Please enter your email again"
            }else if (password.length<6){
                binding.passwordContainer.error="Password must atleast 6 character long"
            }else{
                firebaseRegistration()
            }
    }

    private fun firebaseRegistration() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, ActivityLogin::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Sorry your sign up is failed", Toast.LENGTH_SHORT).show()
            }
    }

}
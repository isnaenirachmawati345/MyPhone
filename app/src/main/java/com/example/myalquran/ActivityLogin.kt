package com.example.myalquran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.myalquran.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //merupakan init firebase
        firebaseAuth= FirebaseAuth.getInstance()

        //untuk cek user

        binding.btnLogin.setOnClickListener {
            //untuk validasi data login
            validasiDataLogin()
        }
        binding.btnRegislogin.setOnClickListener {
            startActivity(Intent(this, ActivityRegistrasi::class.java))
        }
        binding.cbForget.setOnClickListener {
            setPasswordAgain()
        }
    }

    private fun setPasswordAgain() {
        email = binding.emailContainerLogin.editText?.text.toString().trim()
        password = binding.passwordContainer.editText?.text.toString().trim()

        //untuk validasi data saat login

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //sukses format email
            binding.emailContainerLogin.error ="your email format invalid "
        }
        else if (TextUtils.isEmpty(password)){
            //ketika pass kosong
            binding.passwordContainer.error ="Please enter your password again"
        }
        else{
            firebaseResetPass()
        }
    }

    private fun firebaseResetPass() {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Email Send !!", Toast.LENGTH_SHORT).show()
                }

            }
    }

    private fun validasiDataLogin() {
        //Ambil data login
        email = binding.emailContainerLogin.editText?.text.toString().trim()
        password = binding.passwordContainer.editText?.text.toString().trim()
        //lakukan validasi data login
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //ketika format email invalid
            binding.emailContainerLogin.error = "Sorry your email format invalid"
        }
        else if (TextUtils.isEmpty(password)){
            //kosong password
            binding.emailContainerLogin.error = "Please enter your password again"
        }else {
            //data valid
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT ).show()
            }
    }
}



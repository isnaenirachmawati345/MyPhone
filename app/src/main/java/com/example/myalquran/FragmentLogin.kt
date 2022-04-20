package com.example.myalquran

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myalquran.Dao.User
import com.example.myalquran.Dao.database.UserDb
import com.example.myalquran.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class FragmentLogin : DialogFragment() {
   private var mYdB : UserDb? = null
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

     companion object{
         const val LGUSER = "Login_user"
         const val USERNAME = "username"
         const val PASSWORD = "password"
     }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLogin1.setOnClickListener {
            findNavController().navigate((R.id.action_fragmentLogin_to_fragmentRegister))
        }
        setUsername()
        mYdB = UserDb.getInstance(requireContext())

        val preferences = this.activity?.getSharedPreferences(LGUSER, Context.MODE_PRIVATE)
        if (preferences!!.getString(USERNAME, null) != null) {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentHomeMain)
            val username = preferences.getString(USERNAME, null)
            Toast.makeText(context, "Welcome User $username", Toast.LENGTH_SHORT).show()
        }
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
            dialog?.hide()
        }
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, p2: Int, p3: Int) {
                binding.btnLogin.isEnabled = binding.etEmail.text.toString()
                    .isNotEmpty() && binding.passwordLogin.text.toString().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }
        binding.etEmail.addTextChangedListener(textWatcher)
        binding.passwordLogin.addTextChangedListener(textWatcher)

        binding.btnLogin.setOnClickListener {
            //bawa data email ke home
            if (binding.etEmail.text.toString().isNotEmpty() || binding.passwordLogin.text.toString().isNotEmpty()){
               val editorUs : SharedPreferences.Editor = preferences!!.edit()
               editorUs.putString(USERNAME, binding.etEmail.text.toString())
                editorUs.putString(PASSWORD,binding.passwordLogin.text.toString())
                editorUs.apply()
            }
            GlobalScope.async {
                val result = mYdB?.userDao()?.loginUser(
                    binding.etEmail.text.toString(),
                    binding.passwordLogin.text.toString()
                )
                runBlocking(Dispatchers.Main){
                    if (result == false){
                        closeKeyboard()
                        val snackbar = Snackbar.make(it, "Maaf anda gagal masuk, silahkan cek email atau password anda kembali",
                        Snackbar.LENGTH_INDEFINITE)
                        snackbar.setAction("yes"){
                            snackbar.dismiss()
                            binding.etEmail.requestFocus()
                            binding.etEmail.text!!.clear()
                            binding.passwordLogin.text!!.clear()
                        }
                        snackbar.show()
                    }else{
                        Toast.makeText(requireContext(), "Selamat data user ${binding.etEmail.text.toString()}", Toast.LENGTH_LONG).show()
                        val bundle = Bundle().apply {
                            putString(USERNAME,binding.etEmail.text.toString())}
                            findNavController().navigate(R.id.action_fragmentRegister_to_fragmentHomeMain,bundle)
                    }
                }
            }


//            val loginn : SharedPreferences.Editor = preferences!!.edit()
//            //untuk bawa data email ke home
//            when {binding.etEmail.text.toString().isEmpty() || binding.passwordLogin.text.toString().isEmpty() ->
//            {
//                Toast.makeText(requireContext(),"form tidak boleh kosong")
//            }
//
//            }
//        }
        }

        }

    private fun closeKeyboard(){
        val activity = activity as MainActivity
        val view = activity.currentFocus
        if (view!= null){
            val logim = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            logim.hideSoftInputFromWindow(view.windowToken,0)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun setUsername(){
        val username = arguments?.getString(USERNAME)
        if (username.isNullOrEmpty()) {
            binding.etEmail.hint = null
        } else {
            binding.etEmail.setText(username)
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireContext(), theme) {
            override fun onBackPressed() {
                activity?.finish()
            }
        }
    }

}







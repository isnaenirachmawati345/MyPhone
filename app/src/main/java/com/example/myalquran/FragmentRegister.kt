package com.example.myalquran

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.myalquran.Dao.User
import com.example.myalquran.Dao.database.UserDb
import com.example.myalquran.databinding.FragmentRegisterBinding
import kotlinx.coroutines.*
import org.w3c.dom.Node
import org.w3c.dom.Text

@DelicateCoroutinesApi
class FragmentRegister : DialogFragment() {
    private var myDbY: UserDb? = null
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //database
        myDbY = UserDb.getInstance(requireContext())
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, p2: Int, p3: Int) {
                binding.btnSignUp.isEnabled =
                    binding.etUsernameRegistrasi.text.toString().isNotEmpty() &&
                            binding.etEmailRegistrasi.text.toString().isNotEmpty() &&
                            binding.etPasswordRegistrasi.text.toString().isNotEmpty() &&
                            binding.etKonfirmasiPassword.text.toString().isNotEmpty()
                if (start != 0) {
                    binding.etKonfirmasiPassword.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
        binding.etUsernameRegistrasi.addTextChangedListener(textWatcher)
        binding.etEmailRegistrasi.addTextChangedListener(textWatcher)
        binding.etPasswordRegistrasi.addTextChangedListener(textWatcher)
        binding.etKonfirmasiPassword.addTextChangedListener(textWatcher)
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin)
            dialog?.hide()
        }
        binding.btnSignUp.setOnClickListener {
            //bawa data email untuk login
            if (binding.etPasswordRegistrasi.text.toString() != binding.etKonfirmasiPassword.text.toString()) {
                binding.etKonfirmasiPassword.error = "Password tidak sama"
                binding.etKonfirmasiPassword.text?.clear()
                binding.etKonfirmasiPassword.requestFocus()
            } else {
                val objeUser = User(
                    null,
                    binding.etUsernameRegistrasi.text.toString(),
                    binding.etPasswordRegistrasi.text.toString(),
                    binding.etEmailRegistrasi.text.toString()
                )
                GlobalScope.async {
                    val result = myDbY?.userDao()?.insertUser(objeUser)
                    runBlocking (Dispatchers.Main){
                        if (result !=0.toLong()){
                            Toast.makeText(requireContext(),"Registrasi berhasil", Toast.LENGTH_SHORT).show()
                            val bundle = Bundle().apply {
                                putString(FragmentLogin.USERNAME, binding.etEmailRegistrasi.text.toString())
                            }
                            findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin,bundle)
                        }else{
                            Toast.makeText(requireContext(),"Registrasi gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialog?.hide()
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  return object : Dialog(requireContext(), theme) {
            override fun onBackPressed() {
                findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin)
                dialog?.hide()
            }
        }
    }
}
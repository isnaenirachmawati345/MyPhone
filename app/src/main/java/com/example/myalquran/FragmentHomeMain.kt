package com.example.myalquran

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myalquran.adapter.PhonesAdapter
import com.example.myalquran.databinding.FragmentHomeMainBinding
import com.example.myalquran.model.phonelistt.DetailPhone
import com.example.myalquran.model.phonelistt.GetAllListPhone
import com.example.myalquran.service.ApiClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class FragmentHomeMain : Fragment() {
    private var _binding: FragmentHomeMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(FragmentLogin.USERNAME)
        preferences = requireContext().getSharedPreferences(FragmentLogin.LGUSER, Context.MODE_PRIVATE)
        if (preferences.getString(FragmentLogin.USERNAME, null) == null) {
            binding.tvUsername.text = username
        }else {
            binding.tvUsername.text = "${preferences.getString(FragmentLogin.USERNAME, null)}"
        }
        logout()
        fetchPhone()
    }

    private fun fetchPhone() {
        ApiClient.instance.getListPhones().enqueue(
            object : retrofit2.Callback<GetAllListPhone> {
                override fun onResponse(call: Call<GetAllListPhone>, response: Response<GetAllListPhone>) {
                val bdy = response.body()
                    val cde = response.code()
                    if (cde == 200){
                        if (bdy != null) {
                            listShow(bdy.detailPhone)
                        }
                        }else{
                            Toast.makeText(requireContext(), "Maaf sekarang kami sedang sibuk", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<GetAllListPhone>, t: Throwable) {
                    Log.d("failure", t.message.toString())

                }

            }
        )
    }

    private fun listShow(detailPhone: List<DetailPhone>) {
      val adapter = PhonesAdapter(object :  PhonesAdapter.OnClickListener{
          override fun onClickItem(data: DetailPhone) {
          }
      })
        adapter.submitData(detailPhone)
        binding.rvPlaying.adapter=adapter;
        binding.rvListtwo.adapter=adapter
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            val dialogKonfirmasi = AlertDialog.Builder(requireContext())
            dialogKonfirmasi.apply {
                setTitle("Logout")
                setMessage("Apakah anda yakin ingin log out?")
                setNegativeButton("Batal") { dialog, which ->
                    dialog.dismiss()
                }
                setPositiveButton("Ya") { dialog, which ->
                    dialog.dismiss()

                    preferences.edit().clear().apply()
                    findNavController().navigate(R.id.action_fragmentHomeMain_to_fragmentLogin)
                }
            }
            dialogKonfirmasi.show()
        }
    }
}





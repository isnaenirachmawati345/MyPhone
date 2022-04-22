package com.example.myalquran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myalquran.adapter.PhonesListAdapter
import com.example.myalquran.databinding.FragmentMainMenu2Binding
import com.example.myalquran.model.phonelistt.DetailPhone
import com.example.myalquran.model.phonelistt.GetAllListPhone
import com.example.myalquran.service.ApiClient
import com.example.myalquran.service.MainMenuViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentMainMenu : Fragment() {
    //lateinit val firebaseAuth : FirebaseAuth

    private var _binding :FragmentMainMenu2Binding? = null
    private val binding get() = _binding!!
    private lateinit var  viewModel : MainMenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenu2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainMenuViewModel::class.java)
        fetchAllDatalistPhone()


        binding.tvUsername.setOnClickListener {
            viewModel.uncensored()
        }
        viewModel.username.observe(viewLifecycleOwner,{
            binding.tvUsername.text = it
        })
    }
    private fun fetchAllDatalistPhone() {
        ApiClient.instance.getListPhones().enqueue(object  : Callback<GetAllListPhone> {
            override fun onResponse(
                call: Call<GetAllListPhone>,
                response: Response<GetAllListPhone>
            ) {
                val body = response.body()
                val code = response.code()
                if(code == 200){
                    showListPhone(body?.detailPhone)
                    binding.progressBar.visibility=View.GONE
                }
            }

            override fun onFailure(call: Call<GetAllListPhone>, t: Throwable) {
                binding.progressBar.visibility=View.GONE
            }
        })
    }

    private fun showListPhone(data : List<DetailPhone>?) {
        val adapter = PhonesListAdapter(object : PhonesListAdapter.OnClickListener{
            override fun onClickItem(data: DetailPhone) {
                val phone_name = data.phone_name
            }
        })
        adapter.submitData(data)
        binding.rvPhone.adapter = adapter
    }
}
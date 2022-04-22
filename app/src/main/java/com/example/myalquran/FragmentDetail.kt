package com.example.myalquran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myalquran.databinding.FragmentDetailBinding
import com.example.myalquran.service.FragmentDetailViewModel
import com.squareup.picasso.Picasso


class FragmentDetail : Fragment() {
     private var  _binding: FragmentDetailBinding? = null
    private val  binding get() = _binding!!
    private val IMAGE_BASE = "https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series-4-steel.jpg"
    private lateinit var viewModel : FragmentDetailViewModel
    private var args: FragmentDetailArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentDetailViewModel::class.java)

        val spekphone = args.spekPhone

        viewModel.spesifikasiPhone.observe(viewLifecycleOwner){
            Picasso.get().load(IMAGE_BASE+it.phone_images).fit().into(binding.dtPhoto)
            binding.dtNamaHandpone.text=it.brand
            binding.tvDetail.text=it.phone_name
        }
        viewModel.getPhoneSpesifikasi(spekphone)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.ac)
        }
    }
}
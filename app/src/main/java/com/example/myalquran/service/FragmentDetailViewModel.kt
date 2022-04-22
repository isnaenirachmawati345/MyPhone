package com.example.myalquran.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myalquran.model.spesifikasiphone.Data
import com.example.myalquran.model.spesifikasiphone.GetAllSpesifikasi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  FragmentDetailViewModel : ViewModel(){
    private val _spesifikasiPhone: MutableLiveData<Data> = MutableLiveData()
    val spesifikasiPhone: LiveData<Data> = _spesifikasiPhone

    fun getPhoneSpesifikasi(Specsifikasiphone: String) {
        ApiClient.instance.getPhoneSpecification(Specsifikasiphone).enqueue(object : Callback<Data>{
                override fun onResponse(
                    call: Call<Data>,
                    response: Response<Data>
                ) {
                    Log.d("detail", response.code().toString())

                    if (response.isSuccessful) {
                        _spesifikasiPhone.postValue((response.body()))
                    } else {

                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {}
            })
    }
}

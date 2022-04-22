package com.example.myalquran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myalquran.databinding.ItemContentBinding
import com.example.myalquran.model.spesifikasiphone.Data
import com.squareup.picasso.Picasso

class SpesifikasiPhoneAdapter (private val onItemClick: OnClickListener): RecyclerView.Adapter<SpesifikasiPhoneAdapter.ViewHolder>(){
  private val diffCallback = object : DiffUtil.ItemCallback<Data>(){
      override fun areItemsTheSame(oldItem: Data, newItem: Data
      ): Boolean=oldItem.phone_name == newItem.phone_name

      override fun areContentsTheSame(oldItem: Data, newItem: Data
      ): Boolean = oldItem.toString() == newItem.toString()
  }
    private val IMAGE_BASE ="https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series-4-steel.jpg"
    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitData(value: List<Data>?) = differ.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpesifikasiPhoneAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(ItemContentBinding.inflate(inflater,parent,false))
    }



    override fun onBindViewHolder(holder: SpesifikasiPhoneAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let{holder.bind(data)}
    }

    override fun getItemCount(): Int=differ.currentList.size

    inner class ViewHolder(private val  binding: ItemContentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data){
            binding.apply {
                Picasso.get().load(IMAGE_BASE+data.phone_images).fit().into(ivHandphone)
                tvBrands.text = data.brand
                tvHandphone.text = data.phone_name
                root.setOnClickListener {
                    onItemClick.OnClikItem(data)
                }
            }
        }

    }
    interface OnClickListener{
        fun OnClikItem(data: Data)
    }
}


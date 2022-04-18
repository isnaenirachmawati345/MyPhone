package com.example.myalquran.adapter

import android.content.DialogInterface
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myalquran.databinding.FragmentListBinding
import com.example.myalquran.model.phonelistt.DetailPhone
import com.squareup.picasso.Picasso

class ListPhonesAdapter (private val onItemClick: OnClickListener) : RecyclerView.Adapter<ListPhonesAdapter.ViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<DetailPhone>() {
        override fun areItemsTheSame(
            oldItem: DetailPhone,
            newItem: DetailPhone
        ): Boolean = oldItem.toString() == newItem.toString()

        override fun areContentsTheSame(
            oldItem: DetailPhone,
            newItem: DetailPhone
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }
    private val IMAGE_BASE ="https://fdn2.gsmarena.com/vv/bigpic/apple-watch-series-4-steel.jpg"

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<DetailPhone>?) = differ.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPhonesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(FragmentListBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: ListPhonesAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: FragmentListBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: DetailPhone){
            binding.apply {
                //menampilkan gambar dixml
                Picasso.get().load(IMAGE_BASE+data.image).fit().into(ivHandphone)
                tvHandphone.text = data.phone_name
                tvBrands.text = data.brand
                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }

    }

    interface OnClickListener {
        fun onClickItem(data: DetailPhone) {

        }


    }


}

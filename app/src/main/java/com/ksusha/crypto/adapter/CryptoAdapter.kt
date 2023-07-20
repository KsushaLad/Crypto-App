package com.ksusha.crypto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ksusha.crypto.databinding.ItemBinding
import com.ksusha.crypto.response.ResponseCoinsMarkets
import javax.inject.Inject

class CryptoAdapter @Inject constructor() : RecyclerView.Adapter<CryptoAdapter.MyViewHolder>() {

    private lateinit var binding: ItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemBinding.inflate(inflater, parent, false)
        return MyViewHolder()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class MyViewHolder(): RecyclerView.ViewHolder() {

    }

    private val differCallBack =
        object : DiffUtil.ItemCallback<ResponseCoinsMarkets.ResponseCoinsMarketsItem>() {
            override fun areItemsTheSame(
                oldItem: ResponseCoinsMarkets.ResponseCoinsMarketsItem,
                newItem: ResponseCoinsMarkets.ResponseCoinsMarketsItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseCoinsMarkets.ResponseCoinsMarketsItem,
                newItem: ResponseCoinsMarkets.ResponseCoinsMarketsItem
            ): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallBack)

}
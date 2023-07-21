package com.ksusha.crypto.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ksusha.crypto.R
import com.ksusha.crypto.databinding.ItemBinding
import com.ksusha.crypto.response.ResponseCoinsMarkets
import com.ksusha.crypto.utils.Constants.animationDuration
import com.ksusha.crypto.utils.roundToTwoDecimals
import com.ksusha.crypto.utils.toDoubleToFloat
import javax.inject.Inject

class CryptoAdapter @Inject constructor() : RecyclerView.Adapter<CryptoAdapter.MyViewHolder>() {

    private lateinit var binding: ItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemBinding.inflate(inflater, parent, false)
        return MyViewHolder()
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    inner class MyViewHolder(): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseCoinsMarkets.ResponseCoinsMarketsItem) {
            binding.apply {
                tvName.text = item.id
                tvPrice.text = "€${item.currentPrice?.roundToTwoDecimals()}"
                tvSymbol.text = item.symbol?.uppercase()
                imgCrypto.load(item.image) {
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.bitcoin)
                    error(R.drawable.bitcoin)
                }
                lineChart.gradientFillColors =
                    intArrayOf(Color.parseColor("#2a9085"), Color.TRANSPARENT)
                lineChart.animation.duration = animationDuration
                val listData = item.sparklineIn7d?.price.toDoubleToFloat()
                lineChart.animate(listData)
            }
        }
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
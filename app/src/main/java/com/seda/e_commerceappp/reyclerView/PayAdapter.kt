package com.seda.e_commerceappp.reyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.databinding.PaycardBinding

class PayAdapter : RecyclerView.Adapter<PayAdapter.FavoriViewHolder>() {



    class FavoriViewHolder(val binding: PaycardBinding): RecyclerView.ViewHolder(binding.root) {

    }

    private val diffCallback= object : DiffUtil.ItemCallback<productsItem>(){
        override fun areItemsTheSame(oldItem: productsItem, newItem: productsItem): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: productsItem, newItem: productsItem): Boolean {

            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<productsItem>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriViewHolder {
        return FavoriViewHolder(PaycardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavoriViewHolder, position: Int) {
        val currentproduct = differ.currentList[position]
        holder.binding.apply {

            payadi.text = currentproduct.title
            payprice.text =currentproduct.price

            Glide.with(holder.itemView)
                .load(currentproduct.image)
                .into(payimage)
        }



    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}
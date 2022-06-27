package com.seda.e_commerceappp.reyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.databinding.NewAllCardBinding

class MostPopularAdapter(private val clickListener:(productsItem)->Unit) : RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    var onLongClickListener: ((productsItem)->Unit)?=null


    class PopularMealViewHolder(val binding:NewAllCardBinding):RecyclerView.ViewHolder(binding.root) {

    }
    private val diffCallback= object : DiffUtil.ItemCallback<productsItem>(){
        override fun areItemsTheSame(oldItem:productsItem, newItem:productsItem): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem:productsItem, newItem: productsItem): Boolean {

            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<productsItem>) = differ.submitList(list)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(NewAllCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        val currentproduct =  differ.currentList[position]
        holder.binding.apply {
            titlee.text = currentproduct.title
            price.text = currentproduct.price.toString()
            Glide.with(holder.itemView)
                .load(currentproduct.image)
                .into(imageView)

        }
        holder.itemView.setOnClickListener {
            clickListener(differ.currentList[position])

        }

        holder.binding.toggleButton.setOnClickListener {
            onLongClickListener?.let { it1 -> it1(differ.currentList[position]) }
        }

    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }
    }



package com.seda.e_commerceappp.reyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seda.e_commerceappp.ViewModel.ShopBagViewModel
import com.seda.e_commerceappp.model.productsItem


import com.seda.e_commerceappp.databinding.SepetBinding

class SepetAdapter(val view:Context,  val viewmodel: ShopBagViewModel) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {
    var onLongClickListener: ((productsItem)->Unit)?=null
    class SepetViewHolder(val binding: SepetBinding):RecyclerView.ViewHolder(binding.root){

    }

    private val diffCallback= object : DiffUtil.ItemCallback<productsItem>(){
        override fun areItemsTheSame(oldItem: productsItem, newItem: productsItem): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: productsItem, newItem: productsItem): Boolean {

            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<productsItem>) = differ.submitList(list)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        return SepetViewHolder(SepetBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }
    var count1 = 1
    var sayi1 =1
    var sayi2=1
    val preferences = view.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    val editor =preferences.edit()
    var cevap = 0
    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        val currentproduct =  differ.currentList[position]



        holder.binding.apply {
            giydiAdi.text = currentproduct.title
            priceurun.text = currentproduct.price
            Glide.with(holder.itemView)
                .load(currentproduct.image)
                .into(image2)

            sil.setOnClickListener {
                viewmodel.delete(currentproduct)
            }
        }




        holder.binding.eksi.setOnClickListener {
            count1--


            val sayi= currentproduct.price!!.toDouble().toInt() / (count1)

            holder.binding.priceurun.text = sayi.toString()
            val product1 = productsItem(currentproduct.id,currentproduct.description,currentproduct.category, currentproduct.image,sayi.toString(),currentproduct.rating,currentproduct.title)
            viewmodel.update(product1)
            Log.e("count2","${holder.binding.priceurun.text}")
            holder.binding.sayac.text = count1.toString()
            Log.e("count1","${holder.binding.sayac.text}")

        }




        holder.binding.arti.setOnClickListener {
            count1++


            cevap = currentproduct.price!!.toDouble().toInt() * count1

            holder.binding.priceurun.text = cevap.toString()



            val product1 = productsItem(currentproduct.id,currentproduct.description,currentproduct.category, currentproduct.image,cevap.toString(),currentproduct.rating,currentproduct.title)
            viewmodel.update(product1)



        }
        holder.binding.sayac.text = count1.toString()

    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }


}




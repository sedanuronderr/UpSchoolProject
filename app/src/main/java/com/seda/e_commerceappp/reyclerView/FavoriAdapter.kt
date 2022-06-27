package com.seda.e_commerceappp.reyclerView



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.ViewModel.FavoriViewModel
import com.seda.e_commerceappp.databinding.FavoriCardBinding

class FavoriAdapter(val viewmodel: FavoriViewModel)  : RecyclerView.Adapter<FavoriAdapter.FavoriViewHolder>() {



    class FavoriViewHolder(val binding: FavoriCardBinding): RecyclerView.ViewHolder(binding.root) {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriViewHolder {
        return FavoriViewHolder(FavoriCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavoriViewHolder, position: Int) {
        val currentproduct = differ.currentList[position]
        holder.binding.apply {
            favoriGiysi.text = currentproduct.title
            favoriPrice.text =currentproduct.price
            Glide.with(holder.itemView)
                .load(currentproduct.image)
                .into(favoriImage)
            clearfavori.setOnClickListener {
                viewmodel.delete(currentproduct)
            }
            favoriRanting.rating = currentproduct.rating?.rate.toString().toFloat()
        }



    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}
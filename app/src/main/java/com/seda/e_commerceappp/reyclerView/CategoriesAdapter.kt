package com.seda.e_commerceappp.reyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.seda.e_commerceappp.model.CategoriItem
import com.seda.e_commerceappp.databinding.CategoriesCardBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(),
    Filterable {

    private val categoriList = ArrayList<CategoriItem>()
    var categoriFilterList = ArrayList<CategoriItem>()
    fun setCategory(list: ArrayList<CategoriItem>){
        categoriList.clear()
        categoriList.addAll(list)
        notifyDataSetChanged()
    }

    init {
        categoriFilterList = categoriList
    }

    class CategoriesViewHolder(val binding:CategoriesCardBinding):RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(CategoriesCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.categoriItem = categoriFilterList.get(position)



    }

    override fun getItemCount(): Int {
        return categoriFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val searchText = constraint.toString().lowercase()
                categoriFilterList = if (searchText.isEmpty()) {
                    categoriList
                } else {
                    val resultList = ArrayList<CategoriItem>()
                    for (row in categoriList) {
                        row.label?.let { categoriName ->
                            if (categoriName.lowercase().contains(searchText)


                            ) {
                                resultList.add(row)
                            }
                        }
                    }
                    resultList
                }

                val filterResults = FilterResults()
                filterResults.values = categoriFilterList

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                categoriFilterList = results?.values as ArrayList<CategoriItem>
                notifyDataSetChanged()
            }
        }
    }

}

package com.seda.e_commerceappp.Dashboard

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.seda.e_commerceappp.ViewModel.CategoriesViewModel
import com.seda.e_commerceappp.model.CategoriItem
import com.seda.e_commerceappp.reyclerView.CategoriesAdapter
import com.seda.e_commerceappp.R


import com.seda.e_commerceappp.databinding.FragmentShopBinding

import kotlin.collections.ArrayList


class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding
    private lateinit var categorimvvm: CategoriesViewModel


    private lateinit var categoriAdapter: CategoriesAdapter
    private lateinit var tempcategorilist :ArrayList<CategoriItem>
    private lateinit var newcategorilist :ArrayList<CategoriItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        categorimvvm = ViewModelProvider(
            this
        ).get(CategoriesViewModel::class.java)

        setupRecyclerView()

        with(binding) {

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    binding.categorii!!.filter.filter(newText)


                    return false
                }
            })
        }
    }



    private fun setupRecyclerView(){
        binding.rv.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            tempcategorilist =ArrayList<CategoriItem>()
            newcategorilist =ArrayList<CategoriItem>()
            binding.categorii = CategoriesAdapter()

            adapter = binding.categorii


        }

        categorimvvm.categori.observe(viewLifecycleOwner, Observer{response->

                binding.categorii!!.setCategory(response as ArrayList<CategoriItem>)



        })
    }}
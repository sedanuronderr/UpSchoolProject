package com.seda.e_commerceappp.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.seda.e_commerceappp.ViewModelFactory.FavoriViewModelFactory

import com.seda.e_commerceappp.reyclerView.FavoriAdapter
import com.seda.e_commerceappp.ViewModel.FavoriViewModel
import com.seda.e_commerceappp.databinding.FragmentFavoriteBinding
import com.seda.e_commerceappp.db.FavoriDao
import com.seda.e_commerceappp.db.FavoriDatabase
import com.seda.e_commerceappp.repository.FavoriRepository


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favorimvvm: FavoriViewModel

    private lateinit var favoridb: FavoriDatabase
    private lateinit var  favoriDao : FavoriDao
    private lateinit var sepetAdapter: FavoriAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoridb = FavoriDatabase.invoke(requireContext())
        favoriDao = favoridb.favoriDao()

        val repository = FavoriRepository(favoriDao)
        val viewModelFactory = FavoriViewModelFactory(repository)
        favorimvvm = ViewModelProvider(
            this,viewModelFactory
        ).get(FavoriViewModel::class.java)



        binding.toolbar2.title ="\n" +
                "Favorites"

        prepareRecycler()

        observefavorites()
    }


    private fun prepareRecycler() {
        sepetAdapter = FavoriAdapter(favorimvvm)
        binding.favoriRcycler.apply {
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter =sepetAdapter
        }
    }

    private fun observefavorites() {
        favorimvvm.getAllTomeal.observe(viewLifecycleOwner, Observer { meals->
            sepetAdapter.differ.submitList(meals)



        })
    }

}
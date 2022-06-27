package com.seda.e_commerceappp.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.seda.e_commerceappp.Dashboard.HomeFragmentDirections
import com.seda.e_commerceappp.ViewModel.FavoriViewModel
import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.reyclerView.MostPopularAdapter
import com.seda.e_commerceappp.ViewModel.HomeViewModel
import com.seda.e_commerceappp.ViewModelFactory.FavoriViewModelFactory
import com.seda.e_commerceappp.databinding.FragmentHomeBinding
import com.seda.e_commerceappp.db.FavoriDao
import com.seda.e_commerceappp.db.FavoriDatabase
import com.seda.e_commerceappp.repository.FavoriRepository

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel

    private lateinit var favoridb: FavoriDatabase
    private lateinit var  favoriDao : FavoriDao

    private lateinit var favorimvvm: FavoriViewModel

    private lateinit var popularAdapter: MostPopularAdapter
    private lateinit var urunlist :ArrayList<productsItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm = ViewModelProvider(
            this
        ).get(HomeViewModel::class.java)

        setupRecyclerView(view)

        favoridb = FavoriDatabase.invoke(requireContext())
        favoriDao = favoridb.favoriDao()

        val repository1 = FavoriRepository(favoriDao)
        val viewModelFactory1 = FavoriViewModelFactory(repository1)

        favorimvvm = ViewModelProvider(
            this,viewModelFactory1
        ).get(FavoriViewModel::class.java)

        onPopularItemLongClickListener()
    }



    private fun onPopularItemLongClickListener() {
        popularAdapter.onLongClickListener = {meal->
            favorimvvm.insert1(meal)
            Log.e("cer","$meal")
Toast.makeText(requireContext(),"Favorilere Eklendi",Toast.LENGTH_SHORT).show()
        }
    }



    private fun setupRecyclerView(v:View){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            urunlist = ArrayList<productsItem>()
            popularAdapter = MostPopularAdapter(){
                // Toast.makeText(requireContext(),"basıldı",Toast.LENGTH_LONG).show()
                val gecis = HomeFragmentDirections.actionHomeFragmentToProductFragment(it)
                Navigation.findNavController(v).navigate(gecis)

            }

            adapter = popularAdapter

        }

        homeMvvm.product.observe(viewLifecycleOwner, Observer{response->
            if (response!=null){
                popularAdapter.submitList(response)
            }

        })
    }

}
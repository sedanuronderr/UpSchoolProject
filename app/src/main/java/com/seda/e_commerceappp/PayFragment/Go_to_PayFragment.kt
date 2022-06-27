package com.seda.e_commerceappp.PayFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.repository.ProductRepository
import com.seda.e_commerceappp.R

import com.seda.e_commerceappp.reyclerView.PayAdapter
import com.seda.e_commerceappp.ViewModel.ShopBagViewModel
import com.seda.e_commerceappp.ViewModelFactory.ProductViewModelFactory
import com.seda.e_commerceappp.databinding.FragmentGoToPayBinding
import com.seda.e_commerceappp.db.ProductDao
import com.seda.e_commerceappp.db.ProductDatabase


class Go_to_PayFragment : Fragment() {
    private lateinit var binding:FragmentGoToPayBinding
    private lateinit var homeMvvm: ShopBagViewModel
    private lateinit var db: ProductDatabase
    private lateinit var  prductDao : ProductDao
    private lateinit var payAdapter: PayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGoToPayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = ProductDatabase.invoke(requireContext())
        prductDao = db.productDao()
        val repository = ProductRepository(prductDao)
        val viewModelFactory = ProductViewModelFactory(repository)



        homeMvvm = ViewModelProvider(
            this,viewModelFactory
        ).get(ShopBagViewModel::class.java)
        prepareRecycler(requireContext())
        observefavorites()


        binding.pay.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_go_to_PayFragment_to_successFragment)

            homeMvvm.getAllTomeal.observe(viewLifecycleOwner, Observer { urunler->
                for (urun in urunler){
                    homeMvvm.delete(urun)
                }

            })

        }

    }
    private fun prepareRecycler(v : Context) {
        payAdapter = PayAdapter()
        binding.payrv.apply {
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter = payAdapter
        }
    }

    private fun observefavorites() {
        homeMvvm.getAllTomeal.observe(viewLifecycleOwner, Observer { meals->
            payAdapter.differ.submitList(meals)
            var totalPrice = 0f
            for (i in meals) {
                i.price.let {
                    totalPrice += it!!.toFloat()
                }
            }
            binding.paypricee.text = totalPrice.toString()


        })
    }
}
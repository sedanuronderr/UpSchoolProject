package com.seda.e_commerceappp.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.todolist.repository.ProductRepository
import com.seda.e_commerceappp.Dashboard.ProductFragmentArgs
import com.seda.e_commerceappp.ViewModel.FavoriViewModel
import com.seda.e_commerceappp.ViewModelFactory.FavoriViewModelFactory
import com.seda.e_commerceappp.GlideLoader
import com.seda.e_commerceappp.model.productsItem
import com.seda.e_commerceappp.ViewModelFactory.ProductViewModelFactory

import com.seda.e_commerceappp.ViewModel.ShopBagViewModel
import com.seda.e_commerceappp.Worker.ShoppingWorker
import com.seda.e_commerceappp.db.FavoriDao
import com.seda.e_commerceappp.db.FavoriDatabase
import com.seda.e_commerceappp.db.ProductDao
import com.seda.e_commerceappp.db.ProductDatabase
import com.seda.e_commerceappp.databinding.FragmentProductBinding
import com.seda.e_commerceappp.db.*
import com.seda.e_commerceappp.repository.FavoriRepository
import java.util.*
import java.util.concurrent.TimeUnit


class ProductFragment : Fragment() {
    private lateinit var binding:FragmentProductBinding
   val bundle:ProductFragmentArgs by navArgs()
    private lateinit var db: ProductDatabase
    private lateinit var  prductDao : ProductDao

    private lateinit var favoridb: FavoriDatabase
    private lateinit var  favoriDao : FavoriDao

    private lateinit var favorimvvm: FavoriViewModel
    private var productsave:productsItem?=null



    private lateinit var homeMvvm: ShopBagViewModel

    var chosenYear = 0
    var chosenMonth = 0
    var chosenDay = 0
    var chosenHour = 0
    var chosenMin = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProductBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val gelenad = bundle.urunyaz.title
        val gelentanım = bundle.urunyaz.description
        val gelenimage = bundle.urunyaz.image
        val gelenprice = bundle.urunyaz.price
        val gelenrating = bundle.urunyaz.rating?.rate
        productsave = bundle.urunyaz


        db =ProductDatabase.invoke(requireContext())
        prductDao = db.productDao()
        val repository = ProductRepository(prductDao)
        val viewModelFactory = ProductViewModelFactory(repository)

        homeMvvm = ViewModelProvider(
            this,viewModelFactory
        ).get(ShopBagViewModel::class.java)

        favoridb = FavoriDatabase.invoke(requireContext())
        favoriDao = favoridb.favoriDao()

        val repository1 = FavoriRepository(favoriDao)
        val viewModelFactory1 = FavoriViewModelFactory(repository1)

        favorimvvm = ViewModelProvider(
            this,viewModelFactory1
        ).get(FavoriViewModel::class.java)

//WorkManager


        val today = Calendar.getInstance()

        binding.datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) {_, year, month, day ->
            chosenYear = year
            chosenMonth = month
            chosenDay = day}

        binding.timePicker.setOnTimeChangedListener{_, hour, minute ->
            chosenHour = hour
            chosenMin  = minute
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { chosenHour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { chosenHour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }
            Log.e("time is","$chosenHour : $minute $am_pm")

        }




        binding.ratingBar2.rating = gelenrating!!.toFloat()
        binding.cardPrice.text = gelenprice
        binding.cardTitle.text = gelenad
        binding.cardDescription.text = gelentanım
        GlideLoader(requireContext()).loadUserPicture(gelenimage!!,binding.cardImage)
        oncardClick()
        onFavoriClick()






    }

    private fun onFavoriClick() {
        binding.toggleButton2.setOnClickListener {
            productsave?.let { it1 ->
                favorimvvm.insert1(it1)
                Log.e("favori","${it1}")
                Toast.makeText(requireContext(),"Favorilere Eklendi",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun oncardClick() {
        binding.button2.setOnClickListener {
            productsave?.let { it1 ->
                homeMvvm.insert(it1)
                Log.e("sepet","${it1}")
                Toast.makeText(requireContext(),"Sepete Eklendi",Toast.LENGTH_SHORT).show()

            }

            // 10


        }
    }








}

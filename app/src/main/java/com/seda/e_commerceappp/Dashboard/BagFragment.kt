package com.seda.e_commerceappp.Dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.todolist.repository.ProductRepository
import com.seda.e_commerceappp.model.products
import com.seda.e_commerceappp.R
import com.seda.e_commerceappp.reyclerView.SepetAdapter
import com.seda.e_commerceappp.ViewModel.ShopBagViewModel
import com.seda.e_commerceappp.ViewModelFactory.ProductViewModelFactory
import com.seda.e_commerceappp.Worker.ShoppingWorker
import com.seda.e_commerceappp.databinding.FragmentBagBinding
import com.seda.e_commerceappp.db.ProductDao
import com.seda.e_commerceappp.db.ProductDatabase
import java.util.*
import java.util.concurrent.TimeUnit

class BagFragment : Fragment() {
    private lateinit var binding: FragmentBagBinding
    private lateinit var homeMvvm: ShopBagViewModel

    private lateinit var db: ProductDatabase
    private lateinit var  prductDao : ProductDao

    private var productsave: products?=null
    private lateinit var sepetAdapter: SepetAdapter
    lateinit var  mealıtem :List<products>



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
        binding= FragmentBagBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        //     preferences ?.edit()?.clear()?.commit();


        db = ProductDatabase.invoke(requireContext())
        prductDao = db.productDao()
        val repository = ProductRepository(prductDao)
        val viewModelFactory = ProductViewModelFactory(repository)



        homeMvvm = ViewModelProvider(
            this,viewModelFactory
        ).get(ShopBagViewModel::class.java)


//Log.e("totallll","$total")
        prepareRecycler(requireContext())
        observefavorites()
        observe()

    }

    private fun prepareRecycler(v : Context) {
        sepetAdapter = SepetAdapter(v,homeMvvm)
        binding.recyclerView2.apply {
            layoutManager =  LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter =sepetAdapter
        }
    }

    private fun observefavorites() {
        homeMvvm.getAllTomeal.observe(viewLifecycleOwner, Observer { meals->
            sepetAdapter.differ.submitList(meals)
        for(meal in meals){

                val userSelectedDateTime = Calendar.getInstance()

                userSelectedDateTime.set(chosenYear,chosenMonth,chosenDay,chosenHour,chosenMin)


                val todayDateTime = Calendar.getInstance()
                Log.e("time","${todayDateTime.get(Calendar.HOUR)}")
                Log.e("time","${todayDateTime.get(Calendar.MINUTE)}")
                val delayInSeconds =(userSelectedDateTime.get(Calendar.MINUTE).toLong()+1)
                createWorkRequest( delayInSeconds)
                Log.e("delay","$delayInSeconds")

        }

        })
    }

    private fun observe() {
        binding.button3.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.go_to_PayFragment)
        }

    }

    private fun createWorkRequest(timeDelayInSeconds: Long  ) {
        val myWorkRequest = OneTimeWorkRequestBuilder<ShoppingWorker>()
            .setInitialDelay(timeDelayInSeconds, TimeUnit.MINUTES)
            .setInputData(
                workDataOf(
                    "title" to "Sepetinde ürün kaldı",

                    )
            )
            .build()

        WorkManager.getInstance(requireActivity()).enqueue(myWorkRequest)
    }
}
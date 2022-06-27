package com.seda.e_commerceappp.Loginİslemler

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.seda.e_commerceappp.R
import com.seda.e_commerceappp.databinding.FragmentLoginBinding
import com.seda.e_commerceappp.databinding.FragmentPasswordResetBinding



class PasswordResetFragment : Fragment() {
    private lateinit var _binding : FragmentPasswordResetBinding
    private val binding get() = _binding
    private var email =""
    private lateinit var  auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentPasswordResetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.passwordback.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.loginFragment)
        }
        binding.ResetButton.setOnClickListener {
            reset(it)

        }

    }

    private fun reset(view: View) {

        email = binding.reset.text.toString().trim()
        if(TextUtils.isEmpty(email)){

            showErrorSnackBar("Eksik Girdiniz", "Tekrar Deneyin", view)

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            showErrorSnackBar("Eksi", "Tekrar Deneyin", view)

        }else{

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task->

                    if(task.isSuccessful){
                        task.addOnSuccessListener {
                            Log.e("cevap2","$it")
                        }
                        showErrorSnackBar("Email Spamınızı Kontrol ediniz", "", view)



                    } else{
                        task.addOnFailureListener {
                            Log.e("cevap","$it")
                        }
                        showErrorSnackBar("Başırısız işlem veya The email address is badly formatted.", "Tekrar Deneyin", view)


                    }
                }
        }
    }
    fun showErrorSnackBar(message: String, errorMessage: String, view: View) {
        val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        sb.setAction(errorMessage) {

        }
        sb.setActionTextColor(Color.RED)
        sb.setTextColor(Color.BLUE)
        sb.setBackgroundTint(Color.WHITE)
        sb.show()
    }

}
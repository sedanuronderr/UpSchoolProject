package com.seda.e_commerceappp.Loginİslemler

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.seda.e_commerceappp.R
import com.seda.e_commerceappp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var _binding : FragmentSignUpBinding
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth

    private lateinit var emaill : String
    private lateinit var sifre : String
    private lateinit var ad:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding= FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        binding.Already.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.loginFragment)

        }


        context?.let { FirebaseApp.initializeApp(it) }

        binding.SignUpButton.setOnClickListener {
            validation(it)

        }
    }

    private fun validation(view: View) {

        ad = binding.firstname.text.toString().trim()
        emaill = binding.email.text.toString().trim()
        sifre = binding.password.text.toString().trim()

        if (TextUtils.isEmpty(ad)) {
            showErrorSnackBar("Eksik Girdiniz", "Tekrar Deneyin", view)

        } else if (TextUtils.isEmpty(emaill)) {
            showErrorSnackBar("Eksik Girdiniz", "Tekrar Deneyin", view)

        } else if (TextUtils.isEmpty(sifre)) {
            showErrorSnackBar("Eksik Girdiniz", "Tekrar Deneyin", view)

        } else {
            auth.createUserWithEmailAndPassword(emaill, sifre)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val db = FirebaseFirestore.getInstance()
                        val firebaseUser: FirebaseUser = task.result!!.user!!


                        // Sign in success, update UI with the signed-in user's information
                        showErrorSnackBar("Başarılı Kayıt ", " ", view)
                        //   UserProfileChangeRequest.Builder().setDisplayName(ad).build()
                        //  auth.currentUser!!.updateProfile(update)
                        Navigation.findNavController(view).navigate(R.id.loginFragment)



                    }else {
                        // If sign in fails, display a message to the user.
                        showErrorSnackBar("Bilgileri kontrol ediniz", "Tekrar Deneyin", view)


                    }


                }
        }
    }


    fun showErrorSnackBar(message: String, errorMessage: String, view: View) {
        val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        sb.setAction(errorMessage) {

        }
        sb.setActionTextColor(Color.RED)
        sb.setTextColor(Color.BLACK)
        sb.setBackgroundTint(Color.WHITE)
        sb.show()
    }

}
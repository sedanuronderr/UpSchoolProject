package com.seda.e_commerceappp.Loginİslemler

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.seda.e_commerceappp.R
import com.seda.e_commerceappp.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {
    private lateinit var _binding : FragmentLoginBinding
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth

    private lateinit var email : String
    private lateinit var password : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding= FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        binding.Forgot.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.passwordResetFragment)

        }
        binding.loginback.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.signUpFragment)


        }
        binding.LoginButton.setOnClickListener {
            login(it)


        }
    }

    private fun login(view: View?) {
        email =binding.email2.text.toString().trim()
        password= binding.passwordd.text.toString().trim()



        if (TextUtils.isEmpty(email) ) {
            if (view != null) {
                showErrorSnackBar("Eksik Girdiniz", "Tekrar Deneyin", view)
            }

        }
        else if (TextUtils.isEmpty(password) ) {
            if (view != null) {
                showErrorSnackBar("Eksik Girdiniz", "Tekrar Deneyin", view)
            }

        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information,


                        Toast.makeText(requireContext(),"Başarılı Login", Toast.LENGTH_SHORT).show()
                        if (view != null) {
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_dashboardActivity)
                            findNavController().popBackStack()
                        }
                        auth.signOut()
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
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
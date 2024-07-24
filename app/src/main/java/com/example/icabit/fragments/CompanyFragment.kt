package com.example.icabit.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.icabit.LoginActivity
import com.example.icabit.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CompanyFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val nextButton: AppCompatButton = view.findViewById(R.id.next_button)

        nextButton.setOnClickListener {
            val companyName: String =
                view.findViewById<TextInputLayout>(R.id.company_name).editText?.text.toString()
            val phone: String =
                view.findViewById<TextInputLayout>(R.id.phone).editText?.text.toString()
            val licence: String =
                view.findViewById<TextInputLayout>(R.id.licence_no).editText?.text.toString()
            val email: String =
                view.findViewById<TextInputLayout>(R.id.email).editText?.text.toString()
            val password: String =
                view.findViewById<TextInputLayout>(R.id.password).editText?.text.toString()
            val confirmPassword: String =
                view.findViewById<TextInputLayout>(R.id.confirm_password).editText?.text.toString()

            if (companyName.isEmpty() || phone.isEmpty() || licence.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireActivity(), "Please submit all details", Toast.LENGTH_SHORT)
                    .show()
            } else if (password != confirmPassword) {
                Toast.makeText(requireActivity(), "Passwords don't match", Toast.LENGTH_SHORT)
                    .show()
            } else {
                createUser(email, password)
            }
        }
    }

    private fun createUser(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireActivity(), "Registration successful", Toast.LENGTH_SHORT)
                        .show()
                    auth.signOut()
                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireActivity(), "Registration failed: ${e.message}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

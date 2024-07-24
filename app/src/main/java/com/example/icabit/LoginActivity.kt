package com.example.icabit

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize Firebase auth
        auth = FirebaseAuth.getInstance()

        // Check if user is coming from signup
        val isFromSignup = intent.getBooleanExtra("isFromSignup", false)

        if (!isFromSignup) {
            // check if user already login
            val currentUser: FirebaseUser? = auth.currentUser
            if (currentUser != null) {
                Log.d(
                    "LoginActivity", "User already logged in. Redirecting to DrawerLayoutActivity."
                )
                startActivity(Intent(this, DrawerLayoutActivity::class.java))
                finish()
            }
        }

        // For Login button click
        val loginButton = findViewById<AppCompatButton>(R.id.login_button)
        loginButton.setOnClickListener {
            val username = findViewById<TextInputLayout>(R.id.username).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.password).editText?.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, DrawerLayoutActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Email not registered: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        // For Sign up text Span
        val myTextView: TextView = findViewById(R.id.signUpSpan)
        val text = "Don’t have an account? Sign Up"
        val spannableString = SpannableString(text)

        // Custom color ke liye
        val myColor = ContextCompat.getColor(this, R.color.primary_color)

        // Text ki range pata karne ke liye
        val signStart = text.indexOf("Sign Up")
        val signEnd = signStart + "Sign Up".length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Click hone par CreatAccount activity open karenge
                val intent = Intent(this@LoginActivity, CreatAccount::class.java)
                startActivity(intent)
            }
        }
        spannableString.setSpan(
            clickableSpan, signStart, signEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // TextView ko clickable banate hain
        myTextView.movementMethod = LinkMovementMethod.getInstance()

        spannableString.setSpan(
            ForegroundColorSpan(myColor), signStart, signEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        myTextView.text = spannableString

        // Note: pehle agar ForegroundColorSpan aur baad me clickableSpan ka use karte hain to color property work nahi karegi by default color with underline aayegi
    }
}

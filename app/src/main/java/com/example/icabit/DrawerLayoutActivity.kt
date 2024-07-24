package com.example.icabit

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class DrawerLayoutActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var menuToggle: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawer_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        menuToggle = findViewById(R.id.drawer_menu)

        menuToggle.setOnClickListener(View.OnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        })

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
               val intent=Intent(this,ICabitDashboard::class.java)
               startActivity(intent)
            }
            R.id.change_pass -> {
               val intent=Intent(this , SetPass::class.java)
                startActivity(intent)
                finish()
            }
            R.id.Ticket -> {
                Toast.makeText(this, "home block", Toast.LENGTH_SHORT).show()
            }
            R.id.Star_Ticket -> {
                // Handle star ticket click
            }
            R.id.wallet -> {
                // Handle wallet click
            }
            R.id.Job_cancellation -> {
                // Handle job cancellation click
            }
            R.id.Settings -> {
                // Handle settings click
            }
            R.id.logout -> {
                logout()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.END)
        return true
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

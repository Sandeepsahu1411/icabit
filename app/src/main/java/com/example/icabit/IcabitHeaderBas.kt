package com.example.icabit

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

open class IcabitHeaderBas:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun setupToolbar() {
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)


        val menuToggle = findViewById<ImageView>(R.id.admin_menu_toggle)
        menuToggle.setOnClickListener { view -> showPopupMenu(view) }

    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.admin_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.admin_home -> {
                    val intent = Intent(this, DrawerLayoutActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.admin_history -> {
                    Toast.makeText(this, "history", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.admin_logout -> {
                    logout()
                    true

                }

                else -> false
            }
        }
        popupMenu.show()
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
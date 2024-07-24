package com.example.icabit

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.icabit.adapter.JobTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ICabitDashboard : IcabitHeaderBas() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_icabit_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //#################  for menu open ###########

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar() // Pass true to show menu toggle


        //#################  for tab fragment adapter ###########

        tabLayout = findViewById(R.id.tabb_layout)
        viewPager2 = findViewById(R.id.view_pager)

        val adapter= JobTabAdapter(this,2)
        viewPager2.adapter=adapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position ->
            tab.text=when(position){
                0 -> "Today's Job"
                1 -> "Yesterday's Job"
                else ->null
            }
        }.attach()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}
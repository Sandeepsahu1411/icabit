package com.example.icabit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.icabit.R
import com.example.icabit.adapter.RvAdapter
import com.example.icabit.RvItem


class TodayJobsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: RvAdapter
    private lateinit var jobList: ArrayList<RvItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_today_jobs, container, false)

        val view = inflater.inflate(R.layout.fragment_today_jobs, container, false)

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.rv_today_job)

        // Set the LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Dummy data for testing
        jobList = arrayListOf(
            RvItem("10:00 AM", "Amit", "#212311", "15 ", "$50", "$500"),
            RvItem("11:00 AM", "Suraj", "#212312", "10 ", "$30", "$254"),
            RvItem("09:00 AM", "Vikram", "#215244", "30", "$70", "$300"),
            RvItem("12:00 AM", "Sahu", "#524587", "45", "$63", "$450")


        )

        // Set the adapter
        rvAdapter = RvAdapter(jobList, requireContext())
        recyclerView.adapter = rvAdapter

        return view
    }
}
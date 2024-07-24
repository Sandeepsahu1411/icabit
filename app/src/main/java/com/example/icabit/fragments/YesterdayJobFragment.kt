package com.example.icabit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.icabit.R
import com.example.icabit.RvItem
import com.example.icabit.adapter.RvAdapter


class YesterdayJobFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: RvAdapter
    private lateinit var jobList: ArrayList<RvItem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_yesterday_job, container, false)
        val view=inflater.inflate(R.layout.fragment_yesterday_job,container,false)

        // Initialize the RecyclerView
        recyclerView=view.findViewById(R.id.rv_yesterday_job)

        //set The layout Manager

        recyclerView.layoutManager=LinearLayoutManager(context)

        // testing data

        jobList= arrayListOf(
            RvItem("10:05 PM" ,"sahu", "#214547","21","$21","$200")
        )
        //set the adapter
        rvAdapter= RvAdapter(jobList,requireContext())
        recyclerView.adapter=rvAdapter
        return view


    }


}
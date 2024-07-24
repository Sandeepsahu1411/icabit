package com.example.icabit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.icabit.AddExpensesForm
import com.example.icabit.LoginActivity
import com.example.icabit.R
import com.example.icabit.RvItem

class RvAdapter(private val jobList: List<RvItem>, private val context: Context):RecyclerView.Adapter<RvAdapter.RvViewHolder>() {

inner class RvViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val jobTime: TextView = itemView.findViewById(R.id.job_time)
    val userName: TextView = itemView.findViewById(R.id.user_name)
    val jobId: TextView = itemView.findViewById(R.id.job_id)
    val waitingTime:TextView=itemView.findViewById(R.id.waiting_time)
    val receivedCash:TextView=itemView.findViewById(R.id.recieved_cash)
    val dueCash:TextView=itemView.findViewById(R.id.due_cash)
    val receiveCashButton:AppCompatButton=itemView.findViewById(R.id.receive_cash_btn)
    val waitingTimeButton:AppCompatButton=itemView.findViewById(R.id.waiting_time_btn)
    val addExpensesButton:AppCompatButton=itemView.findViewById(R.id.expenses_btn)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.activity_rv_items,parent,false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val currentItem=jobList[position]
        holder.jobTime.text=currentItem.time
        holder.userName.text=currentItem.userName
        holder.jobId.text=currentItem.jobId
        holder.waitingTime.text=currentItem.waitingTime
        holder.receivedCash.text=currentItem.receivedCash
        holder.dueCash.text=currentItem.dueCash

        // On Button Click

        holder.receiveCashButton.setOnClickListener{
            //bad me lagaunga
        }
        holder.waitingTimeButton.setOnClickListener{
            Toast.makeText(context, "hellow Sandeep", Toast.LENGTH_SHORT).show()
        }
        holder.addExpensesButton.setOnClickListener{
            val intent=Intent(context, AddExpensesForm::class.java)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return jobList.size

    }
}
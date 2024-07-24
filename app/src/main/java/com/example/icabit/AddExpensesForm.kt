package com.example.icabit

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddExpensesForm : IcabitHeaderBas() {
    private lateinit var dateTextView: TextView
    private lateinit var shiftStart: EditText
    private lateinit var shiftEnd: EditText
    private lateinit var totalTime: TextView
    private lateinit var btnAttach1: ImageView
    private lateinit var itemView: ImageView
    private lateinit var btnAttach2: ImageView
    private lateinit var itemView2: ImageView

    private var currentImageView: ImageView? = null
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        currentImageView?.setImageURI(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expenses_form)

        // Initialize views
        itemView = findViewById(R.id.itemView)
        btnAttach1 = findViewById(R.id.btnAttach1)
        itemView2 = findViewById(R.id.itemView2)
        btnAttach2 = findViewById(R.id.btnAttach2)

        // Set default image
        itemView.setImageResource(R.drawable.gallry)
        itemView2.setImageResource(R.drawable.gallry)

        // Set up image picker for first image
        btnAttach1.setOnClickListener {
            currentImageView = itemView
            contract.launch("image/*")
        }

        // Set up image picker for second image
        btnAttach2.setOnClickListener {
            currentImageView = itemView2
            contract.launch("image/*")
        }

        // Set up toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar()

        // Set current date
        dateTextView = findViewById(R.id.dateTextView)
        val sdf = SimpleDateFormat("dd-MMMM-yyyy EEEE", Locale.getDefault())
        val currentDate = sdf.format(Date())
        dateTextView.text = currentDate

        // Time picker setup
        shiftStart = findViewById(R.id.shiftStart)
        shiftEnd = findViewById(R.id.shiftEnd)
        totalTime = findViewById(R.id.shiftTotelTime)

        shiftStart.setOnClickListener { showTimePickerDialog(shiftStart) }
        shiftEnd.setOnClickListener { showTimePickerDialog(shiftEnd) }

        shiftStart.addTextChangedListener { updateTotalTime() }
        shiftEnd.addTextChangedListener { updateTotalTime() }

        // Parking spinner setup
        val parkingOption = arrayOf("--select--", "Heathrow", "Gatwick", "Luton", "Stansted")
        val spinner: Spinner = findViewById(R.id.parking_spinner)
        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, parkingOption) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = super.getDropDownView(position, convertView, parent)
                val textView = view as TextView
                textView.setTextColor(if (position == 0) android.graphics.Color.GRAY else android.graphics.Color.BLACK)
                return view
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun showTimePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val amPm = if (selectedHour < 12) "AM" else "PM"
            val hourIn12Format = if (selectedHour % 12 == 0) 12 else selectedHour % 12
            editText.setText(String.format("%02d:%02d %s", hourIn12Format, selectedMinute, amPm))
        }, hour, minute, false).show()
    }

    private fun updateTotalTime() {
        val startTime = shiftStart.text.toString()
        val endTime = shiftEnd.text.toString()
        if (startTime.isNotEmpty() && endTime.isNotEmpty()) {
            totalTime.text = " ${calculateTotalTime(startTime, endTime)}"
        }
    }

    private fun calculateTotalTime(startTime: String, endTime: String): String {
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date1 = timeFormat.parse(startTime) ?: return "00:00"
        val date2 = timeFormat.parse(endTime) ?: return "00:00"

        var difference = date2.time - date1.time
        if (difference < 0) {
            difference += 24 * 60 * 60 * 1000
        }
        val hours = (difference / (1000 * 60 * 60)).toInt()
        val minutes = (difference / (1000 * 60) % 60).toInt()

        return String.format("%02d:%02d Hr", hours, minutes)
    }
}

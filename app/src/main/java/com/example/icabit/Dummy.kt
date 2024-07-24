package com.example.icabit
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.core.widget.addTextChangedListener

class Dummy: AppCompatActivity() {

    private lateinit var etMiles: EditText
    private lateinit var etFuelCost: EditText
    private lateinit var btnAttach1: Button
    private lateinit var btnAttach2: Button
    private lateinit var btnAttach3: Button
    private lateinit var tvFile1: TextView
    private lateinit var tvFile2: TextView
    private lateinit var tvFile3: TextView
    private lateinit var btnAdd: Button
    private var fileUri1: Uri? = null
    private var fileUri2: Uri? = null
    private var fileUri3: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        etMiles = findViewById(R.id.etMiles)
        etFuelCost = findViewById(R.id.etFuelCost)
        btnAttach1 = findViewById(R.id.btnAttach1)
        btnAttach2 = findViewById(R.id.btnAttach2)
        btnAttach3 = findViewById(R.id.btnAttach3)
        tvFile1 = findViewById(R.id.tvFile1)
        tvFile2 = findViewById(R.id.tvFile2)
        tvFile3 = findViewById(R.id.tvFile3)
        btnAdd = findViewById(R.id.btnAdd)

        btnAttach1.setOnClickListener { openFilePicker(REQUEST_CODE_PICK_FILE1) }
        btnAttach2.setOnClickListener { openFilePicker(REQUEST_CODE_PICK_FILE2) }
        btnAttach3.setOnClickListener { openFilePicker(REQUEST_CODE_PICK_FILE3) }

        etMiles.addTextChangedListener { updateAddButtonState() }
        etFuelCost.addTextChangedListener { updateAddButtonState() }
    }

    private fun openFilePicker(requestCode: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            when (requestCode) {
                REQUEST_CODE_PICK_FILE1 -> {
                    fileUri1 = fileUri
                    tvFile1.text = fileUri?.path?.let { it.substring(it.lastIndexOf('/') + 1) } ?: "No file selected"
                }
                REQUEST_CODE_PICK_FILE2 -> {
                    fileUri2 = fileUri
                    tvFile2.text = fileUri?.path?.let { it.substring(it.lastIndexOf('/') + 1) } ?: "No file selected"
                }
                REQUEST_CODE_PICK_FILE3 -> {
                    fileUri3 = fileUri
                    tvFile3.text = fileUri?.path?.let { it.substring(it.lastIndexOf('/') + 1) } ?: "No file selected"
                }
            }
            updateAddButtonState()
        }
    }

    private fun updateAddButtonState() {
        btnAdd.isEnabled = etMiles.text.isNotEmpty() && etFuelCost.text.isNotEmpty() &&
                (fileUri1 != null || fileUri2 != null || fileUri3 != null)
    }

    companion object {
        private const val REQUEST_CODE_PICK_FILE1 = 1
        private const val REQUEST_CODE_PICK_FILE2 = 2
        private const val REQUEST_CODE_PICK_FILE3 = 3
    }
}

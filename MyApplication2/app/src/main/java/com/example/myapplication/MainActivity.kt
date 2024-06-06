package com.example.formapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNIM: EditText
    private lateinit var editTextNama: EditText
    private lateinit var textViewTanggalLahir: TextView
    private lateinit var radioGroupJenisKelamin: RadioGroup
    private lateinit var radioGroupHubungan: RadioGroup
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNIM = findViewById(R.id.editTextNIM)
        editTextNama = findViewById(R.id.editTextNama)
        textViewTanggalLahir = findViewById(R.id.textViewTanggalLahir)
        radioGroupJenisKelamin = findViewById(R.id.radioGroupJenisKelamin)
        radioGroupHubungan = findViewById(R.id.radioGroupHubungan)
        buttonNext = findViewById(R.id.buttonNext)

        textViewTanggalLahir.setOnClickListener {
            showDatePickerDialog()
        }

        buttonNext.setOnClickListener {
            val nim = editTextNIM.text.toString()
            val nama = editTextNama.text.toString()
            val tanggalLahir = textViewTanggalLahir.text.toString()

            val jenisKelaminId = radioGroupJenisKelamin.checkedRadioButtonId
            val jenisKelamin = findViewById<RadioButton>(jenisKelaminId)?.text.toString()

            val hubunganId = radioGroupHubungan.checkedRadioButtonId
            val hubungan = findViewById<RadioButton>(hubunganId)?.text.toString()

            if (nim.isNotEmpty() && nama.isNotEmpty() && tanggalLahir.isNotEmpty() && jenisKelamin.isNotEmpty() && hubungan.isNotEmpty()) {
                val intent = Intent(this, ReviewActivity::class.java).apply {
                    putExtra("NIM", nim)
                    putExtra("Nama", nama)
                    putExtra("TanggalLahir", tanggalLahir)
                    putExtra("JenisKelamin", jenisKelamin)
                    putExtra("Hubungan", hubungan)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            textViewTanggalLahir.text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }
}

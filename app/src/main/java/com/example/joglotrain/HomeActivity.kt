package com.example.joglotrain

import android.R
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.joglotrain.data.DatabaseContract
import com.example.joglotrain.data.UserModel
import com.example.joglotrain.databinding.ActivityHomeBinding
import com.example.joglotrain.helper.MappingHelper
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var valueKeberangkatan = ""
    private var valueTujuan = ""
    private var valueJumlahPenumpang = 1
    private var valueTanggalKeberangkatan = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!intent.getBooleanExtra("isLogin", false)) {
            overridePendingTransition(0, 0)
        }
        setupBottomNav()
        setupKeberangkatanItem()
        setupTujuanItem()
        setupAction()
    }

    private fun setupAction() {
        binding.ivMinus.setOnClickListener {
            if (valueJumlahPenumpang == 1) return@setOnClickListener
            valueJumlahPenumpang--
            binding.tvValueJumlahPenumpang.text = valueJumlahPenumpang.toString()
        }
        binding.ivPlus.setOnClickListener {
            valueJumlahPenumpang++
            binding.tvValueJumlahPenumpang.text = valueJumlahPenumpang.toString()
        }
        binding.etTanggal.requestFocus()
        binding.etTanggal.setOnClickListener {
            showDatePickerDialog()
        }
        binding.btnCariTiket.setOnClickListener {
            when {
                valueKeberangkatan == "Pilih keberangkatan" -> {
                    Toast.makeText(this, "Mohon isi keberangkatan", Toast.LENGTH_SHORT).show()
                }

                valueTujuan == "Pilih tujuan" -> {
                    Toast.makeText(this, "Mohon isi tujuan", Toast.LENGTH_SHORT).show()
                }

                valueTanggalKeberangkatan.isEmpty() -> {
                    Toast.makeText(this, "Mohon isi tanggal keberangkatan", Toast.LENGTH_SHORT)
                        .show()
                }

                valueKeberangkatan == valueTujuan -> {
                    Toast.makeText(
                        this,
                        "Keberangkatan dan Tujuan tidak boleh sama",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    MappingHelper.jumlahPenumpang = valueJumlahPenumpang
                    MappingHelper.dateTicket = valueTanggalKeberangkatan
                    startActivity(Intent(this, TicketActivity::class.java).apply {
                        if (valueKeberangkatan == "Yogyakarta") {
                            putExtra("data", DatabaseContract.TicketColumns.YOGYASOLO)
                        } else {
                            putExtra("data", DatabaseContract.TicketColumns.SOLOYOGYA)
                        }
                    })
                }
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this@HomeActivity,
            { view, year1, monthOfYear, dayOfMonth1 ->
                val selectedDate = "$dayOfMonth1-${monthOfYear + 1}-$year1"
                binding.etTanggal.setText(selectedDate)
                valueTanggalKeberangkatan = selectedDate
            }, year, month, dayOfMonth
        )

        datePickerDialog.show()
    }

    private fun setupKeberangkatanItem() {
        val items: MutableList<String> = ArrayList()
        items.add("Pilih keberangkatan")
        items.add("Yogyakarta")
        items.add("Solo Balapan")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerKeberangkatan.adapter = adapter
        binding.spinnerKeberangkatan.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    valueKeberangkatan = parentView?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // no-op
                }
            }
    }

    private fun setupTujuanItem() {
        val items: MutableList<String> = ArrayList()
        items.add("Pilih tujuan")
        items.add("Yogyakarta")
        items.add("Solo Balapan")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerTujuan.adapter = adapter
        binding.spinnerTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                valueTujuan = parentView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // no-op
            }
        }
    }

    private fun setupBottomNav() {
        binding.ivTicket.setOnClickListener {
            startActivity(Intent(this, MyTicketActivity::class.java))
            finishAffinity()
        }
        binding.ivProfile.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java).apply {
                putExtra("data", intent.getParcelableExtra<UserModel>("data"))
            })
            finishAffinity()
        }
    }
}
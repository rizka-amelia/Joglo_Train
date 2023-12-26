package com.example.joglotrain

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joglotrain.data.DatabaseContract
import com.example.joglotrain.data.TicketModel
import com.example.joglotrain.data.UserHelper
import com.example.joglotrain.databinding.ActivityTicketBinding
import com.example.joglotrain.helper.MappingHelper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TicketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTicketBinding
    private lateinit var userHelper: UserHelper
    private var destination = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()
        intent.getStringExtra("data")?.let {
            destination = it
            binding.tvKeberangkatan.text = if (destination == DatabaseContract.TicketColumns.YOGYASOLO) "Yogyakarta" else "Solo Balapan"
            binding.tvTujuan.text = if (destination == DatabaseContract.TicketColumns.YOGYASOLO) "Solo Balapan" else "Yogyakarta"
        }
        binding.ivBack.setOnClickListener { finish() }
        getAllTicket()
    }

    private fun getAllTicket() {
        lifecycleScope.launch {
            val deferred: Deferred<ArrayList<TicketModel>> = async(Dispatchers.IO) {
                val cursor = userHelper.queryAllTicket()
                MappingHelper.mapCursorToAllTicket(cursor)
            }
            val data = deferred.await().filter { it.destination == destination }

            if (data.isNotEmpty()) {
                binding.rvTicket.layoutManager = LinearLayoutManager(this@TicketActivity)
                binding.rvTicket.adapter = TicketAdapter(data, destination)
            } else {
                Toast.makeText(
                    this@TicketActivity,
                    "Data kosong!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
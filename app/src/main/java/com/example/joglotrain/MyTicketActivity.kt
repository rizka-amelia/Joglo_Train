package com.example.joglotrain

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joglotrain.data.DatabaseContract
import com.example.joglotrain.data.TicketModel
import com.example.joglotrain.data.UserHelper
import com.example.joglotrain.databinding.ActivityMyTicketBinding
import com.example.joglotrain.helper.MappingHelper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MyTicketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyTicketBinding
    private lateinit var userHelper: UserHelper
    private var valueJumlahPenumpang = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()
        overridePendingTransition(0, 0)
        setupBottomNav()
        getAllMyTickets()
    }

    private fun getAllMyTickets() {
        lifecycleScope.launch {
            val deferred: Deferred<ArrayList<TicketModel>> = async(Dispatchers.IO) {
                val cursor = userHelper.queryAllMyTicket()
                MappingHelper.mapCursorToMyTickets(cursor)
            }
            val data = deferred.await()

            if (data.size > 0) {
                binding.rvMyTicket.layoutManager = LinearLayoutManager(this@MyTicketActivity)
                binding.rvMyTicket.adapter = MyTicketAdapter(data) {
                    valueJumlahPenumpang = it.jumlahPenumpang
                    showDialogUpdateDelete(it)
                }
            } else {
                Toast.makeText(
                    this@MyTicketActivity,
                    "Data kosong!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showDialogUpdateDelete(data: TicketModel) {
        val builder = AlertDialog.Builder(this)

        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.alert_dialog_my_ticket, null)

        builder.setView(dialogView)
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.cancel()
            }

        val alertDialog = builder.create()
        alertDialog.show()

        val tvValueJumlahPenumpang = dialogView.findViewById<TextView>(R.id.tvValueJumlahPenumpang)
        tvValueJumlahPenumpang.text = data.jumlahPenumpang.toString()
        dialogView.findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            dialogView.findViewById<ConstraintLayout>(R.id.clDialog).isVisible = false
            dialogView.findViewById<ConstraintLayout>(R.id.clUpdateJumlahPenumpang).isVisible = true
            dialogView.findViewById<ImageView>(R.id.ivMinus).setOnClickListener {
                if (valueJumlahPenumpang == 1) return@setOnClickListener
                valueJumlahPenumpang--
                tvValueJumlahPenumpang.text = valueJumlahPenumpang.toString()
            }
            dialogView.findViewById<ImageView>(R.id.ivPlus).setOnClickListener {
                valueJumlahPenumpang++
                tvValueJumlahPenumpang.text = valueJumlahPenumpang.toString()
            }
            dialogView.findViewById<Button>(R.id.btnUpdateJumlahPenumpang).setOnClickListener {
                val values = ContentValues().apply {
                    put(DatabaseContract.CheckoutColumns.DATE, data.date)
                    put(DatabaseContract.CheckoutColumns.PASSANGERNAME, data.passangerName)
                    put(DatabaseContract.CheckoutColumns.DESTINATION, data.destination)
                    put(DatabaseContract.CheckoutColumns.DURATION, data.duration)
                    put(DatabaseContract.CheckoutColumns.PRICE, data.price)
                    put(DatabaseContract.CheckoutColumns.JUMLAHPENUMPANG, valueJumlahPenumpang)
                }
                val rowId = userHelper.updateMyTicketById(data.id.toString(), values)
                if (rowId > 0) {
                    Toast.makeText(this, "Berhasil update jumlah penumpang!", Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                    getAllMyTickets()
                } else {
                    Toast.makeText(this, "Gagal update jumlah penumpang!", Toast.LENGTH_SHORT).show()
                }
            }

        }

        dialogView.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val rowId = userHelper.deleteMyTicketById(data.id.toString())
            if (rowId > 0) {
                Toast.makeText(this, "Berhasil hapus tiket!", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
                getAllMyTickets()
            } else {
                Toast.makeText(this, "Gagal hapus tiket!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupBottomNav() {
        binding.ivHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finishAffinity()
        }
        binding.ivProfile.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
            finishAffinity()
        }
    }
}
package com.example.joglotrain

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.joglotrain.data.DatabaseContract
import com.example.joglotrain.data.UserHelper
import com.example.joglotrain.databinding.ActivityCheckoutTicketBinding
import com.example.joglotrain.helper.MappingHelper
import java.text.NumberFormat
import java.util.Locale

@SuppressLint("SetTextI18n")
class CheckoutTicket : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvValueJumlahPenumpang.text = "${MappingHelper.jumlahPenumpang} x Rp ${
            NumberFormat.getNumberInstance(
                Locale("in", "ID")
            ).format(MappingHelper.hargaTicket)
        }"
        binding.tvValueTotalHarga.text = "Rp ${
            NumberFormat.getNumberInstance(Locale("in", "ID")).format(
                MappingHelper.hargaTicket * MappingHelper.jumlahPenumpang
            )
        }"

        binding.btnPayment.setOnClickListener {
            when {
                binding.etName.text.toString().isEmpty() -> {
                    binding.etName.error = "Mohon isi nama"
                }

                binding.etEmail.text.toString().isEmpty() -> {
                    binding.etEmail.error = "Mohon isi email"
                }

                binding.etPhoneNumber.text.toString().isEmpty() -> {
                    binding.etPhoneNumber.error = "Mohon isi nomor telepon"
                }

                binding.etKTP.text.toString().isEmpty() -> {
                    binding.etKTP.error = "Mohon isi nomor KTP"
                }

                binding.etPassangerName.text.toString().isEmpty() -> {
                    binding.etPassangerName.error = "Mohon isi nama penumpang"
                }

                else -> {
                    val values = ContentValues().apply {
                        put(DatabaseContract.CheckoutColumns.DATE, MappingHelper.dateTicket)
                        put(DatabaseContract.CheckoutColumns.PASSANGERNAME, binding.etPassangerName.text.toString().trim())
                        put(DatabaseContract.CheckoutColumns.DESTINATION, MappingHelper.destination)
                        put(DatabaseContract.CheckoutColumns.DURATION, MappingHelper.duration)
                        put(DatabaseContract.CheckoutColumns.PRICE, MappingHelper.hargaTicket)
                        put(DatabaseContract.CheckoutColumns.JUMLAHPENUMPANG, MappingHelper.jumlahPenumpang)
                    }
                    val userHelper = UserHelper.getInstance(applicationContext)
                    userHelper.open()
                    val result = userHelper.insertCheckoutTicket(values)

                    if (result > 0) {
                        Toast.makeText(this, "Berhasil membeli tiket!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MyTicketActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Gagal membeli tiket",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
package com.example.joglotrain

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.joglotrain.auth.LoginActivity
import com.example.joglotrain.databinding.ActivityAccountBinding
import com.example.joglotrain.helper.MappingHelper

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(0, 0)
        setupBottomNav()

        binding.run {
            val dataUser = MappingHelper.dataUser
            etName.setText(dataUser.username)
            etPhoneNumber.setText(dataUser.phoneNumber)
            etEmail.setText(dataUser.email)

            btnLogout.setOnClickListener {
                startActivity(Intent(this@AccountActivity, LoginActivity::class.java))
                finishAffinity()
            }
        }
    }

    private fun setupBottomNav() {
        binding.ivHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finishAffinity()
        }
        binding.ivTicket.setOnClickListener {
            startActivity(Intent(this, MyTicketActivity::class.java))
            finishAffinity()
        }
    }
}
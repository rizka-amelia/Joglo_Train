package com.example.joglotrain.auth

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.joglotrain.R
import com.example.joglotrain.data.DatabaseContract
import com.example.joglotrain.data.UserHelper
import com.example.joglotrain.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userHelper: UserHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            when {
                binding.etUsername.text.toString().isEmpty() -> {
                    binding.etUsername.error = "Username tidak boleh kosong!"
                    binding.etUsername.requestFocus()
                }

                binding.etPhoneNumber.text.toString().isEmpty() -> {
                    binding.etPhoneNumber.error = "Phone Number tidak boleh kosong!"
                    binding.etPhoneNumber.requestFocus()
                }

                binding.etEmail.text.toString().isEmpty() -> {
                    binding.etEmail.error = "Email tidak boleh kosong!"
                    binding.etEmail.requestFocus()
                }

                binding.etPassword.text.toString().isEmpty() -> {
                    binding.etPassword.error = "Password tidak boleh kosong!"
                    binding.etPassword.requestFocus()
                }

                else -> {
                    val values = ContentValues().apply {
                        put(DatabaseContract.UserColumns.USERNAME, binding.etUsername.text.toString().trim())
                        put(DatabaseContract.UserColumns.PHONENUMBER, binding.etPhoneNumber.text.toString().trim())
                        put(DatabaseContract.UserColumns.EMAIL, binding.etEmail.text.toString().trim())
                        put(DatabaseContract.UserColumns.PASSWORD, binding.etPassword.text.toString().trim())
                    }
                    val result = userHelper.insert(values)

                    if (result > 0) {
                        Toast.makeText(this, "Berhasil daftar!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Gagal Daftar akun",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }

    }
}
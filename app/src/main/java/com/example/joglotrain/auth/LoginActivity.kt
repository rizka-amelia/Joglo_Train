package com.example.joglotrain.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.joglotrain.HomeActivity
import com.example.joglotrain.data.UserHelper
import com.example.joglotrain.data.UserModel
import com.example.joglotrain.databinding.ActivityLoginBinding
import com.example.joglotrain.helper.MappingHelper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userHelper: UserHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            when {
                binding.etUsername.text.toString().isEmpty() -> {
                    binding.etUsername.error = "Username tidak boleh kosong!"
                    binding.etUsername.requestFocus()
                }

                binding.etPassword.text.toString().isEmpty() -> {
                    binding.etPassword.error = "Password tidak boleh kosong!"
                    binding.etPassword.requestFocus()
                }

                else -> {
                    lifecycleScope.launch {
                        val deferred: Deferred<UserModel> = async(Dispatchers.IO) {
                            val cursor = userHelper.queryByUsername(
                                binding.etUsername.text.toString().trim()
                            )
                            MappingHelper.mapCursorToUserModel(cursor)
                        }
                        val user = deferred.await()

                        if (user.username == binding.etUsername.text.toString().trim() && user.password == binding.etPassword.text.toString().trim()) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Berhasil login!",
                                Toast.LENGTH_SHORT
                            ).show()
                            MappingHelper.dataUser = user
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java).apply {
                                putExtra("isLogin", true)
                            })
                            finishAffinity()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Username/password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}
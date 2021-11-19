package com.jonareas.pylearn.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jonareas.pylearn.MainActivity
import com.jonareas.pylearn.R
import com.jonareas.pylearn.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
     //credenciales
    private val USER_CREDENTIAL = "root"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(loginBinding.root)
        loginBinding.buttonLogin.setOnClickListener {
            // To-be-replaced in future updates
            verifyCredentials()
        }
    }

    private fun showLoadingScreen() {
        loginBinding.mainLayoutLogin.visibility = View.GONE
        loginBinding.lottieLoading.visibility = View.VISIBLE

    }

    private fun verifyCredentials() {
        with(loginBinding) {
            val password = editTextLoginPassword.text.toString()
            val user = editTextLoginUsername.text.toString()

            when {
                user != USER_CREDENTIAL -> Snackbar.make(
                    loginBinding.root,
                    "El usuario ingresado no está registrado",
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null)
                    .show()
                password != USER_CREDENTIAL -> Snackbar.make(
                    loginBinding.root,
                    "La contraseña ingresada es inválida",
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null)
                    .show()
                else -> setUpMainScreen()
            }
        }
    }

    private fun loadLevels() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun setUpMainScreen() {
        showLoadingScreen()

        // Just to flex the loadingScreen Animation, remove if required for performance reasons
        Handler().postDelayed({
            loadLevels()
        }, 5000)

    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

}


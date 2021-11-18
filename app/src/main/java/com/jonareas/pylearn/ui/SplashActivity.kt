package com.jonareas.pylearn.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.jonareas.pylearn.R
import com.jonareas.pylearn.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    lateinit var splashBinding: ActivitySplashBinding

    var handler: Handler? = null
    private val displayLength = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(splashBinding.root)
        SplashStart()

        splashBinding.textViewLogo.startAnimation(AnimationUtils.loadAnimation(splashBinding.root.context,R.anim.slide_from_bottom))
        splashBinding.textViewAuthor.startAnimation(AnimationUtils.loadAnimation(splashBinding.root.context, R.anim.slide_from_bottom))
        splashBinding.constraintLayoutPylearnInfo.startAnimation(AnimationUtils.loadAnimation(splashBinding.root.context,R.anim.slide_from_bottom))

    }

    private fun SplashStart() {
        handler = Handler()
        handler!!.postDelayed(Runnable {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, displayLength.toLong())
    }


    override fun onResume() {
        super.onResume()
    }
}

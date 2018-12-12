package com.ky.kktyu.a5w2hmemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.startActivity

class BootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser

        if (user == null) {
            finish()
            beginSetUp()
        } else {
            beginMemo()
        }
    }

    private fun beginSetUp() {
        finish()
        startActivity<LoginActivity>()
    }

    private fun beginMemo() {
        finish()
        startActivity<MainActivity>()
    }
}

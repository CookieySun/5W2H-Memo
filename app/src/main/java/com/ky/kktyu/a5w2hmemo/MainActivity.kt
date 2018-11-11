package com.ky.kktyu.a5w2hmemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    val USER = UserModel()
    var MEMO = MemoModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance().currentUser

        if (user == null) {
            finish()
            beginSetUp()
        } else {
            beginMemo(user)
        }
    }

    private fun beginSetUp() {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
    }

    private fun beginMemo(userId: FirebaseUser) {
        USER.id = userId.uid

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MemosFragment())
            .commit()
    }
}

package com.example.kktyu.a5w2hmemo

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class MainActivity : AppCompatActivity() {

    val USER = IndividualModel()
    var MEMO = RowModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = PreferenceManager.getDefaultSharedPreferences(this)
            .getString("USER_ID", null)

        val user = FirebaseAuth.getInstance().currentUser


        if (user == null) {
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

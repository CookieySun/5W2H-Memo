package com.ky.kktyu.a5w2hmemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val USER = UserModel()
    var MEMO = MemoModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance().currentUser
        USER.id = user!!.uid

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MemosFragment())
            .commit()
    }
}

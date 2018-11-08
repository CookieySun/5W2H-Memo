package com.example.kktyu.a5w2hmemo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_memo.*


class AddMemoFragment : Fragment() {
    private lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            this.activity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resistBtn.setOnClickListener {
            if (whatText.text.isBlank())
                Toast.makeText(activity, "whatは必須です", Toast.LENGTH_SHORT).show()
            else
                registration()
        }
    }

    private fun registration() {
        val db = FirebaseFirestore.getInstance()

        // Create a new user with a first and last names
        val memo = HashMap<String, Any>().apply {
            put(resources.getString(R.string.when_), whenText.text.toString())
            put(resources.getString(R.string.where), whereText.text.toString())
            put(resources.getString(R.string.who), whoText.text.toString())
            put(resources.getString(R.string.what), whatText.text.toString())
            put(resources.getString(R.string.why), whyText.text.toString())
            put(resources.getString(R.string.how), howText.text.toString())
            put(resources.getString(R.string.how_many__how_match), howManyHowMatchText.text.toString())
        }

        // Add a new document with a generated ID
        db.collection(activity.USER.id)
            .add(memo)
            .addOnSuccessListener {
                Toast.makeText(activity, "登録完了", Toast.LENGTH_SHORT).show()
                fragmentManager!!.popBackStack()
            }
            .addOnFailureListener { e -> Log.w("MainActivity", "Error adding document", e) }
    }
}

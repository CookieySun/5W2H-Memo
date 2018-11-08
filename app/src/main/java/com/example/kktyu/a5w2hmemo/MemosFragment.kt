package com.example.kktyu.a5w2hmemo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_memos.*


class MemosFragment : Fragment() {
    private lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            this.activity = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_memos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = FirebaseFirestore.getInstance()

        val ids = mutableListOf<String>()
        val whens = mutableListOf<String>()
        val wheres = mutableListOf<String>()
        val whos = mutableListOf<String>()
        val whats = mutableListOf<String>()
        val whys = mutableListOf<String>()
        val hows = mutableListOf<String>()
        val howMenyHowMachs = mutableListOf<String>()

        //RecyclerView
        val recyclerView = my_recycler_view

        db.collection(activity.USER.id)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        Log.d("loglog", document.data.toString())
                        ids.add(document.id)
                        whens.add(document.getString(resources.getString(R.string.when_)) ?: "")
                        wheres.add(document.getString(resources.getString(R.string.where)) ?: "")
                        whos.add(document.getString(resources.getString(R.string.who)) ?: "")
                        whats.add(document.getString(resources.getString(R.string.what)) ?: "")
                        whys.add(document.getString(resources.getString(R.string.why)) ?: "")
                        hows.add(document.getString(resources.getString(R.string.how)) ?: "")
                        howMenyHowMachs.add(
                            (document.getString(resources.getString(R.string.how_many__how_match))
                                ?: "")
                        )
                    }
                    val adapter =
                        ViewAdapter(
                            createDataList(
                                ids,
                                whens,
                                wheres,
                                whos,
                                whats,
                                whys,
                                hows,
                                howMenyHowMachs
                            ), object : ViewAdapter.ListListener {
                                override fun onClickRow(tappedView: View, rowModel: RowModel) {
                                    activity.MEMO = rowModel
                                    fragmentManager!!.beginTransaction().apply {
                                        addToBackStack(null)
                                        replace(R.id.fragment_container, ViewMemoFragment())
                                        commit()
                                    }
                                }
                            })

                    recyclerView.setHasFixedSize(true)
                    recyclerView.layoutManager = GridLayoutManager(activity, 2)
                    recyclerView.adapter = adapter
                } else {
                }
            }

        //FloatingActionButton
        floatingActionButton.setOnClickListener {
            fragmentManager!!.beginTransaction().apply {
                addToBackStack(null)
                replace(R.id.fragment_container, AddMemoFragment())
                commit()
            }
        }


    }

    private fun createDataList(
        ids: MutableList<String>,
        what_s: MutableList<String>,
        wheres: MutableList<String>,
        whos: MutableList<String>,
        whats: MutableList<String>,
        whys: MutableList<String>,
        hows: MutableList<String>,
        howMenyHowMachs: MutableList<String>
    ): List<RowModel> {
        val dataList = mutableListOf<RowModel>()

        for ((i, what) in whats.withIndex()) {
            dataList.add(RowModel().also {
                it.id = ids[i]
                it.when_ = what_s[i]
                it.where = wheres[i]
                it.who = whos[i]
                it.what = what
                it.why = whys[i]
                it.how = hows[i]
                it.howMenyHowMach = howMenyHowMachs[i]
            })
        }

        return dataList
    }

}

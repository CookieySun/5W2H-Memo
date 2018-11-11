package com.ky.kktyu.a5w2hmemo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_view_memo.*

class ViewMemoFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_view_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        whenText.text = activity.MEMO.when_
        whereText.text = activity.MEMO.where
        whoText.text = activity.MEMO.who
        whatText.text = activity.MEMO.what
        whyText.text = activity.MEMO.why
        howText.text = activity.MEMO.how
        howManyHowMatchText.text = activity.MEMO.howMenyHowMach
    }
}

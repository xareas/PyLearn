package com.xareas.pylearn.ui.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xareas.pylearn.R
import com.xareas.pylearn.adapter.DictionaryAdapter
import kotlinx.android.synthetic.main.fragment_dictionary.*

class DictionaryFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private  var adapter: RecyclerView.Adapter<DictionaryAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dictionary,container,false    )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply { layoutManager = LinearLayoutManager(activity)
            adapter = DictionaryAdapter()
        }
    }
}
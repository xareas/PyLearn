package com.xareas.pylearn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xareas.pylearn.R

class DictionaryAdapter: RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {
    val titles = arrayOf("Variables","If - Else","Ciclo For","Funciones","Operadores")
    val details = arrayOf("Concepto No 1","Concepto No 2","Concepto No 3","Concepto No 4","Concepto No 5")
    val images = intArrayOf(R.drawable.uno,R.drawable.dos,R.drawable.tres,R.drawable.cuatro,R.drawable.cinco )

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetal: TextView


        init {

            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.title_concept)
            itemDetal = itemView.findViewById(R.id.n_concept)

        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_dictionary,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetal.text = details[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }


}
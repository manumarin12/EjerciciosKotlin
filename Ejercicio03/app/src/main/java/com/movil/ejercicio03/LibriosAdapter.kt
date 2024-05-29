package com.movil.ejercicio03


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibrosAdapter (private val datas: List<Data>):
    RecyclerView.Adapter<LibrosAdapter.LibrosViewHolder>(){

    class LibrosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        private val txtRelease = itemView.findViewById<TextView>(R.id.txtRelease)
        private val txtPage = itemView.findViewById<TextView>(R.id.txtPage)

        fun bind(data: Data){
            txtTitle.text = data.number + " - " + data.title
            txtRelease.text = data.releaseDate
            txtPage.text = data.pages
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_libros,parent,false)

        return LibrosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val data = datas[position]
        holder.bind(data)
    }


}
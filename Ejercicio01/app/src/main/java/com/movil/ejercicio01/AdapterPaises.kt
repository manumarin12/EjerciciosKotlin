package com.movil.ejercicio01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import  com.movil.ejercicio01.databinding.ListItemsPaisesBinding


class AdapterPaises(private val paises: List<Paises>) : RecyclerView.Adapter<AdapterPaises.PaisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val binding = ListItemsPaisesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val pais = paises[position]
        holder.bind(pais)
    }

    override fun getItemCount(): Int = paises.size

    class PaisViewHolder(private val binding: ListItemsPaisesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pais: Paises) {
            binding.txtPais.text = pais.name.common
            binding.txtCapital.text = if (pais.capital.isNotEmpty()) pais.capital[0] else "No Capital"
            Glide.with(binding.imageView.context)
                .load(pais.flags.png)
                .into(binding.imageView)
        }
    }
}
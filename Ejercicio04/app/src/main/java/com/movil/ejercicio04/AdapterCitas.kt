package com.movil.ejercicio04

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movil.ejercicio04.databinding.ItemsListCitasBinding

class AdapterCitas(private val citas: List<Citas>) : RecyclerView.Adapter<AdapterCitas.CitasViewHolder>() {

    // Clase interna que representa cada elemento de la lista en la vista
    class CitasViewHolder(private val binding: ItemsListCitasBinding) : RecyclerView.ViewHolder(binding.root) {
        // Función para vincular el valor con el campo del itemView
        fun bind(citas: Citas) {
            binding.txtcita.text = citas.date
            binding.txtlocal.text = citas.localName
        }
    }

    // Método llamado cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitasViewHolder {
        val binding = ItemsListCitasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitasViewHolder(binding)
    }

    // Método que devuelve el número total de elementos en la lista
    override fun getItemCount(): Int = citas.size

    // Método llamado cuando RecyclerView necesita mostrar un elemento en una posición específica
    override fun onBindViewHolder(holder: CitasViewHolder, position: Int) {
        val cita = citas[position]
        holder.bind(cita)
    }
}

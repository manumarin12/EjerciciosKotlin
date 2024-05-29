package com.movil.ejercicio04_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movil.ejercicio04_1.databinding.ItemsBooksBinding

class AdapterCasos(private val casos: List<Casos>) : RecyclerView.Adapter<AdapterCasos.CasosViewHolder>() {

    // Clase interna que representa cada elemento de la lista en la vista
    class CasosViewHolder(private val binding: ItemsBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        // Función para vincular el valor con el campo del itemView
        fun bind(caso: Casos) {
            binding.txtFecha.text = "Fecha: " + caso.date
            binding.txtCant.text = "Cantidad de Casos: " + caso.cantidad
        }
    }

    // Método llamado cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasosViewHolder {
        val binding = ItemsBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasosViewHolder(binding)
    }

    // Método que devuelve el número total de elementos en la lista
    override fun getItemCount(): Int = casos.size

    // Método llamado cuando RecyclerView necesita mostrar un elemento en una posición específica
    override fun onBindViewHolder(holder: CasosViewHolder, position: Int) {
        val caso = casos[position]
        holder.bind(caso)
    }
}

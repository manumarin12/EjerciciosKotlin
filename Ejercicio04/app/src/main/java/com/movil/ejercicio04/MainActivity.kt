package com.movil.ejercicio04

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.movil.ejercicio04.databinding.ActivityMainBinding


data class Citas(
    val date: String,
    val localName: String,
    val countryCode: String,
    val fixed: Boolean,
    val countries: List<String>?,
    val launchYear: Int?,
    val types: List<String>?
)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listDates: List<Citas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        // Realizar la Solicitud Get al API con Fuel
        Fuel.get("https://date.nager.at/api/v3/publicholidays/2024/AT")
            .response { _, response, result ->
                result.fold({ data ->
                    val jsoBody = String(data)
                    val gson = Gson()
                    listDates = gson.fromJson(jsoBody, Array<Citas>::class.java).toList()

                    runOnUiThread {
                        binding.rvCitas.layoutManager = LinearLayoutManager(this)
                        binding.rvCitas.adapter = AdapterCitas(listDates)
                        showLoading(false)
                    }
                }, { error ->
                    // Maneja el error aquí
                    error.printStackTrace()
                    runOnUiThread {
                        showLoading(false)
                        // Muestra un mensaje de error o toma otra acción adecuada
                    }
                })
            }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvCitas.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvCitas.visibility = View.VISIBLE
        }
    }
}

package com.movil.ejercicio04_1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.movil.ejercicio04_1.databinding.ActivityMainBinding

data class Casos(
    val date: String,
    val cantidad: Int,
)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listCasos: List<Casos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        // Realizar la Solicitud Get al API con Fuel
        Fuel.get("https://disease.sh/v3/covid-19/historical/all?lastdays=all")
            .response { _, _, result ->
                result.fold({ data ->
                    val jsonBody = String(data)
                    val gson = Gson()
                    val jsonObject = gson.fromJson(jsonBody, JsonObject::class.java)
                    val casesJson = jsonObject.getAsJsonObject("cases")

                    val type = object : TypeToken<Map<String, Int>>() {}.type
                    val casesMap: Map<String, Int> = gson.fromJson(casesJson, type)

                    listCasos = casesMap.map { Casos(it.key, it.value) }

                    runOnUiThread {
                        binding.rvCitas.layoutManager = LinearLayoutManager(this)
                        binding.rvCitas.adapter = AdapterCasos(listCasos)
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

        // Aplicar Edge-to-Edge
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
package com.movil.ejercicio01

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.movil.ejercicio01.databinding.ActivityMainBinding

//Declaracion de las clases
data class Paises(
    val name: Name,
    val capital: List<String>,
    val flags: Flags
)
data class Name(
    val common: String
)

data class Flags(
    val png: String,
    val svg: String
)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //Declaramos la lista
    private lateinit var listPaises: List<Paises>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        //Realizar la Solicitud Get al API con fuel
        Fuel.get("https://restcountries.com/v3.1/all?fields=name,capital,flags")
            .response { request, response, result ->
                result.fold({ data ->
                    val jsnBody = String(data)
                    val convertidor = Gson()
                    listPaises = convertidor.fromJson(jsnBody, Array<Paises>::class.java).toList()

                    runOnUiThread {
                        //binding.inicioPais.text= "El total de países es: ${listaPaises.size}"
                        binding.rvPaises.layoutManager = LinearLayoutManager(this)
                        binding.rvPaises.adapter = AdapterPaises(listPaises)
                        showLoading(false)
                    }
                }, { error ->
                    // Maneja el error aquí
                    error.printStackTrace()
                })
            }

    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvPaises.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvPaises.visibility = View.VISIBLE
        }
    }
}
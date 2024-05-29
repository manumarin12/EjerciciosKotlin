package com.movil.ejercicio02

import AdapterLibros
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.movil.ejercicio02.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Fuel.get("https://stephen-king-api.onrender.com/api/villains")
            .response(){request, response, result ->

                val jsonBody = response.body().asString("application/json")
                val gson = Gson()

                datos = gson.fromJson(jsonBody, Datos::class.java)

                binding.rvLibros.layoutManager = LinearLayoutManager(this)
                binding.rvLibros.adapter = AdapterLibros(datos.data)
            }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



    /*private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvNoticias.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvNoticias.visibility = View.VISIBLE
        }
    }*/
}

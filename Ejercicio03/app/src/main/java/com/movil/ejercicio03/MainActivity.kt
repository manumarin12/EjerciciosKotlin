package com.movil.ejercicio03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.movil.ejercicio03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Fuel.get("https://potterapi-fedeperin.vercel.app/en/books")
            .response(){ request, response, result ->
                val jsonBody = response.body().asString("application/json")

                val gson = Gson()
                listaLibros = gson.fromJson(jsonBody, Array<Data>::class.java).toList()

                binding.txtText1.setText("Total Libros: ${listaLibros.size}")

                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = LibrosAdapter(listaLibros)
            }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
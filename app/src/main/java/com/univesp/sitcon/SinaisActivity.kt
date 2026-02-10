package com.univesp.sitcon

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class SinaisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amv) // Reutiliza layout com RecyclerView

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAmv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val listaDeIds = db.dao().getUniqueSinaisIds()

            if (listaDeIds.isNotEmpty()) {
                val adapter = SinaisAdapter(listaDeIds)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this@SinaisActivity, "Nenhum Sinal encontrado!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
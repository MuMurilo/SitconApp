package com.univesp.sitcon

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class SinaisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinais)

        val recycler = findViewById<RecyclerView>(R.id.recyclerSinais)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarSinais)

        recycler.layoutManager = GridLayoutManager(this, 2)

        btnVoltar.setOnClickListener { finish() }

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val listaIds = db.dao().getUniqueSinaisIds()

            if (listaIds.isNotEmpty()) {
                val adapter = SinaisAdapter(listaIds)
                recycler.adapter = adapter
            } else {
                Toast.makeText(this@SinaisActivity, "Nenhum sinal encontrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
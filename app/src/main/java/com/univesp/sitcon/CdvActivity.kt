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

class CdvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdv)

        val recycler = findViewById<RecyclerView>(R.id.recyclerCdv)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarCdv)

        // Grade de 2 colunas
        recycler.layoutManager = GridLayoutManager(this, 2)

        btnVoltar.setOnClickListener { finish() }

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)

            // Busca lista de Strings (ex: "01T", "02T")
            val listaIds = db.dao().getUniqueCdvIds()

            if (listaIds.isNotEmpty()) {
                val adapter = CdvAdapter(listaIds)
                recycler.adapter = adapter
            } else {
                Toast.makeText(this@CdvActivity, "Nenhum CDV encontrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.univesp.sitcon

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class SinaisDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinais_detalhe)

        val idSinal = intent.getIntExtra("ID_SINAL_SELECIONADO", 0)

        val txtTitulo = findViewById<TextView>(R.id.txtTituloSinalDetalhe)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarSinalDetalhe)
        val recycler = findViewById<RecyclerView>(R.id.recyclerSinalDetalhe)

        txtTitulo.text = "SINAL $idSinal"

        btnVoltar.setOnClickListener { finish() }

        recycler.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)

            // Busca todos os aspectos para esse sinal
            val registros = db.dao().getSinaisById(idSinal)

            if (registros.isNotEmpty()) {
                val adapter = SinaisDetalheAdapter(registros)
                recycler.adapter = adapter
            } else {
                Toast.makeText(this@SinaisDetalheActivity, "Nenhum detalhe encontrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
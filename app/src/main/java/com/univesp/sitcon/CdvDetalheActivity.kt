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

class CdvDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdv_detalhe)

        // Atenção: O ID do CDV é uma String (ex: "01T"), diferentemente de AMV/Sinal que é Int
        val idCdv = intent.getStringExtra("ID_CDV_SELECIONADO") ?: ""

        val txtTitulo = findViewById<TextView>(R.id.txtTituloCdvDetalhe)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarCdvDetalhe)
        val recycler = findViewById<RecyclerView>(R.id.recyclerCdvDetalhe)

        txtTitulo.text = "CDV $idCdv"

        btnVoltar.setOnClickListener { finish() }

        recycler.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)

            // Busca todos os registros (tipos) para esse CDV
            val registros = db.dao().getCdvDetails(idCdv)

            if (registros.isNotEmpty()) {
                val adapter = CdvDetalheAdapter(registros)
                recycler.adapter = adapter
            } else {
                Toast.makeText(this@CdvDetalheActivity, "Nenhum detalhe encontrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
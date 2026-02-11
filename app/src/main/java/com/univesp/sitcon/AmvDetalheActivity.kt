package com.univesp.sitcon

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import com.univesp.sitcon.utils.SitconUtils
import kotlinx.coroutines.launch

class AmvDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amv_detalhe)

        val idAmv = intent.getIntExtra("ID_AMV_SELECIONADO", 0)
        val tipoAmv = intent.getStringExtra("TIPO_AMV") ?: "Comando"

        val txtTitulo = findViewById<TextView>(R.id.txtTituloAmvDetalhe)
        val btnMapa = findViewById<Button>(R.id.btnVerNoMapa)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarDetalhe)
        val recycler = findViewById<RecyclerView>(R.id.recyclerAmvDetalhe)

        txtTitulo.text = "AMV $idAmv - $tipoAmv"

        val corTema = if (tipoAmv == "Comando") {
            ContextCompat.getColor(this, R.color.sitcon_primary)
        } else {
            ContextCompat.getColor(this, R.color.sitcon_success)
        }

        txtTitulo.setTextColor(corTema)

        val coords = SitconUtils.AMV_COORDINATES[idAmv]
        if (coords != null) {
            btnMapa.visibility = View.VISIBLE
            btnMapa.setOnClickListener {
                val uri = Uri.parse("geo:${coords.first},${coords.second}?q=${coords.first},${coords.second}(AMV $idAmv)")
                val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        } else {
            btnMapa.visibility = View.GONE
        }

        btnVoltar.setOnClickListener { finish() }

        recycler.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)

            // Define filtros igual ao app.py
            val funcoes = if (tipoAmv == "Comando") {
                listOf("NWR", "WR")
            } else {
                listOf("NWP", "WP")
            }

            // Chama o método com o nome correto
            val registros = db.dao().getAmvDetailsByFunction(idAmv, funcoes)

            if (registros.isNotEmpty()) {
                val adapter = AmvDetalheAdapter(registros, tipoAmv)
                recycler.adapter = adapter
            } else {
                Toast.makeText(this@AmvDetalheActivity, "Nenhum registro encontrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
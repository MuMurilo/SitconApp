package com.univesp.sitcon

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class AmvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amv)

        val recyclerComando = findViewById<RecyclerView>(R.id.recyclerComando)
        val recyclerIndicacao = findViewById<RecyclerView>(R.id.recyclerIndicacao)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        // Headers clicáveis
        val headerComando = findViewById<LinearLayout>(R.id.headerComando)
        val headerIndicacao = findViewById<LinearLayout>(R.id.headerIndicacao)

        // Ícones de seta
        val arrowComando = findViewById<ImageView>(R.id.arrowComando)
        val arrowIndicacao = findViewById<ImageView>(R.id.arrowIndicacao)

        // Configura Grid de 2 colunas
        recyclerComando.layoutManager = GridLayoutManager(this, 2)
        recyclerIndicacao.layoutManager = GridLayoutManager(this, 2)

        // Função auxiliar para alternar visibilidade
        fun toggleSection(recyclerView: RecyclerView, arrow: ImageView) {
            if (recyclerView.visibility == View.VISIBLE) {
                recyclerView.visibility = View.GONE
                arrow.rotation = 0f // Seta para baixo
            } else {
                recyclerView.visibility = View.VISIBLE
                arrow.rotation = 180f // Seta para cima
            }
        }

        // Configura cliques nos títulos
        headerComando.setOnClickListener {
            toggleSection(recyclerComando, arrowComando)
        }

        headerIndicacao.setOnClickListener {
            toggleSection(recyclerIndicacao, arrowIndicacao)
        }

        btnVoltar.setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val listaDeIds = db.dao().getUniqueAmvIds()

            if (listaDeIds.isNotEmpty()) {
                val adapterComando = AmvAdapter(listaDeIds, "Comando")
                val adapterIndicacao = AmvAdapter(listaDeIds, "Indicação")

                recyclerComando.adapter = adapterComando
                recyclerIndicacao.adapter = adapterIndicacao
            } else {
                Toast.makeText(this@AmvActivity, "Nenhum AMV encontrado!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
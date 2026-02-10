package com.univesp.sitcon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // 1. Referenciando os cartões pelos IDs corretos (conforme activity_menu.xml)
        val btnAmv = findViewById<CardView>(R.id.cardAmv)       // Corrigido de cardAMV para cardAmv
        val btnSinais = findViewById<CardView>(R.id.cardSinais)
        val btnCdv = findViewById<CardView>(R.id.cardCdv)       // Corrigido de cardCDV para cardCdv

        // 2. Configurando os cliques
        btnAmv.setOnClickListener {
            val intent = Intent(this, AmvActivity::class.java)
            startActivity(intent)
        }

        btnSinais.setOnClickListener {
            val intent = Intent(this, SinaisActivity::class.java)
            startActivity(intent)
        }

        btnCdv.setOnClickListener {
            // Certifique-se de ter criado o arquivo CdvActivity.kt
            val intent = Intent(this, CdvActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.univesp.sitcon // <--- CONFIRA SE ESSE É SEU PACOTE (com.exemplo.sitcon?)

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // 1. Encontrar os botões na tela (pelo ID que demos no XML)
        val cardAMV = findViewById<CardView>(R.id.cardAMV)
        val cardCDV = findViewById<CardView>(R.id.cardCDV)
        val cardSinais = findViewById<CardView>(R.id.cardSinais)

        // Configurar o clique do botão AMV
        cardAMV.setOnClickListener {
            // Toast.makeText(this, "Abrindo Equipamentos AMV...", Toast.LENGTH_SHORT).show() <-- APAGUE ISSO

            // USE ISSO:
            val intent = Intent(this, AmvActivity::class.java)
            startActivity(intent)
        }

        // 3. Configurar o clique do botão CDV
        cardCDV.setOnClickListener {
            Toast.makeText(this, "Abrindo Circuitos de Via...", Toast.LENGTH_SHORT).show()
        }

        // 4. Configurar o clique do botão Sinais
        cardSinais.setOnClickListener {
            Toast.makeText(this, "Abrindo Sinais...", Toast.LENGTH_SHORT).show()
        }
    }
}
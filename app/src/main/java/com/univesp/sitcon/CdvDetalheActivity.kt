package com.univesp.sitcon

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CdvDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdv_detalhe)

        val idCdv = intent.getStringExtra("ID_CDV_SELECIONADO")
        val txtTitulo = findViewById<TextView>(R.id.txtTituloCdv)

        txtTitulo.text = "Circuito: $idCdv"
    }
}
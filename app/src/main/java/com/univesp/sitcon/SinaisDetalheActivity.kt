package com.univesp.sitcon

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class SinaisDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Aponta para o NOVO layout exclusivo
        setContentView(R.layout.activity_sinais_detalhe)

        val idSinal = intent.getIntExtra("ID_SINAL_SELECIONADO", -1)

        // 2. Usa os IDs novos e claros que criamos no XML
        val txtTitulo = findViewById<TextView>(R.id.txtTituloSinal)
        val txtRelatorio = findViewById<TextView>(R.id.txtRelatorioSinal)

        txtTitulo.text = "Sinal #$idSinal"

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val detalhes = db.dao().getSinaisById(idSinal)

            if (detalhes.isNotEmpty()) {
                val sb = StringBuilder()
                for (item in detalhes) {
                    sb.append("ASPECTO: ${item.tipoAspecto}\n")
                    sb.append("TORRE: ${item.tower ?: "-"}\n")
                    sb.append("INTERFACE: ${item.interface_ ?: "-"}\n")

                    // Lógica de locações (L1, L2...)
                    val locs = listOfNotNull(item.L1, item.L2, item.L3, item.L4).filter { it.isNotBlank() }
                    sb.append("LOCAÇÕES: ${if(locs.isEmpty()) "-" else locs.joinToString(", ")}\n")

                    sb.append("_________________________\n\n")
                }
                txtRelatorio.text = sb.toString()
            } else {
                txtRelatorio.text = "Nenhuma informação encontrada."
            }
        }
    }
}
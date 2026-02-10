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
        setContentView(R.layout.activity_amv_detalhe)

        val idSinal = intent.getIntExtra("ID_SINAL_SELECIONADO", -1)
        val txtTitulo = findViewById<TextView>(R.id.txtIdAmv)
        val txtConteudo = findViewById<TextView>(R.id.txtFuncao)

        // Altera o título fixo do layout reutilizado
        txtTitulo?.text = "Relatório do Sinal #$idSinal"
        txtConteudo?.text = "Buscando informações no banco..."

        lifecycleScope.launch {
            try {
                val db = AppDatabase.getDatabase(applicationContext)
                val detalhes = db.dao().getSinaisById(idSinal)

                if (detalhes.isNotEmpty()) {
                    val relatorio = StringBuilder()
                    for (sinal in detalhes) {
                        relatorio.append("Aspecto: ${sinal.tipoAspecto}\n")
                        relatorio.append("Torre: ${sinal.tower ?: "-"}\n")
                        relatorio.append("Interface: ${sinal.interface_ ?: "-"}\n")

                        // Acessando as propriedades com letras maiúsculas (L1, L2, etc)
                        val locs = listOfNotNull(sinal.L1, sinal.L2, sinal.L3, sinal.L4).filter { !it.isNullOrBlank() }
                        relatorio.append("Locações: ${if(locs.isEmpty()) "-" else locs.joinToString(", ")}\n")
                        relatorio.append("--------------------------------\n\n")
                    }
                    txtConteudo?.text = relatorio.toString()
                } else {
                    txtConteudo?.text = "Nenhuma informação encontrada para o ID $idSinal."
                }
            } catch (e: Exception) {
                txtConteudo?.text = "Erro ao carregar: ${e.localizedMessage}"
            }
        }
    }
}
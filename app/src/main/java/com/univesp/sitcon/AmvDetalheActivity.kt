package com.univesp.sitcon

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class AmvDetalheActivity : AppCompatActivity() {

    private val traducoes = mapOf(
        "L1" to "Locação 1", "L2" to "Locação 2", "L3" to "Locação 3",
        "L4" to "Locação 4", "L5" to "Locação 5", "L6" to "Locação 6",
        "L7" to "Locação 7", "L8" to "Locação 8", "L9" to "Locação 9",
        "L10" to "Locação 10", "tower" to "NX", "interface_" to "Bastidor de Interface",
        "L14" to "Locação 14", "L15" to "Locação 15", "L16" to "Locação 16",
        "L17" to "Locação 17", "L18" to "Locação 18", "L19" to "Locação 19",
        "L20" to "Locação 20", "L21" to "Locação 21", "L22" to "Locação 22", "L23" to "Locação 23"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amv_detalhe)

        val idAmv = intent.getIntExtra("ID_AMV_SELECIONADO", -1)
        val txtTitulo = findViewById<TextView>(R.id.txtTituloAmvDetalhe)
        val container = findViewById<LinearLayout>(R.id.containerAmvCards)

        txtTitulo.text = "AMV #$idAmv"

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val listaFuncoes = db.dao().getAmvFunctions(idAmv)

            container.removeAllViews()
            val inflater = LayoutInflater.from(this@AmvDetalheActivity)

            for (amv in listaFuncoes) {
                val cardView = inflater.inflate(R.layout.item_amv_detail_card, container, false)
                val txtHeader = cardView.findViewById<TextView>(R.id.txtHeaderCard)
                val txtBody = cardView.findViewById<TextView>(R.id.txtBodyCard)

                val tipo = amv.tipoFuncao ?: ""
                txtHeader.text = "Tipo Função: $tipo"

                // Cores seguindo a lógica do app.py (NWR/WR = Comando)
                if (tipo.contains("NWR") || tipo.contains("WR")) {
                    txtHeader.setBackgroundColor(ContextCompat.getColor(this@AmvDetalheActivity, R.color.sitcon_primary))
                } else {
                    txtHeader.setBackgroundColor(ContextCompat.getColor(this@AmvDetalheActivity, R.color.sitcon_success))
                }

                // Ordem rigorosa dos campos conforme a tabela
                val campos = listOf(
                    "L1" to amv.L1, "L2" to amv.L2, "L3" to amv.L3, "L4" to amv.L4,
                    "L5" to amv.L5, "L6" to amv.L6, "L7" to amv.L7, "L9" to amv.L9,
                    "L10" to amv.L10, "tower" to amv.tower, "interface_" to amv.interface_,
                    "L14" to amv.L14, "L15" to amv.L15, "L16" to amv.L16, "L17" to amv.L17,
                    "L18" to amv.L18, "L20" to amv.L20, "L21" to amv.L21, "L22" to amv.L22,
                    "L23" to amv.L23
                )

                val sb = StringBuilder()
                for ((chave, valor) in campos) {
                    if (!valor.isNullOrBlank()) {
                        val nomeAmigavel = traducoes[chave] ?: chave

                        // Aplicamos a formatação especial apenas se não for tower ou interface_
                        val valorFinal = if (chave == "tower" || chave == "interface_") {
                            valor
                        } else {
                            formatarValorLocacao(valor)
                        }

                        sb.append("<b>$nomeAmigavel:</b> $valorFinal<br><br>")
                    }
                }

                txtBody.text = Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
                container.addView(cardView)
            }
        }
    }

    private fun formatarValorLocacao(valor: String): String {
        // Se não tiver o formato esperado de locação (X-Y-Z), retorna o texto original
        if (!valor.contains("-")) return valor

        val partes = valor.split("-")
        return when {
            partes.size >= 3 -> "Caixa ${partes[0]}, TB ${partes[1]}-${partes[2]}"
            partes.size == 2 -> "Caixa ${partes[0]}, TB ${partes[1]}"
            else -> valor
        }
    }
}
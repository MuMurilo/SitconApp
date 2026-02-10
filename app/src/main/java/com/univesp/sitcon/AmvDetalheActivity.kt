package com.univesp.sitcon

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class AmvDetalheActivity : AppCompatActivity() {
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

            // Limpa views antigas se houver
            container.removeAllViews()

            // O Título "Lista de AMVs" no XML original é fixo, mas aqui mudamos para o Detalhe
            // infla um cartão para cada função encontrada no banco
            val inflater = LayoutInflater.from(this@AmvDetalheActivity)

            for (amv in listaFuncoes) {
                // 1. Infla o layout do cartão
                val cardView = inflater.inflate(R.layout.item_amv_detail_card, container, false)

                // 2. Referencia os textos do cartão
                val txtHeader = cardView.findViewById<TextView>(R.id.txtHeaderCard)
                val txtBody = cardView.findViewById<TextView>(R.id.txtBodyCard)

                // 3. Define a Cor e Título baseado no Tipo (Lógica do HTML)
                val tipo = amv.tipoFuncao ?: "DESCONHECIDO"
                txtHeader.text = "Tipo Função: $tipo"

                if (tipo.contains("Normal", ignoreCase = true) || tipo.contains("Reverso", ignoreCase = true)) {
                    // Se for Normal ou Reverso (Comando), Azul (Primary)
                    txtHeader.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.sitcon_primary))
                } else {
                    // Outros (Controle, etc), Verde (Success)
                    txtHeader.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.sitcon_success))
                }

                // 4. Monta os dados
                val sb = StringBuilder()
                if (!amv.tower.isNullOrBlank()) sb.append("Torre: ${amv.tower}\n")
                if (!amv.interface_.isNullOrBlank()) sb.append("Interface: ${amv.interface_}\n")

                val locs = listOfNotNull(
                    amv.L1, amv.L2, amv.L3, amv.L4, amv.L5, amv.L6, amv.L7,
                    amv.L9, amv.L10, amv.L14, amv.L15, amv.L16, amv.L17,
                    amv.L18, amv.L20, amv.L21, amv.L22, amv.L23
                ).filter { it.isNotBlank() }

                if (locs.isNotEmpty()) {
                    sb.append("\nLocações: ${locs.joinToString(", ")}")
                }

                txtBody.text = sb.toString()

                // 5. Adiciona o cartão na tela
                container.addView(cardView)
            }
        }
    }
}
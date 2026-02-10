package com.univesp.sitcon

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.univesp.sitcon.data.AppDatabase
import com.univesp.sitcon.data.Sinais
import kotlinx.coroutines.launch

class SinaisDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinais_detalhe)

        val idSinal = intent.getIntExtra("ID_SINAL_SELECIONADO", -1)

        val txtTitulo = findViewById<TextView>(R.id.txtTituloSinal)
        txtTitulo.text = "Sinal #$idSinal"

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            // Busca TODAS as linhas desse sinal (ex: RGE, YGE, etc)
            val listaDados = db.dao().getSinaisById(idSinal)

            // Filtra e preenche cada cartão
            preencherCartao(listaDados, "RGE", R.id.cardRGE, R.id.txtDadosRGE)
            preencherCartao(listaDados, "RGENX", R.id.cardRGENX, R.id.txtDadosRGENX)
            preencherCartao(listaDados, "YGE", R.id.cardYGE, R.id.txtDadosYGE)
            preencherCartao(listaDados, "YGENX", R.id.cardYGENX, R.id.txtDadosYGENX)
        }
    }

    private fun preencherCartao(lista: List<Sinais>, tipoAlvo: String, idCard: Int, idTexto: Int) {
        val card = findViewById<CardView>(idCard)
        val txt = findViewById<TextView>(idTexto)

        // Procura na lista se existe um registro com esse 'tipoAspecto'
        // Ignora maiúsculas/minúsculas para garantir
        val dados = lista.find { it.tipoAspecto.equals(tipoAlvo, ignoreCase = true) }

        if (dados != null) {
            card.visibility = View.VISIBLE
            val sb = StringBuilder()

            // Monta o texto bonitinho igual uma tabela
            if (!dados.tower.isNullOrBlank()) sb.append("Torre: ${dados.tower}\n")
            if (!dados.interface_.isNullOrBlank()) sb.append("Interface: ${dados.interface_}\n")

            // Junta as locações
            val locs = listOfNotNull(
                dados.L1, dados.L2, dados.L3, dados.L4, dados.L5, dados.L6, dados.L7, dados.L8,
                dados.L10, dados.L14, dados.L15, dados.L16, dados.L18, dados.L19, dados.L20,
                dados.L21, dados.L22, dados.L23
            ).filter { it.isNotBlank() }

            if (locs.isNotEmpty()) {
                sb.append("\nLocações:\n${locs.joinToString(", ")}")
            }

            txt.text = sb.toString()
        } else {
            // Se não tiver dados para esse aspecto, esconde o cartão (ou deixa visível vazio, como preferir)
            card.visibility = View.GONE
        }
    }
}
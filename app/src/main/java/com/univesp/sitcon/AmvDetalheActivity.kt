package com.univesp.sitcon

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class AmvDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amv_detalhe)

        val txtTitulo = findViewById<TextView>(R.id.txtTituloDetalhe)
        val txtDados = findViewById<TextView>(R.id.txtDadosCompletos)

        val idRecebido = intent.getIntExtra("ID_AMV_SELECIONADO", -1)

        if (idRecebido != -1) {
            txtTitulo.text = "AMV #$idRecebido - Relatório Geral"

            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(applicationContext)
                val listaDeFuncoes = db.dao().getAmvFunctions(idRecebido)

                val relatorio = StringBuilder()

                for (amv in listaDeFuncoes) {
                    // Cabeçalho da Função
                    relatorio.append("\n================================\n")
                    relatorio.append("   FUNÇÃO: ${amv.tipoFuncao}   \n")
                    relatorio.append("================================\n")

                    fun adicionar(rotulo: String, valor: String?) {
                        if (!valor.isNullOrBlank()) {
                            // Agora o formato fica "Locação 1: valor"
                            relatorio.append("$rotulo: $valor\n")
                        }
                    }

                    // --- MUDANÇA AQUI: Trocamos os nomes dos rótulos ---

                    adicionar("Torre", amv.tower)
                    adicionar("Interface", amv.interface_)

                    adicionar("Locação 1", amv.L1)
                    adicionar("Locação 2", amv.L2)
                    adicionar("Locação 3", amv.L3)
                    adicionar("Locação 4", amv.L4)
                    adicionar("Locação 5", amv.L5)
                    adicionar("Locação 6", amv.L6)
                    adicionar("Locação 7", amv.L7)
                    // L8 não existe no seu banco
                    adicionar("Locação 9", amv.L9)
                    adicionar("Locação 10", amv.L10)
                    // L11, L12, L13 não existem
                    adicionar("Locação 14", amv.L14)
                    adicionar("Locação 15", amv.L15)
                    adicionar("Locação 16", amv.L16)
                    adicionar("Locação 17", amv.L17)
                    adicionar("Locação 18", amv.L18)
                    // L19 não existe
                    adicionar("Locação 20", amv.L20)
                    adicionar("Locação 21", amv.L21)
                    adicionar("Locação 22", amv.L22)
                    adicionar("Locação 23", amv.L23)

                    relatorio.append("\n")
                }
                txtDados.text = relatorio.toString()
            }
        } else {
            txtDados.text = "Erro ao carregar ID."
        }
    }
}
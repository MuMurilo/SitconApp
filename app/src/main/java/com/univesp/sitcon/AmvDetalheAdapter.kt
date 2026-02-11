package com.univesp.sitcon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AMV
import com.univesp.sitcon.utils.SitconUtils

class AmvDetalheAdapter(
    private val listaRegistros: List<AMV>,
    private val tipo: String
) : RecyclerView.Adapter<AmvDetalheAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_amv_detail_card, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val registro = listaRegistros[position]
        val context = holder.itemView.context

        // 1. Configura o Cabeçalho
        holder.txtHeader.text = "Tipo Função: ${registro.tipoFuncao}"

        val cor = if (tipo == "Comando") {
            ContextCompat.getColor(context, R.color.sitcon_primary)
        } else {
            ContextCompat.getColor(context, R.color.sitcon_success)
        }
        holder.txtHeader.setBackgroundColor(cor)

        // 2. Limpa dados antigos
        holder.containerDados.removeAllViews()

        // 3. Mapeamento manual dos campos (Resolve o erro de 'Unresolved reference')
        // Cria uma lista de pares "Nome do Campo" -> "Valor"
        val campos = listOf(
            "L1" to registro.L1,
            "L2" to registro.L2,
            "L3" to registro.L3,
            "L4" to registro.L4,
            "L5" to registro.L5,
            "L6" to registro.L6,
            "L7" to registro.L7,
            // Nota: L8 não existe na tabela AMV, pula para L9
            "L9" to registro.L9,
            "L10" to registro.L10,
            "tower" to registro.tower,
            "interface_" to registro.interface_,
            "L14" to registro.L14,
            "L15" to registro.L15,
            "L16" to registro.L16,
            "L17" to registro.L17,
            "L18" to registro.L18,
            // L19 não existe na tabela AMV
            "L20" to registro.L20,
            "L21" to registro.L21,
            "L22" to registro.L22,
            "L23" to registro.L23
        )

        // 4. Itera sobre a lista manual
        for ((nomeCampo, valor) in campos) {

            // Só exibe se o valor não for nulo nem vazio
            if (!valor.isNullOrEmpty()) {

                // Busca a tradução (ex: "tower" -> "NX")
                val label = SitconUtils.TRADUCOES_GERAIS[nomeCampo] ?: nomeCampo

                // Formatação inteligente (ex: "B-B-29")
                val valorFormatado = if (nomeCampo.startsWith("L") && nomeCampo.length > 1 && nomeCampo[1].isDigit()) {
                    SitconUtils.formatarValorLocacao(valor)
                } else {
                    valor
                }

                // Infla a linha e adiciona ao container
                val rowView = LayoutInflater.from(context).inflate(R.layout.row_amv_key_value, holder.containerDados, false)
                val txtChave = rowView.findViewById<TextView>(R.id.txtChave)
                val txtValor = rowView.findViewById<TextView>(R.id.txtValor)

                txtChave.text = label
                txtValor.text = valorFormatado

                holder.containerDados.addView(rowView)
            }
        }
    }

    override fun getItemCount(): Int = listaRegistros.size

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtHeader: TextView = itemView.findViewById(R.id.txtHeaderCard)
        val containerDados: LinearLayout = itemView.findViewById(R.id.containerDados)
    }
}
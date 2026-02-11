package com.univesp.sitcon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.CDV
import com.univesp.sitcon.utils.SitconUtils

class CdvDetalheAdapter(
    private val listaRegistros: List<CDV>
) : RecyclerView.Adapter<CdvDetalheAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cdv_detail_card, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val registro = listaRegistros[position]
        val context = holder.itemView.context

        // 1. Cabeçalho (Tipo)
        holder.txtHeader.text = "Tipo: ${registro.tipo}"

        // Mantemos a cor Azul padrão para CDV, pois geralmente não tem lógica de cores como Sinais
        // Se houver, podemos adicionar aqui depois.

        // 2. Limpa dados anteriores
        holder.containerDados.removeAllViews()

        // 3. Mapeamento Manual dos campos de CDV (Baseado na Entity CDV)
        val campos = listOf(
            "L1" to registro.L1,
            "L2" to registro.L2,
            "L3" to registro.L3,
            "L4" to registro.L4,
            "L5" to registro.L5,
            "L6" to registro.L6,
            "L7" to registro.L7,
            "L8" to registro.L8, // CDV tem L8
            "L9" to registro.L9, // CDV tem L9
            "L10" to registro.L10,
            "tower" to registro.tower,
            "interface_" to registro.interface_,
            "L14" to registro.L14,
            "L15" to registro.L15,
            "L16" to registro.L16,
            "L17" to registro.L17, // CDV tem L17
            "L18" to registro.L18,
            // CDV não tem L19 na definição da Entity que vimos anteriormente, pulamos
            "L20" to registro.L20,
            "L21" to registro.L21,
            "L22" to registro.L22,
            "L23" to registro.L23
        )

        for ((nomeCampo, valor) in campos) {
            if (!valor.isNullOrEmpty()) {
                val label = SitconUtils.TRADUCOES_GERAIS[nomeCampo] ?: nomeCampo

                val valorFormatado = if (nomeCampo.startsWith("L") && nomeCampo.length > 1 && nomeCampo[1].isDigit()) {
                    SitconUtils.formatarValorLocacao(valor)
                } else {
                    valor
                }

                // Reutiliza o layout de linha (row_amv_key_value)
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
        val txtHeader: TextView = itemView.findViewById(R.id.txtHeaderCdvCard)
        val containerDados: LinearLayout = itemView.findViewById(R.id.containerDadosCdv)
    }
}
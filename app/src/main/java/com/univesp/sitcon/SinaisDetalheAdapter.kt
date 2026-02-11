package com.univesp.sitcon

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.Sinais
import com.univesp.sitcon.utils.SitconUtils

class SinaisDetalheAdapter(
    private val listaRegistros: List<Sinais>
) : RecyclerView.Adapter<SinaisDetalheAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sinais_detail_card, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val registro = listaRegistros[position]
        val context = holder.itemView.context

        // 1. Tratamento do Aspecto (Remove espaços e garante maiúsculas)
        val aspectoOriginal = registro.tipoAspecto ?: ""
        val aspecto = aspectoOriginal.trim().uppercase()

        holder.txtHeader.text = "Aspecto: $aspectoOriginal"

        // 2. Lógica de Cores Rigorosa
        val corFundo: Int
        val corTexto: Int

        when {
            // Começa com R (ex: RGE, RGENX) -> VERMELHO
            aspecto.startsWith("R") -> {
                corFundo = Color.parseColor("#D32F2F") // Vermelho Material Design
                corTexto = Color.WHITE
            }
            // Começa com Y (ex: YGE, YGENX) -> AMARELO
            aspecto.startsWith("Y") -> {
                corFundo = Color.parseColor("#FFD600") // Amarelo Ouro Vibrante
                corTexto = Color.BLACK // Texto Preto para contraste no Amarelo
            }
            // Padrão -> AZUL
            else -> {
                corFundo = ContextCompat.getColor(context, R.color.sitcon_primary)
                corTexto = Color.WHITE
            }
        }

        holder.txtHeader.setBackgroundColor(corFundo)
        holder.txtHeader.setTextColor(corTexto)

        // 3. Preenchimento dos dados (Mantido igual)
        holder.containerDados.removeAllViews()

        val campos = listOf(
            "L1" to registro.L1,
            "L2" to registro.L2,
            "L3" to registro.L3,
            "L4" to registro.L4,
            "L5" to registro.L5,
            "L6" to registro.L6,
            "L7" to registro.L7,
            "L8" to registro.L8,
            "L10" to registro.L10,
            "tower" to registro.tower,
            "interface_" to registro.interface_,
            "L14" to registro.L14,
            "L15" to registro.L15,
            "L16" to registro.L16,
            "L18" to registro.L18,
            "L19" to registro.L19,
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
        val txtHeader: TextView = itemView.findViewById(R.id.txtHeaderSinalCard)
        val containerDados: LinearLayout = itemView.findViewById(R.id.containerDadosSinal)
    }
}
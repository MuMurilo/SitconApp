package com.univesp.sitcon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class AmvAdapter(
    private val listaIds: List<Int>,
    private val tipo: String // "Comando" ou "Indicação"
) : RecyclerView.Adapter<AmvAdapter.AmvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_amv_grid, parent, false)
        return AmvViewHolder(view)
    }

    override fun onBindViewHolder(holder: AmvViewHolder, position: Int) {
        val idAtual = listaIds[position]
        val context = holder.itemView.context

        // CORREÇÃO: Removido " LUZ", mantendo apenas o padrão AMV XX
        holder.txtId.text = "AMV $idAtual"

        val cor: Int
        if (tipo == "Comando") {
            cor = ContextCompat.getColor(context, R.color.sitcon_primary)
        } else {
            cor = ContextCompat.getColor(context, R.color.sitcon_success)
        }

        holder.txtId.setTextColor(cor)

        // Configura borda
        (holder.itemView as? MaterialCardView)?.strokeColor = cor

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AmvDetalheActivity::class.java)
            intent.putExtra("ID_AMV_SELECIONADO", idAtual)
            intent.putExtra("TIPO_AMV", tipo)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaIds.size

    class AmvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtIdAmvGrid)
    }
}
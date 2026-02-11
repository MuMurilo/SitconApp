package com.univesp.sitcon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class CdvAdapter(private val listaIds: List<String>) : RecyclerView.Adapter<CdvAdapter.CdvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cdv_grid, parent, false)
        return CdvViewHolder(view)
    }

    override fun onBindViewHolder(holder: CdvViewHolder, position: Int) {
        val idAtual = listaIds[position]
        val context = holder.itemView.context

        holder.txtId.text = idAtual

        // Garante visual Azul
        holder.txtId.setTextColor(ContextCompat.getColor(context, R.color.sitcon_primary))
        (holder.itemView as? MaterialCardView)?.strokeColor = ContextCompat.getColor(context, R.color.sitcon_primary)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CdvDetalheActivity::class.java)
            // Atenção: CDV usa String como ID, não Int
            intent.putExtra("ID_CDV_SELECIONADO", idAtual)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaIds.size

    class CdvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtIdCdvGrid)
    }
}
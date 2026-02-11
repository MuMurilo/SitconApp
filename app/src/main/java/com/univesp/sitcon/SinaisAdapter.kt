package com.univesp.sitcon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class SinaisAdapter(private val listaIds: List<Int>) : RecyclerView.Adapter<SinaisAdapter.SinalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinalViewHolder {
        // Infla o layout do item (que já colocamos como azul no XML)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sinal_grid, parent, false)
        return SinalViewHolder(view)
    }

    override fun onBindViewHolder(holder: SinalViewHolder, position: Int) {
        val idAtual = listaIds[position]
        val context = holder.itemView.context

        holder.txtId.text = "SINAL $idAtual"

        // Garante a cor Azul (Primary) programaticamente também, para evitar conflitos
        holder.txtId.setTextColor(ContextCompat.getColor(context, R.color.sitcon_primary))
        (holder.itemView as? MaterialCardView)?.strokeColor = ContextCompat.getColor(context, R.color.sitcon_primary)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SinaisDetalheActivity::class.java)
            intent.putExtra("ID_SINAL_SELECIONADO", idAtual)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaIds.size

    class SinalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtIdSinalGrid)
    }
}
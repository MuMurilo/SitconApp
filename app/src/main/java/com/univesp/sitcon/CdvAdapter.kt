package com.univesp.sitcon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CdvAdapter(private val listaIds: List<String>) : RecyclerView.Adapter<CdvAdapter.CdvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cdv_grid, parent, false)
        return CdvViewHolder(view)
    }

    override fun onBindViewHolder(holder: CdvViewHolder, position: Int) {
        val idAtual = listaIds[position] // É uma String

        holder.txtId.text = idAtual // Ex: "01 A"

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CdvDetalheActivity::class.java)
            intent.putExtra("ID_CDV_SELECIONADO", idAtual) // Passa String
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaIds.size

    class CdvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtIdCdvGrid)
    }
}
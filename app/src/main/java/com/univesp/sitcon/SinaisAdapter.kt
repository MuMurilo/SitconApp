package com.univesp.sitcon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SinaisAdapter(private val listaIds: List<Int>) : RecyclerView.Adapter<SinaisAdapter.SinaisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinaisViewHolder {
        // MUDANÇA: Agora usamos o layout de GRID que criamos acima
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sinal_grid, parent, false)
        return SinaisViewHolder(view)
    }

    override fun onBindViewHolder(holder: SinaisViewHolder, position: Int) {
        val idAtual = listaIds[position]

        holder.txtId.text = "$idAtual" // Exibe apenas o número, o layout já tem o texto "Sinal"

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SinaisDetalheActivity::class.java)
            intent.putExtra("ID_SINAL_SELECIONADO", idAtual)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaIds.size

    class SinaisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Certifique-se que o ID no XML item_sinal_grid é txtIdSinal
        val txtId: TextView = itemView.findViewById(R.id.txtIdSinal)
    }
}
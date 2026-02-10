package com.univesp.sitcon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SinaisAdapter(private val listaIds: List<Int>) : RecyclerView.Adapter<SinaisAdapter.SinaisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinaisViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_amv, parent, false) // Reutilizando o layout de item
        return SinaisViewHolder(view)
    }

    override fun onBindViewHolder(holder: SinaisViewHolder, position: Int) {
        val idAtual = listaIds[position]

        holder.txtId.text = "Sinal #$idAtual"
        holder.txtFuncao.visibility = View.GONE

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SinaisDetalheActivity::class.java)
            intent.putExtra("ID_SINAL_SELECIONADO", idAtual)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaIds.size

    class SinaisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtIdAmv)
        val txtFuncao: TextView = itemView.findViewById(R.id.txtFuncao)
    }
}
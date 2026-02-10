package com.univesp.sitcon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AmvAdapter(private val listaIds: List<Int>) : RecyclerView.Adapter<AmvAdapter.AmvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmvViewHolder {
        // MUDANÇA: Usando item_amv_grid.xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_amv_grid, parent, false)
        return AmvViewHolder(view)
    }

    override fun onBindViewHolder(holder: AmvViewHolder, position: Int) {
        val idAtual = listaIds[position]

        holder.txtId.text = "$idAtual" // O layout já tem "AMV" se você quiser, ou pode por "AMV #$idAtual"

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AmvDetalheActivity::class.java)
            intent.putExtra("ID_AMV_SELECIONADO", idAtual)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaIds.size

    class AmvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Certifique-se de que o ID bate com o XML novo (txtIdAmvGrid)
        val txtId: TextView = itemView.findViewById(R.id.txtIdAmvGrid)
    }
}
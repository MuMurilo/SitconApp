package com.univesp.sitcon

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager // IMPORTANTE
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class AmvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amv)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAmv)

        // MUDANÇA: Grid com 2 colunas
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val listaDeIds = db.dao().getUniqueAmvIds()

            if (listaDeIds.isNotEmpty()) {
                val adapter = AmvAdapter(listaDeIds)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this@AmvActivity, "Nenhum AMV encontrado!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
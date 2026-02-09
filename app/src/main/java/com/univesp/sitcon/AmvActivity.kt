package com.univesp.sitcon

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class AmvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amv)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAmv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)

            // MUDANÇA: Agora chamamos aquela função nova que traz só os números (IDs)
            val listaDeIds = db.dao().getUniqueAmvIds()

            if (listaDeIds.isNotEmpty()) {
                // Passamos a lista de números para o Adapter
                val adapter = AmvAdapter(listaDeIds)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this@AmvActivity, "Nenhum AMV encontrado!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
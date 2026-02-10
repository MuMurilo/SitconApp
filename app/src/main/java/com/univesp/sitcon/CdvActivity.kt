package com.univesp.sitcon

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class CdvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdv)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCdv)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            // Chama a função do DAO que retorna List<String>
            val listaIds = db.dao().getUniqueCdvIds()

            if (listaIds.isNotEmpty()) {
                val adapter = CdvAdapter(listaIds)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this@CdvActivity, "Nenhum CDV encontrado!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
package com.univesp.sitcon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editUser = findViewById<TextInputEditText>(R.id.editUser)
        val editPassword = findViewById<TextInputEditText>(R.id.editPassword)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val usuarioDigitado = editUser.text.toString().trim()
            val senhaDigitada = editPassword.text.toString().trim()

            if (usuarioDigitado.isEmpty() || senhaDigitada.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 1. BACKDOOR (A pedido: admin/admin sempre funciona)
            if (usuarioDigitado == "admin" && senhaDigitada == "admin") {
                abrirMenu()
                return@setOnClickListener
            }

            // 2. VALIDAÇÃO REAL (Banco de Dados)
            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(applicationContext)

                // Busca usuário no banco (tabela 'usuarios')
                // Nota: Certifique-se que seu DAO tem o método checkLogin
                val usuarioEncontrado = db.dao().checkLogin(usuarioDigitado, senhaDigitada)

                if (usuarioEncontrado != null) {
                    Toast.makeText(this@MainActivity, "Bem-vindo, ${usuarioEncontrado.nome}!", Toast.LENGTH_SHORT).show()
                    abrirMenu()
                } else {
                    Toast.makeText(this@MainActivity, "Usuário ou senha inválidos.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun abrirMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish() // Fecha a tela de login para não voltar com o botão "Voltar"
    }
}
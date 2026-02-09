package com.univesp.sitcon // <--- CONFIRA SE ESSE É O SEU PACOTE

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.univesp.sitcon.data.AppDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- AQUI ESTÁ A CORREÇÃO: "Apresentando" os itens da tela para o código ---
        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        val edtSenha = findViewById<EditText>(R.id.edtSenha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val btnCriarConta = findViewById<Button>(R.id.btnCriarConta)
        // --------------------------------------------------------------------------

        // Configurando o botão Entrar
        btnEntrar.setOnClickListener {
            val usuario = edtUsuario.text.toString()
            val senha = edtSenha.text.toString()

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val db = AppDatabase.getDatabase(applicationContext)
                    val userEncontrado = db.dao().checkLogin(usuario, senha)

                    // Login mestre (admin) OU Login do Banco
                    if (userEncontrado != null || (usuario == "admin" && senha == "admin")) {
                        Toast.makeText(this@MainActivity, "Login aprovado!", Toast.LENGTH_SHORT).show()

                        // Abre o Menu
                        val intent = Intent(this@MainActivity, MenuActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Configurando o botão Criar Conta (Deixa só um Toast por enquanto)
        btnCriarConta.setOnClickListener {
            Toast.makeText(this, "Funcionalidade em breve!", Toast.LENGTH_SHORT).show()
        }
    }
}
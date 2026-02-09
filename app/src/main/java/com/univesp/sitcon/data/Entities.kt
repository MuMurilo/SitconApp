package com.univesp.sitcon.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Tabela de Usuários
@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "matricula") val matricula: String,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "senha") val senha: String
)

// Tabela AMV (Chave composta: idamv + tipofuncao)
@Entity(tableName = "amv", primaryKeys = ["idamv", "tipofuncao"])
data class AMV(
    @ColumnInfo(name = "idamv") val idAmv: Int,
    @ColumnInfo(name = "tipofuncao") val tipoFuncao: String,
    val L1: String?, val L2: String?, val L3: String?, val L4: String?,
    val L5: String?, val L6: String?, val L7: String?, val L9: String?,
    val L10: String?, val tower: String?, @ColumnInfo(name = "interface") val interface_: String?, // 'interface' é palavra reservada, usamos interface_
    val L14: String?, val L15: String?, val L16: String?, val L17: String?,
    val L18: String?, val L20: String?, val L21: String?, val L22: String?,
    val L23: String?
) : Serializable


// Tabela Sinais (Chave composta: idSinais + tipoAspecto)
@Entity(tableName = "sinais", primaryKeys = ["idSinais", "tipoAspecto"])
data class Sinais(
    @ColumnInfo(name = "idSinais") val idSinais: Int,
    @ColumnInfo(name = "tipoAspecto") val tipoAspecto: String,

    // As colunas que o Android achou que tinham sumido:
    val L1: String?, val L2: String?, val L3: String?, val L4: String?,
    val L5: String?, val L6: String?, val L7: String?, val L8: String?,
    // Note que não tem L9 no seu banco, então não colocamos aqui
    val L10: String?,

    val tower: String?,

    @ColumnInfo(name = "interface") // O mapeamento importante
    val interface_: String?,

    val L14: String?, val L15: String?, val L16: String?,
    // Não tem L17 no banco
    val L18: String?, val L19: String?, val L20: String?,
    val L21: String?, val L22: String?, val L23: String?
) : Serializable



// Tabela CDV (Chave composta: idcdv + tipo)
@Entity(tableName = "cdv", primaryKeys = ["idcdv", "tipo"])
data class CDV(
    @ColumnInfo(name = "idcdv") val idCdv: String,
    @ColumnInfo(name = "tipo") val tipo: String,
    val L1: String?, val L2: String?, val L3: String?, val L4: String?,
    val L5: String?, val L6: String?, val L7: String?, val L8: String?,
    val L9: String?, val L10: String?, val tower: String?,
    @ColumnInfo(name = "interface") // <--- ADICIONE ISSO AQUI
    val interface_: String?,
    val L14: String?, val L15: String?, val L16: String?, val L17: String?,
    val L18: String?, val L20: String?, val L21: String?, val L22: String?,
    val L23: String?
) : Serializable



// Tabela MatriculasValidas
@Entity(tableName = "matriculas_validas")
data class MatriculaValida(
    @PrimaryKey @ColumnInfo(name = "matricula") val matricula: String
)
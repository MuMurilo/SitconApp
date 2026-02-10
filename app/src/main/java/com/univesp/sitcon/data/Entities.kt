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
    @ColumnInfo(name = "L1") val L1: String?,
    @ColumnInfo(name = "L2") val L2: String?,
    @ColumnInfo(name = "L3") val L3: String?,
    @ColumnInfo(name = "L4") val L4: String?,
    @ColumnInfo(name = "L5") val L5: String?,
    @ColumnInfo(name = "L6") val L6: String?,
    @ColumnInfo(name = "L7") val L7: String?,
    @ColumnInfo(name = "L8") val L8: String?,
    @ColumnInfo(name = "L10") val L10: String?,
    @ColumnInfo(name = "tower") val tower: String?,
    @ColumnInfo(name = "interface") val interface_: String?,
    @ColumnInfo(name = "L14") val L14: String?,
    @ColumnInfo(name = "L15") val L15: String?,
    @ColumnInfo(name = "L16") val L16: String?,
    @ColumnInfo(name = "L18") val L18: String?,
    @ColumnInfo(name = "L19") val L19: String?,
    @ColumnInfo(name = "L20") val L20: String?,
    @ColumnInfo(name = "L21") val L21: String?,
    @ColumnInfo(name = "L22") val L22: String?,
    @ColumnInfo(name = "L23") val L23: String?
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
package com.univesp.sitcon.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SitconDao {

    // --- LOGIN (Mantenha este) ---
    @Query("SELECT * FROM usuarios WHERE login = :login AND senha = :senha")
    suspend fun checkLogin(login: String, senha: String): Usuario?

    // --- NOVAS FUNÇÕES DO AMV (Adicione estas duas) ---

    // 1. Traz apenas os números dos AMVs (para a lista não repetir)
    @Query("SELECT DISTINCT idAmv FROM amv ORDER BY idAmv ASC")
    suspend fun getUniqueAmvIds(): List<Int>

    // 2. Traz todas as variações (NWP, NWR, etc) de UM único número de AMV
    @Query("SELECT * FROM amv WHERE idAmv = :idAmvSelecionado")
    suspend fun getAmvFunctions(idAmvSelecionado: Int): List<AMV>

    // Buscar Sinais
    @Query("SELECT * FROM sinais")
    suspend fun getAllSinais(): List<Sinais>

    // Buscar CDVs
    @Query("SELECT * FROM cdv")
    suspend fun getAllCDVs(): List<CDV>
}
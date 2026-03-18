package com.univesp.sitcon.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SitconDao {

    @Query("SELECT * FROM usuarios WHERE login = :login AND senha = :senha")
    suspend fun checkLogin(login: String, senha: String): Usuario?

    // --- MÓDULO AMV ---
    // Abordagem de exclusão manual: ignorando o ID 1
    @Query("SELECT DISTINCT idAmv FROM amv WHERE idAmv != 1 ORDER BY idAmv ASC")
    suspend fun getUniqueAmvIds(): List<Int>

    @Query("SELECT * FROM amv WHERE idAmv = :id AND TRIM(tipofuncao) IN (:funcoes) ORDER BY tipofuncao ASC")
    suspend fun getAmvDetailsByFunction(id: Int, funcoes: List<String>): List<AMV>

    @Query("SELECT * FROM amv WHERE idAmv = :idAmvSelecionado")
    suspend fun getAmvFunctions(idAmvSelecionado: Int): List<AMV>

    // --- MÓDULO SINAIS ---
    @Query("SELECT DISTINCT idSinais FROM sinais ORDER BY idSinais ASC")
    suspend fun getUniqueSinaisIds(): List<Int>

    @Query("SELECT * FROM sinais WHERE idSinais = :idSinalSelecionado")
    suspend fun getSinaisById(idSinalSelecionado: Int): List<Sinais>

    // --- MÓDULO CDV ---
    @Query("SELECT DISTINCT idcdv FROM cdv ORDER BY idcdv ASC")
    suspend fun getUniqueCdvIds(): List<String>

    @Query("SELECT * FROM cdv WHERE idcdv = :idCdvSelecionado")
    suspend fun getCdvDetails(idCdvSelecionado: String): List<CDV>
}
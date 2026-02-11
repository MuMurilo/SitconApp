package com.univesp.sitcon.utils

object SitconUtils {

    // Coordenadas dos AMVs
    val AMV_COORDINATES = mapOf(
        3 to Pair(-23.53772748, -46.62564841),
        7 to Pair(-23.537293002295275, -46.6272271538021),
        9 to Pair(-23.537224763636324, -46.62748464586983),
        11 to Pair(-23.536951907113195, -46.62862858190322),
        25 to Pair(-23.53481585882352, -46.63692885775565),
        27 to Pair(-23.53456763366985, -46.63777073643062),
        29 to Pair(-23.53160774467059, -46.641171676914915),
        31 to Pair(-23.531280618446186, -46.641427018385784),
        39 to Pair(-23.530611035553193, -46.64175876209118),
        43 to Pair(-23.535751059028946, -46.633295825547265),
        47 to Pair(-23.535590081366507, -46.63401657557258)
    )

    // Mapa de Traduções - Ajustado para os nomes das variáveis no Kotlin (CamelCase)
    val TRADUCOES_GERAIS = mapOf(
        "idAmv" to "AMV",
        "tipoFuncao" to "Tipo da Função",
        "idCdv" to "CDV",
        "tipo" to "Tipo",
        "idSinais" to "SINAL",
        "tipoAspecto" to "Tipo do Aspecto",
        "interface_" to "Bastidor de Interface", // Atenção aqui: interface_
        "tower" to "NX",
        "L1" to "Locação 1", "L2" to "Locação 2", "L3" to "Locação 3",
        "L4" to "Locação 4", "L5" to "Locação 5", "L6" to "Locação 6",
        "L7" to "Locação 7", "L8" to "Locação 8", "L9" to "Locação 9",
        "L10" to "Locação 10", "L14" to "Locação 14", "L15" to "Locação 15",
        "L16" to "Locação 16", "L17" to "Locação 17", "L18" to "Locação 18",
        "L19" to "Locação 19", "L20" to "Locação 20", "L21" to "Locação 21",
        "L22" to "Locação 22", "L23" to "Locação 23"
    )

    // Função de formatação
    fun formatarValorLocacao(valor: String?): String {
        if (valor.isNullOrEmpty() || !valor.contains("-")) return valor ?: ""

        val partes = valor.split("-")
        return if (partes.size >= 3) {
            "Caixa ${partes[0]}, TB ${partes[1]}-${partes[2]}"
        } else if (partes.size == 2) {
            "Caixa ${partes[0]}, TB ${partes[1]}"
        } else {
            valor
        }
    }
}
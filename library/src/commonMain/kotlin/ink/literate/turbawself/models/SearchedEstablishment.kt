package ink.literate.turbawself.models

data class SearchedEstablishment(
    val code: Long,
    val version: String,
    val city: String? = null,
    val name: String
)
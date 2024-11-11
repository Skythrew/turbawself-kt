package ink.literate.turbawself.models

data class Terminal(
    val id: Long,
    val localID: Long,
    val code: Long,
    val name: String,
    val price: List<TerminalPrice>
)
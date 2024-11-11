package ink.literate.turbawself.models

data class Summary(
    val latestPayment: Payment? = null,
    val history: List<HistoryPayment>? = null,
    val balances: List<Balance>
)
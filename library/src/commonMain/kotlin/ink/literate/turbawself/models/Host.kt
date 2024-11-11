package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime

data class Host(
    val id: Long,
    val originalID: Long,
    val establishment: Establishment,
    val firstName: String,
    val lastName: String,
    val mode: String,
    val quality: String,
    val division: String,
    val lunchPrice: Double,
    val paymentPermission: Boolean,
    val reservationPermission: Boolean,
    val cafeteriaPermission: Boolean,
    val lastSyncDate: LocalDateTime,
    val disabled: Boolean,
    val privatePassword: Boolean,
    val allowInsufficientCredit: Boolean,
    val codedCard: Long
)

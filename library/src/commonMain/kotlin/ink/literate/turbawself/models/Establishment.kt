package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime

data class Reservation(
    val id: Long,
    val usage: Long,
    val elecom: Long,
    val endReservation: String? = null
)

data class Permissions(
    val canStudentUseQrCode: Boolean,
    val canCompanionUseQrCode: Boolean,
    val canInternUseQrCode: Boolean
)

data class Establishment(
    val code: Long,
    val name: String,
    val version: String,
    val id: Long,
    val address1: String? = null,
    val address2: String? = null,
    val postalCode: String,
    val city: String? = null,
    val phone: String? = null,
    val website: String,
    val email: String,
    val login: String,
    val password: String,
    val firstSyncDate: LocalDateTime,
    val lastSyncDate: LocalDateTime,
    val lastSelfSyncDate: LocalDateTime,
    val turboselfID: Long,
    val currencySymbol: String,
    val hideHistory: Boolean,

    val reservations: List<Reservation>,

    val uai: String,
    val disabled: Boolean,
    /** MAC address of the establishment's Turboself server. */
    val serverMAC: String,
    val maximumTransactionAllowed: Double,
    val totalReservations: Long,
    val welcomeMessage: String? = null,

    val minimumMeals: Long,
    val minimumClaim: Double,
    val minimumCredit: Double,

    val permissions: Permissions
)
package ink.literate.turbawself.models

data class Session(
    val userID: Long,

    /**
     * ID of the establishment
     * the user is currently logged in.
     */
    val hostID: Long
)
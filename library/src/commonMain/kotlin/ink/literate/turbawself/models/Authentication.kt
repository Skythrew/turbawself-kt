package ink.literate.turbawself.models

import io.ktor.client.*

data class Authentication(
    val username: String,

    /**
     * You can omit this property
     * if you want to force using the `accessToken`.
     *
     * Note that if the `accessToken` is expired,
     * and the `password` is not provided,
     * the API will return an error.
     */
    val password: String? = null,

    /**
     * JWT access token generated
     * by the API that expires
     * after 1 hour.
     *
     * Will be automatically refreshed
     * if the `password` is provided.
     */
    var accessToken: String? = null,

    /**
     * Timestamp telling when the
     * `accessToken` will expire.
     */
    var accessTokenExpiration: Long? = null,

    val client: HttpClient = HttpClient()
)
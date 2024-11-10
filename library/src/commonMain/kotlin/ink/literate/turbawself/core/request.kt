package ink.literate.turbawself.core

import ink.literate.turbawself.api.login
import ink.literate.turbawself.models.Authentication
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

class Request(version: Int, path: String) {
    val url: Url
    var headers: MutableMap<String, String> = mutableMapOf()
    var content = ""
    var method: HttpMethod = HttpMethod.Get

    init {
        this.url = Url("https://api-rest-prod.incb.fr/api/v${version}/${path}")
    }

    fun setJSON(json: JsonObject): Request {
        this.method = HttpMethod.Post
        this.content = Json.encodeToString(json)
        this.headers = mutableMapOf(
            "Content-Type" to "application/json"
        )

        return this
    }

    private suspend fun refresh (auth: Authentication) {
        // Token has expired and we should renew it.
        if (auth.password == null)
            throw Error("The access token is invalid and the password is not provided to refresh.")

        // Refresh the access token and expiration
        login(auth)
    }

    /**
     * Adds the access token to the request headers.
     *
     * Also checks if the access token is expired
     * and refreshes it if necessary.
     */
    suspend fun useAuthentication (auth: Authentication): Request {
        if (auth.accessTokenExpiration != null) {
            if (Clock.System.now().toEpochMilliseconds() >= auth.accessTokenExpiration!!) {
                // Token has expired and we should renew it.
                this.refresh(auth)
            }
        }

        if (auth.accessToken == null) {
            // Token is not provided, we should create it.
            this.refresh(auth)
        }

        this.headers["Authorization"] = "Bearer ${auth.accessToken!!}"

        return this
    }

    suspend fun send(client: HttpClient = HttpClient()): JsonElement {
        val httpMethod = this.method
        val httpHeaders = this.headers
        val httpContent = this.content

        val response = client.request (url) {
            method = httpMethod
            headers {
                httpHeaders.forEach { (k, v) -> header(k, v) }
            }
            setBody(httpContent)
        }

        return handleResponse(response)
    }
}
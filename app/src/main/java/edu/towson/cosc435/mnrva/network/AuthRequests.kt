package edu.towson.cosc435.mnrva.network

import com.google.gson.Gson
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.data.model.user.ExistingUser
import edu.towson.cosc435.mnrva.data.model.user.NewUser
import edu.towson.cosc435.mnrva.utils.extractToken
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

// What requests can we make?
interface IAuthRequests {
    suspend fun login(email: String, password: String): String?
    suspend fun register(name: String, email: String, password: String): String
    suspend fun testCredentials(token: String)
}

class AuthRequests : IAuthRequests {
    private val client = DependencyGraph.okHttpClient
    private val jsonType = "application/json; charset=utf-8".toMediaType()

    // Send the email and password to the API, set JWT if successful
    override suspend fun login(email: String, password: String): String {
        return withContext(DependencyGraph.ioDispatcher) {
            val json = "{\"email\":\"$email\", \"password\":\"$password\"}"
            val body = json.toRequestBody(jsonType)
            val request = Request.Builder().url(LOGIN_URL).post(body).build()
            val response = client.newCall(request).execute()
            val token: String = extractToken(response.headers["set-cookie"].orEmpty())
            response.close()
            token
        }
    }

    // Register a new user to the API, set JWT if successful
    override suspend fun register(name: String, email: String, password: String): String {
        return withContext(DependencyGraph.ioDispatcher) {
            val newUser = NewUser(name, email, password)
            val gson = Gson()
            val newUserJson = gson.toJson(newUser)
            val requestBody = newUserJson.toRequestBody(jsonType)
            val request = Request.Builder().post(requestBody).url(REGISTER_URL).build()
            val response = client.newCall(request).execute()
            val token: String = extractToken(response.headers["set-cookie"].orEmpty())
            response.close()
            token
        }
    }

    // Send the current JWT to the API to check for validity; stores name and JWT if successful
    override suspend fun testCredentials(token: String) {
        withContext(DependencyGraph.ioDispatcher) {
            val request = Request.Builder().get().url(CHECK_USER).addHeader("Cookie", "jwt=$token").build()
            val response = client.newCall(request).execute()
            if (response != null && response.isSuccessful) {
                val responseBody = response.body
                if (responseBody != null) {
                    val jsonString = responseBody.string()
                    val gson = Gson()
                    val userData = gson.fromJson(jsonString, ExistingUser::class.java)
                    DependencyGraph.settingsRepository.setName(userData.name)
                    DependencyGraph.settingsRepository.setJwt(token)
                }
            }
            response.close()
        }
    }

    // Object holding string constants for API endpoints
    companion object AuthEndpoints {
        private const val BASE_URL = "https://mnrva.alexjenkins.dev/api"
        const val LOGIN_URL = "$BASE_URL/auth/login"
        const val REGISTER_URL = "$BASE_URL/auth/register"
        const val CHECK_USER = "$BASE_URL/auth/check"
    }
}
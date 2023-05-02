package edu.towson.cosc435.mnrva.network

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

interface IAuthRequests {
    suspend fun testAuthenticated(): Boolean
    suspend fun login(email: String, password: String): Boolean
    suspend fun register(name: String, email: String, password: String): Boolean
}

class AuthRequests : IAuthRequests {

    // OkHTTP Client
    private val client = OkHttpClient()

    // Endpoints
    companion object Endpoints {
        private const val BASE_URL: String = "https://mnrva.alexjenkins.dev/api"
        const val TEST_AUTH: String = "$BASE_URL/auth/test-protected"
    }

    override suspend fun testAuthenticated(): Boolean {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder().get().url(Endpoints.TEST_AUTH).build()
            val response = client.newCall(request).execute()
            val responseBody = response.body
            val status = if (responseBody != null) {
                val string = responseBody.string()
                val gson = Gson()
                gson.fromJson(string, TestAuthResponse::class.java)
            } else null
            Log.d(null, "Authenticated: ${status!!.status}")
            status.status
        }
    }

    override suspend fun login(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun register(name: String, email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}

data class TestAuthResponse(val status: Boolean)
package edu.towson.cosc435.mnrva

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class HttpRequest {

    companion object {
        //basically makes the following functions 'static' so we can do
        //HttpRequest.makeRequest instead of making a whole new class every time
        fun makeRequest(url_string: String): String {

            //details here: https://developer.android.com/reference/java/net/HttpURLConnection
            val urlObject = URL(url_string)
            val connection = urlObject.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            //some chatgpt assisted code: needs testing
            return try {
                val inputStream = connection.inputStream
                val response = inputStream.bufferedReader().use { it.readText() }
                inputStream.close()
                response

            } catch (e: Exception) {
                Log.d("GET_REQUEST", "error with get request: $e")
                "HttpRequest Error: $e"
            } finally {
                connection.disconnect()
            }
        }
    }
}
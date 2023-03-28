package edu.towson.cosc435.mnrva

import java.net.HttpURLConnection
import java.net.URL

class HttpRequest {

    companion object{//basicly makes the following functions 'static' so we can do
        //HttpRequest.doSomeithng instead of making a whole new class every time
        fun makeRequest(url: String): String{

            //details here: https://developer.android.com/reference/java/net/HttpURLConnection
            val url = URL(url)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            //some chatgpt assisted code: needs testing
            try {
                val inputStream = connection.inputStream
                val response = inputStream.bufferedReader().use { it.readText() }
                inputStream.close()
                print(response)
                return response
            } finally {
                connection.disconnect()
            }
            return "Error in request"
        }
    }
}
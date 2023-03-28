package edu.towson.cosc435.mnrva

import org.junit.Assert
import org.junit.Test

class HttpRequestTest {

    @Test
    fun simpleHttpTest() {
        val myUrlTest = "https://www.youtube.com/"
        val response = HttpRequest.makeRequest(myUrlTest)
        Assert.assertTrue(response != null)
    }
}
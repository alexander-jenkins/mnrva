package edu.towson.cosc435.mnrva.utils

fun extractToken(cookie: String): String {
    // we need everything between = and ;
    return cookie.substringAfter("=").substringBefore(";")
}
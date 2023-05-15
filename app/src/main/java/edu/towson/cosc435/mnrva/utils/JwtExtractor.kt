package edu.towson.cosc435.mnrva.utils

fun extractToken(cookie: String): String {
    // Extract the JWT from the provided string- substring between "=" and ";"
    return cookie.substringAfter("=").substringBefore(";")
}
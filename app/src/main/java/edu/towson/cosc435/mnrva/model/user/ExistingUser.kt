package edu.towson.cosc435.mnrva.model.user

data class ExistingUser(
    val authenticated: Boolean,
    val name: String,
    val email: String
)
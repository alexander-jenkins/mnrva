package edu.towson.cosc435.mnrva.model.event

data class NewEvent(
    val title: String,
    val description: String?,
    val start: String,
    val end: String?,
    val tags: String?,
)

package edu.towson.cosc435.mnrva.model

import java.time.LocalDateTime

data class Event(
    val owner: String,
    val title: String,
    val description: String?,
    val start: LocalDateTime,
    val end: LocalDateTime?,
    val tags: String?,
)
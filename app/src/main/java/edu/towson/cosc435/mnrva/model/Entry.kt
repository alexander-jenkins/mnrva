package edu.towson.cosc435.mnrva.model

import kotlinx.datetime.LocalDateTime

data class Entry(
    //TODO: change data types if necessary
   val title: String,
   val date: LocalDateTime,
   val start_time: String,
   val end_time: String,
   val description: String,
   val tag: String,
) {
}
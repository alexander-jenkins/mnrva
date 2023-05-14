package edu.towson.cosc435.mnrva.model.event

import com.google.gson.annotations.SerializedName

data class SyncedEventResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("owner_id") val owner: String,
    val title: String,
    val description: String?,
    val start: String,
    val end: String?,
    val tags: String?,
    @SerializedName("__v") val version: String
)
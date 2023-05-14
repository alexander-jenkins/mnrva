package edu.towson.cosc435.mnrva.network

import android.util.Log
import androidx.compose.runtime.getValue
import com.google.gson.Gson
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.event.Event
import edu.towson.cosc435.mnrva.model.event.NewEvent
import edu.towson.cosc435.mnrva.model.event.NewEventResponse
import edu.towson.cosc435.mnrva.model.event.SyncedEventResponse
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.LocalDateTime

interface IEventRequests {
    suspend fun newEvent(
        title: String,
        start: LocalDateTime,
        end: LocalDateTime?,
        description: String?,
        tags: String?
    )

    suspend fun downloadEvents(): List<SyncedEventResponse>
    suspend fun deleteEvent(id: String)
}

class EventRequests : IEventRequests {
    private val client = DependencyGraph.okHttpClient
    private val jsonType = "application/json; charset=utf-8".toMediaType()
    private val token by DependencyGraph.settingsRepository.jwt
    private val eventRepository = DependencyGraph.eventRepository

    override suspend fun newEvent(
        title: String,
        start: LocalDateTime,
        end: LocalDateTime?,
        description: String?,
        tags: String?
    ) {
        withContext(DependencyGraph.ioDispatcher) {
            val gson = Gson()
            val body = gson.toJson(
                NewEvent(
                    title = title,
                    start = start.toString(),
                    end = end.toString(),
                    description = description,
                    tags = tags
                )
            ).toRequestBody(jsonType)
            val request =
                Request.Builder().url(EVENTS).post(body).addHeader("Cookie", "jwt=$token").build()

            val response = client.newCall(request).execute()
            if (response != null && response.isSuccessful) {
                val responseBody = response.body
                if (responseBody != null) {
                    val gson = Gson()
                    val jsonString = responseBody.string()
                    val newEventData = gson.fromJson(jsonString, NewEventResponse::class.java)
                    val eventId = newEventData.id
                    val createdEvent = Event(
                        id = eventId,
                        title = title,
                        start = start,
                        end = end,
                        description = description,
                        tags = tags
                    )
                    response.close()
                    eventRepository.addEvent(createdEvent)
                }
            }
        }
    }

    override suspend fun downloadEvents(): List<SyncedEventResponse> {
        return withContext(DependencyGraph.ioDispatcher) {
            val gson = Gson()
            var events = listOf<SyncedEventResponse>()
            val request =
                Request.Builder().url(EVENTS).get().addHeader("Cookie", "jwt=$token").build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body
                if (responseBody != null) {
                    val json = responseBody.string()
                    Log.d("MNRVA", json)
                    events = gson.fromJson(json, Array<SyncedEventResponse>::class.java).toList()
                }
            }
            response.close()
            events
        }
    }

    override suspend fun deleteEvent(id: String) {
        withContext(DependencyGraph.ioDispatcher) {
            val request =
                Request.Builder().url("$EVENTS/$id").delete().addHeader("Cookie", "jwt=$token")
                    .build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val event = eventRepository.getById(id)
                if (event != null) {
                    eventRepository.deleteEvent(event)
                }
            }
        }
    }

    companion object EventEndpoints {
        private const val BASE_URL = "https://mnrva.alexjenkins.dev/api"
        const val EVENTS = "$BASE_URL/events"
    }

}
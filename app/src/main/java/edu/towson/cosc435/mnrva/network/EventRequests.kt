package edu.towson.cosc435.mnrva.network

import android.util.Log
import androidx.compose.runtime.getValue
import com.google.gson.Gson
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.data.model.event.Event
import edu.towson.cosc435.mnrva.data.model.event.NewEvent
import edu.towson.cosc435.mnrva.data.model.event.NewEventResponse
import edu.towson.cosc435.mnrva.data.model.event.SyncedEventResponse
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.time.LocalDateTime

interface IEventRequests {
    suspend fun newEvent(
        title: String, start: LocalDateTime, end: LocalDateTime?, description: String?, tags: String?
    )

    suspend fun downloadEvents(): List<SyncedEventResponse>
    suspend fun deleteEvent(id: String)
    suspend fun updateEvent(event: Event)
}

class EventRequests : IEventRequests {
    private val client = DependencyGraph.okHttpClient
    private val jsonType = "application/json; charset=utf-8".toMediaType()
    private val token by DependencyGraph.settingsRepository.jwt
    private val eventRepository = DependencyGraph.eventRepository

    // Insert a new event from the given parameters into the API; store event in SQLite if successful
    override suspend fun newEvent(
        title: String, start: LocalDateTime, end: LocalDateTime?, description: String?, tags: String?
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
            val request = Request.Builder().url(EVENTS).post(body).addHeader("Cookie", "jwt=$token").build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body
                if (responseBody != null) {
                    val jsonString = responseBody.string()
                    val newEventData = gson.fromJson(jsonString, NewEventResponse::class.java)
                    val eventId = newEventData.id
                    val createdEvent = Event(
                        id = eventId, title = title, start = start, end = end, description = description, tags = tags
                    )
                    response.close()
                    eventRepository.addEvent(createdEvent)
                }
            }
        }
    }

    // Download and store a local copy of all events currently on the API in the SQLite database
    override suspend fun downloadEvents(): List<SyncedEventResponse> {
        return withContext(DependencyGraph.ioDispatcher) {
            val gson = Gson()
            var events = listOf<SyncedEventResponse>()
            val request = Request.Builder().url(EVENTS).get().addHeader("Cookie", "jwt=$token").build()
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

    // Delete an event from the API; removes the local copy from SQLite database if successful
    override suspend fun deleteEvent(id: String) {
        withContext(DependencyGraph.ioDispatcher) {
            val request = Request.Builder().url("$EVENTS/$id").delete().addHeader("Cookie", "jwt=$token").build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val event = eventRepository.getById(id)
                if (event != null) {
                    eventRepository.deleteEvent(event)
                }
            }
            response.close()
        }
    }

    // Update the details of an event on the API; stores the changes in local SQLite database if successful
    override suspend fun updateEvent(event: Event) {
        withContext(DependencyGraph.ioDispatcher) {
            val json =
                "{\"id\": \"${event.id}\", \"title\": \"${event.title}\", \"description\": \"${event.description}\", \"start\": \"${event.start}\", \"end\": \"${event.end}\", \"tags\": \"${event.tags}\"}"
            val body = json.toRequestBody(jsonType)
            Log.d("MNRVA", json)
            val request =
                Request.Builder().url("$EVENTS/${event.id}").put(body).addHeader("Cookie", "jwt=$token").build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                eventRepository.updateEvent(event)
            }
            response.close()
        }
    }

    // Object holding string constants for the API endpoints
    companion object EventEndpoints {
        private const val BASE_URL = "https://mnrva.alexjenkins.dev/api"
        const val EVENTS = "$BASE_URL/events"
    }

}
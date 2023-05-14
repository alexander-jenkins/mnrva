package edu.towson.cosc435.mnrva.network

import android.util.Log
import androidx.compose.runtime.getValue
import com.google.gson.Gson
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.model.event.Event
import edu.towson.cosc435.mnrva.model.event.NewEvent
import edu.towson.cosc435.mnrva.model.event.NewEventResponse
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
            val body =
                gson.toJson(NewEvent(title, start.toString(), end.toString(), description, tags))
                    .toRequestBody(jsonType)
            val request =
                Request.Builder().url(EVENTS).post(body).addHeader("Cookie", "jwt=$token").build()

            val response = client.newCall(request).execute()
            if (response != null && response.isSuccessful) {
                val responseBody = response.body
                if (responseBody != null) {
                    val gson = Gson()
                    val jsonString = responseBody.string()
                    Log.d("MNRVA", jsonString)
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

    companion object EventEndpoints {
        private const val BASE_URL = "https://mnrva.alexjenkins.dev/api"
        const val EVENTS = "$BASE_URL/events"
    }

}
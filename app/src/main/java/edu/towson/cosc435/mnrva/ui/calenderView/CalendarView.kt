package edu.towson.cosc435.mnrva.ui.calenderView

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.gson.Gson
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import edu.towson.cosc435.mnrva.model.Entry
import kotlinx.datetime.LocalDate


@Composable
fun Calendar(){
    val event1 = KalendarEvent(
        LocalDate(2023, 4,4),
        "NAME HERE",
        "DESC HERE"
    )

    val kalenderEvents: List<KalendarEvent> = listOf(event1)

    Kalendar(
        onCurrentDayClick={kDay,kEvents->
            println("${kDay.localDate} has the following events: ${kEvents}")
    },
        kalendarType=KalendarType.Firey,
        kalendarEvents = kalenderEvents
    )
    Text(text = "List")
    LazyColumn{
        items(kalenderEvents){
            event ->
            Log.d("LOG", "Hello world $event")
            val entry: Entry? = parseEntryJson(event.eventDescription)
            if (entry != null){
                Column() {
                    Text(text = entry.title)
                    Text(text = entry.description)
                    Text(text = entry.tag)
                    Text(text = entry.start_time)
                    Text(text = entry.end_time)
                    Text(text = entry.date.toString())
                }
            }
            else{
                Log.d("LOG", "entry didnt parse correctly")
            }
        }
    }
}

fun entryToKalendarEvent(entry: Entry): KalendarEvent?{

    val date: LocalDate = entry.date.date
    val eventName = entry.title
    val eventDesc = entry.description

    return KalendarEvent(date, eventName, eventDesc)
}

fun parseEntryJson(jsonString: String?): Entry? {
    val gson: Gson = Gson()
    try{
        val myVal = gson.fromJson(jsonString, Entry::class.java)
        return myVal
    }
    catch (e: Exception){
        Log.d("LOG", e.toString())
    }
    return null
}


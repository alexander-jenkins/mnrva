package edu.towson.cosc435.mnrva.model

import android.util.Log
import com.google.gson.Gson
import java.time.LocalDateTime

data class Entry(
    //TODO: change data types if necessary
   val id: Int,
   val title: String,
   val date: LocalDateTime,
   val startTime: LocalDateTime?,
   val endTime: LocalDateTime?,
   val description: String,
   val tag: String,
) {
   fun toJson(): String {
      val gson = Gson()
      //todo -- IF we need to jsonify this object, make sure gson will serialize LocalDateTime and if it doesnt then implement it
      return gson.toJson(this)
   }
   companion object{//static functions of Entry
      fun fromJson(jsonString: String?): Entry? {
         val gson = Gson()
         try {
            return gson.fromJson(jsonString, Entry::class.java)
         } catch (e: Exception) {
            Log.d("ERROR", "Json String didn't parse correctly $jsonString")
            Log.d("ERROR", e.toString())
         }
         return null
      }
   }
}


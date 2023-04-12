package edu.towson.cosc435.mnrva.model

import android.util.Log
import com.google.gson.Gson

data class Entry(
    //TODO: change data types if necessary
   val id: Int,
   val title: String,
   val date: String,
   val start_time: String,
   val end_time: String,
   val description: String,
   val tag: String,
) {
   fun toJson(): String {
      val gson = Gson()
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


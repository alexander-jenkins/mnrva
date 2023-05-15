package edu.towson.cosc435.mnrva.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.towson.cosc435.mnrva.data.model.event.Event


@Database(entities = [Event::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MnrvaDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
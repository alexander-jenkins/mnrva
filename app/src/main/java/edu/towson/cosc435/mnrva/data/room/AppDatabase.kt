package edu.towson.cosc435.mnrva.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.towson.cosc435.mnrva.model.Event


@Database(entities = [Event::class], version = 1)
@TypeConverters(Converters::class)
abstract class MnrvaDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
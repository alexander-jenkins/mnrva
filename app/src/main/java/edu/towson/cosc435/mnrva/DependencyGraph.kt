package edu.towson.cosc435.mnrva

import android.content.Context
import android.util.Log
import androidx.room.Room
import edu.towson.cosc435.mnrva.data.EventRepository
import edu.towson.cosc435.mnrva.data.SettingsRepository
import edu.towson.cosc435.mnrva.data.room.MnrvaDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

// This is a helper class to provide dependencies to the application
object DependencyGraph {
    // OkHTTP Client
    lateinit var okHttpClient: OkHttpClient

    // Room Database
    lateinit var database: MnrvaDatabase

    // Settings
    lateinit var settingsRepository: SettingsRepository

    // Repository
    val eventRepository by lazy {
        EventRepository(database.eventDao())
    }

    // Coroutine stuff
    val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO


    // Add things to context
    fun provide(context: Context) {
        Log.d("MNRVA", "Providing application dependencies...")
        settingsRepository = SettingsRepository(context)
        okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http_cache"), (20 * 1024 * 1024).toLong())).build()
        database = Room.databaseBuilder(context, MnrvaDatabase::class.java, "data.db")
            .fallbackToDestructiveMigration().build()
    }
}
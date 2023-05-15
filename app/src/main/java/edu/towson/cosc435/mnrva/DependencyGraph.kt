package edu.towson.cosc435.mnrva

import android.content.Context
import androidx.room.Room
import edu.towson.cosc435.mnrva.data.repository.EventRepository
import edu.towson.cosc435.mnrva.data.repository.SettingsRepository
import edu.towson.cosc435.mnrva.data.room.MnrvaDatabase
import edu.towson.cosc435.mnrva.network.AuthRequests
import edu.towson.cosc435.mnrva.network.EventRequests
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

// This is a helper class to provide dependencies to the application
object DependencyGraph {
    // HTTP  Client declaration
    lateinit var okHttpClient: OkHttpClient

    // SQLite database declaration
    lateinit var database: MnrvaDatabase

    // Settings repository declaration
    lateinit var settingsRepository: SettingsRepository

    // HTTP requests for user authentication
    val authRequests by lazy {
        AuthRequests()
    }

    // HTTP requests for event management
    val eventRequests by lazy {
        EventRequests()
    }

    // Provide the event repository, must be lazy since we need a database object on init
    val eventRepository by lazy {
        EventRepository(database.eventDao())
    }

    // Provide a reference to the IO Dispatcher for the HTTP client
    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO


    // Inject dependencies into the application
    fun provide(context: Context) {

        // Provide an instance of the settings repository, passes ApplicationContext
        settingsRepository = SettingsRepository(context)

        // Provide an instance of the HTTP client, uses a cache file
        okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http_cache"), (20 * 1024 * 1024).toLong())).build()

        // Provide a single instance of the Room SQLite database
        database = Room.databaseBuilder(context, MnrvaDatabase::class.java, "data.db")
            .fallbackToDestructiveMigration().build()
    }
}
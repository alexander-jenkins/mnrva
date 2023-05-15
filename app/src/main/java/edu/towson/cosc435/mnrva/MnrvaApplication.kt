package edu.towson.cosc435.mnrva

import android.app.Application

class MnrvaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inject dependencies into the application at runtime
        DependencyGraph.provide(this)
    }
}
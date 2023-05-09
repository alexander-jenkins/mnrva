package edu.towson.cosc435.mnrva

import android.app.Application

class MnrvaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyGraph.provide(this)
    }
}
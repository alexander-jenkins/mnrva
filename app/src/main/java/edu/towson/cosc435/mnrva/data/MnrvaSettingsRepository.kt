package edu.towson.cosc435.mnrva.data

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class MnrvaSettingsRepository(app: Application) {

    // References to the preferences
    private val preferences = app.getSharedPreferences(REPO_KEY, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    // JWT Auth
    private val _jwt = mutableStateOf(preferences.getString(JWT_PREF_KEY, "")!!)
    val jwt: State<String> = _jwt
    fun setJwt(token: String) {
        _jwt.value = token
        editor.putString(JWT_PREF_KEY, token).apply()
    }

    // Keys
    companion object PreferenceKeys {
        const val REPO_KEY: String = "MNRVA_SETTINGS"
        const val JWT_PREF_KEY: String = "JTW_AUTH_TOKEN"
    }

}
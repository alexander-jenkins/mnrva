package edu.towson.cosc435.mnrva.settings

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class SettingsViewModel(app: Application) : AndroidViewModel(app) {

    // References to the preferences
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    // JWT Auth
    private val _jwt: MutableState<String>
    val jwt: State<String>

    fun setJwt(token: String) {
        _jwt.value = token
        editor.putString(JWT_PREF_KEY, token).apply()
    }

    // Initialize SharedPreferences ref and values
    init {
        preferences = app.getSharedPreferences(REPO_KEY, Context.MODE_PRIVATE)
        editor = preferences.edit()
        _jwt = mutableStateOf(preferences.getString(JWT_PREF_KEY, "")!!)
        jwt = _jwt
    }

    companion object PreferenceKeys {
        const val REPO_KEY: String = "MNRVA_SETTINGS"
        const val JWT_PREF_KEY: String = "JTW_AUTH_TOKEN"
    }

}
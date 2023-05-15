package edu.towson.cosc435.mnrva.data.repository

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class SettingsRepository(context: Context) {
    // References to the preferences
    private val preferences = context.getSharedPreferences(REPO_KEY, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    // manage the JWT used to talk to the API
    private val _jwt = mutableStateOf(preferences.getString(JWT_PREF_KEY, "")!!)
    val jwt: State<String> = _jwt
    fun setJwt(token: String) {
        _jwt.value = token
        editor.putString(JWT_PREF_KEY, token).apply()
    }

    // Store the user's name in memory; shown on the home screen
    private val _name = mutableStateOf(preferences.getString(USER_NAME, DEFAULT_NAME))
    val name = _name
    fun setName(name: String) {
        _name.value = name
        editor.putString(USER_NAME, name)
    }

    // Track the login status of the user; determines which state to show
    private val _authenticated = mutableStateOf(true)
    val authenticated: State<Boolean>
        get() {
            _authenticated.value = (_jwt.value != "")
            return _authenticated
        }

    // Track whether the user wants to create a notification for each new event
    private val _useNotifications = mutableStateOf(preferences.getBoolean(USE_NOTIFICATIONS, true))
    val useNotifications = _useNotifications
    fun setUseNotifications(status: Boolean) {
        _useNotifications.value = status
        editor.putBoolean(USE_NOTIFICATIONS, _useNotifications.value)
    }

    // Keys to references Settings objects
    companion object PreferenceKeys {
        const val REPO_KEY: String = "MNRVA_SETTINGS"
        const val JWT_PREF_KEY: String = "JTW_AUTH_TOKEN"
        const val USER_NAME: String = "USER_NAME"
        const val DEFAULT_NAME: String = "Friend"
        const val USE_NOTIFICATIONS: String = "USE_NOTIFICATIONS"
    }
}
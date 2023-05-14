package edu.towson.cosc435.mnrva.data

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class SettingsRepository(context: Context) {

    // References to the preferences
    private val preferences = context.getSharedPreferences(REPO_KEY, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    // JWT Auth
    private val _jwt = mutableStateOf(preferences.getString(JWT_PREF_KEY, "")!!)
    val jwt: State<String> = _jwt
    fun setJwt(token: String) {
        _jwt.value = token
        editor.putString(JWT_PREF_KEY, token).apply()
    }

    // User Name
    private val _name = mutableStateOf(preferences.getString(USER_NAME, DEFAULT_NAME))
    val name = _name
    fun setName(name: String) {
        _name.value = name
        editor.putString(USER_NAME, name)
    }

    // Authentication status
    private val _authenticated = mutableStateOf(true)
    val authenticated: State<Boolean>
        get() {
            _authenticated.value = (_jwt.value != "")
            return _authenticated
        }

    // create notifications?
    private val _useNotifications = mutableStateOf(preferences.getBoolean(USE_NOTIFICATIONS, true))
    val useNotifications = _useNotifications
    fun setUseNotifications(status: Boolean) {
        _useNotifications.value = status
        editor.putBoolean(USE_NOTIFICATIONS, _useNotifications.value)
    }

    // Keys
    companion object PreferenceKeys {
        const val REPO_KEY: String = "MNRVA_SETTINGS"
        const val JWT_PREF_KEY: String = "JTW_AUTH_TOKEN"
        const val USER_NAME: String = "USER_NAME"
        const val USER_EMAIL: String = "USER_EMAIL"
        const val DEFAULT_NAME: String = "Friend"
        const val USE_NOTIFICATIONS: String = "USE_NOTIFICATIONS"
    }
}
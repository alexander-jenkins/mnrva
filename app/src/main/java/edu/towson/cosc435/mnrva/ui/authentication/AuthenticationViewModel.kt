package edu.towson.cosc435.mnrva.ui.authentication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.towson.cosc435.mnrva.DependencyGraph
import edu.towson.cosc435.mnrva.data.SettingsRepository

class AuthenticationViewModel : ViewModel() {
    private val settingsRepository: SettingsRepository = DependencyGraph.settingsRepository
    private val setToken = settingsRepository::setJwt

    // User's name
    private val _name: MutableState<String> = mutableStateOf("")
    val name: State<String> = _name
    fun setName(name: String) {
        _name.value = name
    }

    // User's email
    private val _email: MutableState<String> = mutableStateOf("")
    val email: State<String> = _email
    fun setEmail(email: String) {
        _email.value = email
    }

    // User's password
    private val _password: MutableState<String> = mutableStateOf("")
    val password: State<String> = _password
    fun setPassword(password: String) {
        _password.value = password
    }

    // Confirm user's password
    private val _confirmPassword: MutableState<String> = mutableStateOf("")
    val confirmPassword: State<String> = _confirmPassword
    fun setConfirmPassword(password: String) {
        _confirmPassword.value = password
    }

    // login
    fun bypass() {
        setName("")
        setEmail("")
        setPassword("")
        setConfirmPassword("")
        settingsRepository.setJwt("{TESTING_TOKEN}")
    }

}
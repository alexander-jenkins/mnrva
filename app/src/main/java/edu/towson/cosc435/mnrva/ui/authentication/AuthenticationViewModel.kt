package edu.towson.cosc435.mnrva.ui.authentication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import kotlinx.coroutines.launch

class AuthenticationViewModel : ViewModel() {
    private val requests = DependencyGraph.authRequests

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
    fun clearFields() {
        setName("")
        setEmail("")
        setPassword("")
        setConfirmPassword("")
    }

    // Get JWT from API
    fun login() {
        viewModelScope.launch {
            val token = requests.login(_email.value, _password.value)
            if (token != "") requests.testCredentials(token)
        }
        clearFields()
    }

    // Register a new account
    fun register() {
        viewModelScope.launch {
            // check if passwords match and aren't empty and that there is a name
            if ((_password.value == _confirmPassword.value && _password.value != "") && _name.value != "") {
                // register account
                val token = requests.register(_name.value, _email.value, _password.value)
                if (token != "") requests.testCredentials(token)
            } else {
                setPassword("")
                setConfirmPassword("")
            }
        }
    }

}
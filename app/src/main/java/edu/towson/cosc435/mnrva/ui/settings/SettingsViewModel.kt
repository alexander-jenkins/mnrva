package edu.towson.cosc435.mnrva.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val settingsRepository = DependencyGraph.settingsRepository
    private val eventRepository = DependencyGraph.eventRepository
    private val eventRequests = DependencyGraph.eventRequests

    private val _useNotifications = settingsRepository.useNotifications
    val useNotifications = _useNotifications
    fun setUseNotifications(status: Boolean) {
        settingsRepository.setUseNotifications(status)
    }

    fun logout() = viewModelScope.launch {
        settingsRepository.setName("")
        settingsRepository.setJwt("")
        setUseNotifications(true)
        eventRepository.clearEvents()
    }

    fun downloadEvents() = viewModelScope.launch {
        val events = eventRequests.downloadEvents()
        eventRepository.addManyEvents(events)
    }

}
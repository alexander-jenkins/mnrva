package edu.towson.cosc435.mnrva.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435.mnrva.DependencyGraph
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val settingsRepository = DependencyGraph.settingsRepository
    private val eventRepository = DependencyGraph.eventRepository

    fun logout() = viewModelScope.launch {
        settingsRepository.setName("")
        settingsRepository.setJwt("")
        eventRepository.clearEvents()
    }

}
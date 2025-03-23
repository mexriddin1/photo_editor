package com.example.todoapp.presentaton.editor

import kotlinx.coroutines.Job
import org.orbitmvi.orbit.ContainerHost

interface EditorContract {

    interface ViewModel : ContainerHost<UiState, Any> {
        fun onEventDispatcher(intent: Intent): Job
    }

    interface Direction {
    }

    sealed interface Intent {

    }

    data class UiState(
        var test: String = ""
    )
}

package com.example.todoapp.presentaton.home

import android.net.Uri
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.ContainerHost

interface HomeContract {

    interface ViewModel : ContainerHost<UiState, Any> {
        fun onEventDispatcher(intent: Intent): Job
    }

    interface Direction {
        suspend fun openEditor(uri: Uri)
    }

    sealed interface Intent {
        data class OpenEditor(val uri: Uri) : Intent
    }

    data class UiState(
        var test: String = ""
    )
}

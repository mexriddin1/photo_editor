package com.example.todoapp.presentaton.editor

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val direction: EditorContract.Direction
) : ViewModel(), EditorContract.ViewModel {

    override val container = container<EditorContract.UiState, Any>(EditorContract.UiState())

    override fun onEventDispatcher(intent: EditorContract.Intent) = intent {
        when (intent) {
            else -> {

            }
        }
    }
}

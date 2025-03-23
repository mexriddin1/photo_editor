package com.example.todoapp.presentaton.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val direction: HomeContract.Direction
) : ViewModel(), HomeContract.ViewModel {

    override val container = container<HomeContract.UiState, Any>(HomeContract.UiState())

    override fun onEventDispatcher(intent: HomeContract.Intent) = intent {
        when (intent) {
            is HomeContract.Intent.OpenEditor -> {
                viewModelScope.launch {
                    direction.openEditor(intent.uri)
                }
            }
        }
    }
}

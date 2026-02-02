package com.youxiang8727.streamletmultiplatform.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface UiState

interface UiEvent

interface UiEffect

abstract class Reducer<S : UiState, E : UiEvent> {
    abstract fun reduce(state: S, event: E): S
}

abstract class MviViewModel<S: UiState, E: UiEvent, F: UiEffect>(
    initialState: S,
    private val reducer: Reducer<S, E>
): ViewModel() {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _event: MutableSharedFlow<E> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 64
    )

    private val _effect: MutableSharedFlow<F> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1
    )
    val effect: SharedFlow<F> = _effect.asSharedFlow()

    fun dispatch(event: E) {
        _event.tryEmit(event)
    }

    fun dispatch(effect: F) {
        _effect.tryEmit(effect)
    }

    init {
        viewModelScope.launch {
            _event.collect { event ->
                _state.update {
                    reducer.reduce(state = _state.value, event = event)
                }
            }
        }
    }
}
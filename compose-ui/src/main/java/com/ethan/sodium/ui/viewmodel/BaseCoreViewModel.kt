package com.ethan.sodium.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ethan.sodium.ui.components.multistate.MultiState
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseCoreViewModel: ViewModel() {

    private val _multiState: MutableStateFlow<MultiState> = MutableStateFlow(MultiState.CONTENT)
    val multistate: MutableStateFlow<MultiState> = _multiState


    fun setMultiState(state: MultiState) {
        _multiState.value = state
    }

}
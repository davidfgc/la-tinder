package com.solucionespruna.latinder.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardsScreenViewModel: ViewModel() {

  private val _uiState = MutableStateFlow(CardsUiState())
  val uiState: StateFlow<CardsUiState>
      get() = _uiState.asStateFlow()

  fun getCards() {
    viewModelScope.launch {
      _uiState.emit(_uiState.value.copy(isLoading = true))
      _uiState.emit(_uiState.value.copy(
        cards = listOf("this is the bottom card", "this is the top card"), isLoading = false))
    }
  }
}

// TODO: define if screen state and data state should be separated
data class CardsUiState(
  val isLoading: Boolean = false,
  val isError: Boolean = false,
  val cards: List<String> = emptyList()
)
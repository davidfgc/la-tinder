package com.solucionespruna.latinder.ui.card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CardsScreenViewModel: ViewModel() {

  private val _uiState = MutableStateFlow(CardsUiState())
  val uiState: StateFlow<CardsUiState>
      get() = _uiState.asStateFlow()

  fun getCards() {
    _uiState.value = _uiState.value.copy(cards = listOf("this is the bottom card", "this is the top card"))
  }
}

data class CardsUiState(
  val cards: List<String> = emptyList()
)
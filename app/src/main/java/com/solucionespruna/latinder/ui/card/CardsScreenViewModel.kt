package com.solucionespruna.latinder.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.latinder.data.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CardsScreenViewModel: ViewModel() {

  private val _uiState = MutableStateFlow<CardsUiState>(CardsUiState.Loading)
  val uiState: StateFlow<CardsUiState>
      get() = _uiState.asStateFlow()

  init {
    getCards()
  }

  fun getCards() {
    viewModelScope.launch(Dispatchers.IO) {
      _uiState.emit(CardsUiState.Loading)
      UserRepositoryImpl().getUsers()
        .catch { _uiState.emit(CardsUiState.Error)}
        .collect {
          val cards = it.map { user -> Card(user.name, user.imageURL) }
          if (cards.isEmpty()) _uiState.emit(CardsUiState.Error)
          else _uiState.emit(CardsUiState.Content(cards))
        }
    }
  }
}

// TODO: define if screen state and data state should be separated
sealed class CardsUiState {
  data object Loading : CardsUiState()
  data object Error : CardsUiState()
  data class Content(val cards: List<Card>) : CardsUiState()
}

data class Card(
  val name: String,
  val imageUrl: String
)
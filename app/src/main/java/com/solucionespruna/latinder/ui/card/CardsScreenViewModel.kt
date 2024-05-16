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

  private val _uiState = MutableStateFlow(CardsUiState())
  val uiState: StateFlow<CardsUiState>
      get() = _uiState.asStateFlow()

  fun getCards() {
    viewModelScope.launch(Dispatchers.IO) {
      _uiState.emit(_uiState.value.copy(isLoading = true))
      UserRepositoryImpl().getUsers()
        .catch { _uiState.emit(_uiState.value.copy(isError = true))}
        .collect {
          _uiState.emit(_uiState.value.copy(
            cards = it.map { user -> Card(user.name, user.imageURL) },
            isLoading = false))
        }
    }
  }
}

// TODO: define if screen state and data state should be separated
data class CardsUiState(
  val isLoading: Boolean = false,
  val isError: Boolean = false,
  val cards: List<Card> = emptyList()
)

data class Card(
  val name: String,
  val imageUrl: String
)
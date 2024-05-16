package com.solucionespruna.latinder.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.latinder.data.UserRepositoryImpl
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
      val userRepository = UserRepositoryImpl()
      val user1 = userRepository.getUser()
      val user2 = userRepository.getUser()
      _uiState.emit(_uiState.value.copy(
        cards = listOf(
          Card(user1.name, user1.imageURL),
          Card(user2.name, user2.imageURL),),
        isLoading = false))
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
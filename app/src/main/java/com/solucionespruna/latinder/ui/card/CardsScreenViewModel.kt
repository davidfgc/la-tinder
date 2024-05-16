package com.solucionespruna.latinder.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.latinder.data.UserRepositoryImpl
import com.solucionespruna.latinder.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CardsScreenViewModel: ViewModel() {

  private val _uiState = MutableStateFlow<CardsScreenUiState>(CardsScreenUiState.Loading)
  val uiState: StateFlow<CardsScreenUiState>
      get() = _uiState.asStateFlow()

  init {
    getCards()
  }

  fun getCards() {
    viewModelScope.launch(Dispatchers.IO) {
      _uiState.emit(CardsScreenUiState.Loading)
      UserRepositoryImpl().getUsers()
        .catch { _uiState.emit(CardsScreenUiState.Error)}
        .collect {
          if (it.isEmpty()) _uiState.emit(CardsScreenUiState.Error)
          else _uiState.emit(CardsScreenUiState.Content(it))
        }
    }
  }
}

sealed class CardsScreenUiState {
  data object Loading : CardsScreenUiState()
  data object Error : CardsScreenUiState()
  data class Content(val users: List<User>) : CardsScreenUiState()
}
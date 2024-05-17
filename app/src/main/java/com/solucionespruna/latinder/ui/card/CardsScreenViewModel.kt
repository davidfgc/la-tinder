package com.solucionespruna.latinder.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solucionespruna.latinder.data.UserRepository
import com.solucionespruna.latinder.data.UserRepositoryImpl
import com.solucionespruna.latinder.domain.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CardsScreenViewModel(private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main): ViewModel() {

  private var userRepositoryImpl: UserRepository = UserRepositoryImpl()
  fun setUserRepositoryImpl(userRepositoryImpl: UserRepository) {
    this.userRepositoryImpl = userRepositoryImpl
  }

  private val _uiState = MutableStateFlow<CardsScreenUiState>(CardsScreenUiState.Loading)
  val uiState: StateFlow<CardsScreenUiState>
      get() = _uiState.asStateFlow()

  init {
    loadUsers()
  }

  fun loadUsers() {
    viewModelScope.launch(mainDispatcher) {
      _uiState.emit(CardsScreenUiState.Loading)
      userRepositoryImpl.getUsers()
        .catch { _uiState.emit(CardsScreenUiState.Error)}
        .collect {
          if (it.isEmpty()) _uiState.emit(CardsScreenUiState.Empty)
          else _uiState.emit(CardsScreenUiState.Content(it))
        }
    }
  }
}

sealed class CardsScreenUiState {
  data object Loading : CardsScreenUiState()
  data object Error : CardsScreenUiState()
  data object Empty : CardsScreenUiState()
  data class Content(val users: List<User>) : CardsScreenUiState()
}
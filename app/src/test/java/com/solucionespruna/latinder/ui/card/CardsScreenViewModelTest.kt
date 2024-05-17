package com.solucionespruna.latinder.ui.card

import com.solucionespruna.latinder.data.UserRepository
import com.solucionespruna.latinder.domain.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CardsScreenViewModelTest {

  @OptIn(ExperimentalCoroutinesApi::class)
  private val testDispatcher = UnconfinedTestDispatcher()
  private lateinit var cardsScreenViewModel:CardsScreenViewModel

  @Before
  fun setup() {
    cardsScreenViewModel = CardsScreenViewModel(testDispatcher)
  }

  @Test
  fun getCardsWithErrorExpectsError() = runTest {
    cardsScreenViewModel.setUserRepositoryImpl(UserRepositoryErrorStub())

    cardsScreenViewModel.loadUsers()

    assertEquals(cardsScreenViewModel.uiState.value, CardsScreenUiState.Error)
  }

  @Test
  fun getCardsWithEmptyListExpectsEmpty() = runTest {
    cardsScreenViewModel.setUserRepositoryImpl(UserRepositorySuccessStub(emptyList()))

    cardsScreenViewModel.loadUsers()

    assertTrue(cardsScreenViewModel.uiState.value is CardsScreenUiState.Empty)
  }

  @Test
  fun loadUsersWithUsersExpectsContent() = runTest {
    val users = listOf(User( "name", "url"))
    cardsScreenViewModel.setUserRepositoryImpl(UserRepositorySuccessStub(users))

    cardsScreenViewModel.loadUsers()

    assertEquals(CardsScreenUiState.Content(users), cardsScreenViewModel.uiState.value)
  }
}

class UserRepositoryErrorStub: UserRepository {
  override fun getUsers() = flow<List<User>> {
    throw AssertionError()
  }
}

class UserRepositorySuccessStub(private val users: List<User>): UserRepository {
  override fun getUsers() = flow {
    emit(users)
  }
}
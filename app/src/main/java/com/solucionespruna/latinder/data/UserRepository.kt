package com.solucionespruna.latinder.data

import com.solucionespruna.latinder.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
  fun getUsers(): Flow<List<User>>
}

class UserRepositoryImpl : UserRepository {
  override fun getUsers() = flow {
    emit(listOf(getUser(), getUser()))
  }

  private suspend fun getUser() =
    RandomUserAdapter(RandomUserServiceImpl().fetchRandomUser().results?.first()!!).toUser()

}
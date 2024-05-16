package com.solucionespruna.latinder.data

import com.solucionespruna.latinder.data.randomuser.RandomUserServiceAdapter
import kotlinx.coroutines.flow.flow

class UserRemoteDataSourceImpl(
  private val randomUserServiceAdapter: RandomUserServiceAdapter = RandomUserServiceAdapter()
)
  : UserRepository.UserRemoteDataSource {

  override fun getUsers() = flow {
    emit(listOf(getUser(), getUser()))
  }

  private suspend fun getUser() = randomUserServiceAdapter.fetchRandomUser()
}
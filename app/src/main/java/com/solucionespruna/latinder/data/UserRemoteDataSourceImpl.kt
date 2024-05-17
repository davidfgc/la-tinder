package com.solucionespruna.latinder.data

import com.solucionespruna.latinder.data.randomuser.RandomUserServiceAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserRemoteDataSourceImpl(
  private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
  private val randomUserServiceAdapter: RandomUserServiceAdapter = RandomUserServiceAdapter()
)
  : UserRepository.UserRemoteDataSource {

  override fun getUsers() = flow {
    emit(listOf(getUser(), getUser()))
  }

  private suspend fun getUser() = withContext(ioDispatcher) {
    randomUserServiceAdapter.fetchRandomUser()
  }
}
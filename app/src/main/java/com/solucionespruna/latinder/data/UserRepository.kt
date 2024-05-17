package com.solucionespruna.latinder.data

import com.solucionespruna.latinder.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
  fun getUsers(): Flow<List<User>>
  interface UserRemoteDataSource {
    fun getUsers(): Flow<List<User>>
  }
  interface UserLocalDataSource
}

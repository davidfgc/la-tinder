package com.solucionespruna.latinder.data

import com.solucionespruna.latinder.domain.User

interface UserRepository {
  suspend fun getUser(): User
}

class UserRepositoryImpl : UserRepository {
  override suspend fun getUser(): User {
    val randomUser = RandomUserServiceImpl().fetchRandomUser().results?.first()
    return RandomUserAdapter(randomUser!!).toUser()
  }
}
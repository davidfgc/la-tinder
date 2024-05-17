package com.solucionespruna.latinder.data

class UserRepositoryImpl(
  private val remoteDataSource: UserRepository.UserRemoteDataSource = UserRemoteDataSourceImpl()) :
  UserRepository, UserRepository.UserRemoteDataSource, UserRepository.UserLocalDataSource {

    override fun getUsers() = remoteDataSource.getUsers()
}
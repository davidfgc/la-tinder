package com.solucionespruna.latinder.data.randomuser

import com.solucionespruna.latinder.domain.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUserServiceAdapter {

  private val api: Retrofit? = null
  private val service: RandomUserService? = null

  private fun getRetrofit(): Retrofit {
    return api ?: Retrofit.Builder()
      .baseUrl("https://randomuser.me/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  private fun getService(): RandomUserService {
    return service ?: getRetrofit().create(RandomUserService::class.java)
  }
  suspend fun fetchRandomUser(): User {
    val randomUser = getService().fetchRandomUser().results!!.first()

    return RandomUserAdapter(randomUser).toUser()
  }
}
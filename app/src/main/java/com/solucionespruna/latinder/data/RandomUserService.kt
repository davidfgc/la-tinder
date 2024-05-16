package com.solucionespruna.latinder.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
  @GET("api/")
  suspend fun fetchRandomUser(@Query("gender") gender: String = "female"): RandomUserResponse
}

class RandomUserServiceImpl {

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
  suspend fun fetchRandomUser(): RandomUserResponse {
    return getService().fetchRandomUser()
  }
}
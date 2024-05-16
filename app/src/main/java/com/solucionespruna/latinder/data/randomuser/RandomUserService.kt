package com.solucionespruna.latinder.data.randomuser

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
  @GET("api/")
  suspend fun fetchRandomUser(@Query("gender") gender: String = "female"): RandomUserResponse
}

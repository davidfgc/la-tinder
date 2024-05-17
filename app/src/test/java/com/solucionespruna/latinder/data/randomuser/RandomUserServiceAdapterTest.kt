package com.solucionespruna.latinder.data.randomuser

import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.URL

class RandomUserServiceAdapterTest {
  @Test
  fun fetchRandomUserNameIsNotEmpty() = runTest {
    val randomUserServiceAdapter = RandomUserServiceAdapter()

    val user = randomUserServiceAdapter.fetchRandomUser()

    assert(user.name.isNotEmpty())
    URL(user.imageURL)
  }
}
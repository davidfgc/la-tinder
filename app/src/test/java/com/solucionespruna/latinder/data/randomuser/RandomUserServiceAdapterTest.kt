package com.solucionespruna.latinder.data.randomuser

import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.URL

class RandomUserServiceAdapterTest {
  @Test
  fun `fetchRandomUser expects name not empty and valid URL`() = runTest {
    val randomUserServiceAdapter = RandomUserServiceAdapter()

    val user = randomUserServiceAdapter.fetchRandomUser()

    assert(user.name.isNotEmpty())
    URL(user.imageURL)
  }
}
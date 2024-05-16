package com.solucionespruna.latinder.data

import com.solucionespruna.latinder.domain.User

class RandomUserAdapter(private val randomUser: RandomUser) {
  fun toUser(): User {
        return User(
            name = "${randomUser.name.first} ${randomUser.name.last}",
            imageURL = randomUser.picture.large
        )
    }
}
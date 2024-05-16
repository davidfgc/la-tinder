package com.solucionespruna.latinder.data.randomuser

data class RandomUser (
  val name: RandomUserName,
  val picture: RandomUserPicture,
)

data class RandomUserName (
  val first: String,
  val last: String,
)

data class RandomUserPicture (
  val large: String,
  val medium: String,
  val thumbnail: String,
)
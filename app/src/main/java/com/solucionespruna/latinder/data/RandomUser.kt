package com.solucionespruna.latinder.data

data class RandomUser (
  val name: RandomeUserName,
  val picture: RandomUserPicture,
)

data class RandomeUserName (
  val first: String,
  val last: String,
)

data class RandomUserPicture (
  val large: String,
  val medium: String,
  val thumbnail: String,
)
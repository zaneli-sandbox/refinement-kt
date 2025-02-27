package com.github.zaneli.refinement.either

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right

@JvmInline
value class PlayerName private constructor(val value: String) {
  companion object {
    fun from(value: String): Either<String, PlayerName> =
        if (value.isEmpty()) {
          Left("空文字です")
        } else if (value.any { it.isWhitespace() }) {
          Left("空白文字が含まれています")
        } else if (value.length > 10) {
          Left("10文字を超えています")
        } else {
          Right(PlayerName(value))
        }
  }
}

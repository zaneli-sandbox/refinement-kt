package com.github.zaneli.refinement.either

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right

@JvmInline
value class PosInt private constructor(val value: Int) {
  companion object {
    fun from(value: Int): Either<String, PosInt> =
        if (value >= 1) {
          Right(PosInt((value)))
        } else {
          Left("1未満です")
        }
  }
}

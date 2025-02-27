package com.github.zaneli.refinement.std

@JvmInline
value class PosInt private constructor(val value: Int) {
  companion object {
    fun from(value: Int): Result<PosInt> =
        if (value >= 1) {
          Result.success(PosInt((value)))
        } else {
          Result.failure(IllegalArgumentException("1未満です"))
        }
  }
}

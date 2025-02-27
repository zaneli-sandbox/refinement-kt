package com.github.zaneli.refinement.std

@JvmInline
value class PlayerName private constructor(val value: String) {
  companion object {
    fun from(value: String): Result<PlayerName> =
        if (value.isEmpty()) {
          Result.failure(IllegalArgumentException("空文字です"))
        } else if (value.any { it.isWhitespace() }) {
          Result.failure(IllegalArgumentException("空白文字が含まれています"))
        } else if (value.length > 10) {
          Result.failure(IllegalArgumentException("10文字を超えています"))
        } else {
          Result.success(PlayerName(value))
        }
  }
}

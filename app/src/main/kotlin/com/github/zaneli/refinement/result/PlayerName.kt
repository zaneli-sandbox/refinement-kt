package com.github.zaneli.refinement.result

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

@JvmInline
value class PlayerName private constructor(val value: String) {
  companion object {
    fun from(value: String): Result<PlayerName, String> =
        if (value.isEmpty()) {
          Err("空文字です")
        } else if (value.any { it.isWhitespace() }) {
          Err("空白文字が含まれています")
        } else if (value.length > 10) {
          Err("10文字を超えています")
        } else {
          Ok(PlayerName(value))
        }
  }
}

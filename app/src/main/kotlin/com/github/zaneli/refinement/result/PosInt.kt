package com.github.zaneli.refinement.result

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

@JvmInline
value class PosInt private constructor(val value: Int) {
  companion object {
    fun from(value: Int): Result<PosInt, String> =
        if (value >= 1) {
          Ok(PosInt((value)))
        } else {
          Err("1未満です")
        }
  }
}

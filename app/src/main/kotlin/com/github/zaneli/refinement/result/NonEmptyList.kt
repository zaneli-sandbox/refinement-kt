package com.github.zaneli.refinement.result

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

data class NonEmptyList<T>(val head: T, val tail: List<T>) {
  fun toList(): List<T> = listOf(head) + tail

  companion object {
    fun <T> from(list: List<T>): Result<NonEmptyList<T>, String> =
        list.getOrNull(0)?.let { head -> Ok(NonEmptyList(head, list.drop(1))) } ?: Err("空リストです")
  }
}

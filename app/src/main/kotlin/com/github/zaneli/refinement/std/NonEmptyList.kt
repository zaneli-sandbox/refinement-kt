package com.github.zaneli.refinement.std

data class NonEmptyList<T>(val head: T, val tail: List<T>) {
  fun toList(): List<T> = listOf(head) + tail

  companion object {
    fun <T> from(list: List<T>): Result<NonEmptyList<T>> =
        list.getOrNull(0)?.let { head -> Result.success(NonEmptyList(head, list.drop(1))) }
            ?: Result.failure(IllegalArgumentException("空リストです"))
  }
}

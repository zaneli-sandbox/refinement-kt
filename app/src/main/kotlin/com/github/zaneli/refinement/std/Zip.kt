package com.github.zaneli.refinement.std

fun <A1, A2, A3, R> zipOrAccumulate(
    a1: () -> Result<A1>,
    a2: () -> Result<A2>,
    a3: () -> Result<A3>,
    transform: (A1, A2, A3) -> R,
): Result<R> {
  val r1 = a1()
  val r2 = a2()
  val r3 = a3()
  val failures = listOf(r1, r2, r3).filter { it.isFailure }
  return if (failures.isEmpty()) {
    Result.success(transform(r1.getOrNull()!!, r2.getOrNull()!!, r3.getOrNull()!!))
  } else {
    Result.failure(AccumulatedException(failures.map { it.exceptionOrNull()!! }))
  }
}

data class AccumulatedException(val errors: List<Throwable>) :
    Throwable(message = errors.map { it.message }.joinToString(","))

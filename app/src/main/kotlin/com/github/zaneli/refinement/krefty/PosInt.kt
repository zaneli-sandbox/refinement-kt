package com.github.zaneli.refinement.krefty

import dev.ustits.krefty.arrow.EitherRefinery
import dev.ustits.krefty.core.Refinement

@JvmInline
value class PosInt private constructor(val value: Int) {
  companion object : EitherRefinery<Int, PosInt>() {
    override fun Refinement<Int>.refine(): Refinement<PosInt> =
        filter { it >= 1 }.map { PosInt(it) }
  }
}

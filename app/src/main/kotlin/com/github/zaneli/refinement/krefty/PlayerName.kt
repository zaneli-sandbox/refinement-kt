package com.github.zaneli.refinement.krefty

import dev.ustits.krefty.arrow.EitherRefinery
import dev.ustits.krefty.core.Refinement

@JvmInline
value class PlayerName private constructor(val value: String) {
  companion object : EitherRefinery<String, PlayerName>() {

    override fun Refinement<String>.refine(): Refinement<PlayerName> =
        filter { it.isNotEmpty() }
            .filter { it.all { !it.isWhitespace() } }
            .filter { it.length <= 10 }
            .map { PlayerName(it) }
  }
}

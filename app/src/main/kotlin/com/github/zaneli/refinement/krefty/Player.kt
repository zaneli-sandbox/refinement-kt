package com.github.zaneli.refinement.krefty

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.toNonEmptyListOrNone
import com.github.zaneli.refinement.Skill
import dev.ustits.krefty.arrow.RefinementError

data class Player(
    val name: PlayerName,
    val level: PosInt,
    val skills: NonEmptyList<Skill>,
) {
  companion object {
    fun from(
        name: String,
        level: Int,
        skills: List<Skill>
    ): Either<NonEmptyList<RefinementError>, Player> =
        Either.zipOrAccumulate(
            PlayerName.from(name),
            PosInt.from(level),
            skills.toNonEmptyListOrNone().toEither { RefinementError },
        ) { name, level, skills -> Player(name, level, skills) }
  }
}

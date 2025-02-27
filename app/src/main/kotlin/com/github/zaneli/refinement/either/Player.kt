package com.github.zaneli.refinement.either

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.toNonEmptyListOrNone
import com.github.zaneli.refinement.Skill

data class Player(
    val name: PlayerName,
    val level: PosInt,
    val skills: NonEmptyList<Skill>,
) {
  companion object {
    fun from(name: String, level: Int, skills: List<Skill>): Either<NonEmptyList<String>, Player> =
        Either.zipOrAccumulate(
            PlayerName.from(name),
            PosInt.from(level),
            skills.toNonEmptyListOrNone().toEither { "空リストです" },
        ) { name, level, skills -> Player(name, level, skills) }
  }
}
